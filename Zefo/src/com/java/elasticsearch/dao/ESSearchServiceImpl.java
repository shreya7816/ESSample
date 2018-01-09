package com.java.elasticsearch.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.elasticsearch.entity.SearchPage;
import com.java.elasticsearch.entity.SortDirection;
import com.java.elasticsearch.exception.ESSearchServiceException;
import com.java.elasticsearch.exception.InvalidFilterException;
import com.java.elasticsearch.factory.FilterFactory;
import com.java.elasticsearch.filter.BaseFilter;
import com.java.elasticsearch.filter.CommonFilterToPropertyMap;
import com.java.elasticsearch.interfaces.ESSearchService;

/**
 * Service to get data from ES
 * 
 * @author 
 *
 */
public class ESSearchServiceImpl implements ESSearchService {

	private Client client;

	private final FilterFactory filterFactory = new FilterFactory(new CommonFilterToPropertyMap());

	public ESSearchServiceImpl(Client client) {
		this.client = client;
	}

	/**
	 * fetches all the data from ES
	 */
	@Override
	public List<SearchPage> getAllData(String index, String type) throws ESSearchServiceException {
		try {
			SearchResponse searchResponse = this.client.prepareSearch(index).setTypes(type)
					.setQuery(QueryBuilders.matchAllQuery()).get();

			List<SearchPage> list = new ArrayList<SearchPage>();
			for (SearchHit searchHit : searchResponse.getHits()) {
				String esDetailPageJSON = searchHit.getSourceAsString();
				ObjectMapper mapper = new ObjectMapper();
				mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				SearchPage detailPage = mapper.readValue(esDetailPageJSON, SearchPage.class);

				list.add(detailPage);
			}
			return list;
		} catch (IOException e) {
			throw new ESSearchServiceException(e);
		}
	}

	/**
	 * fetches the filtered and sorted data from ES
	 */
	@Override
	public List<SearchPage> getData(String index, String type, List<BaseFilter> filterList,
			Map<String, SortDirection> orderByProperties, Integer startIndex, Integer batchSize)
			throws ESSearchServiceException {

		try {
			SortBuilder sortBuilder = null;

			for (Map.Entry<String, SortDirection> orderByProperty : orderByProperties.entrySet()) {
				SortOrder sortOrder = orderByProperty.getValue().equals(SortDirection.ASC) ? SortOrder.ASC
						: SortOrder.DESC;
				sortBuilder = SortBuilders.fieldSort(orderByProperty.getKey()).order(sortOrder);
			}
			SearchResponse searchResponse = this.client.prepareSearch(index).setTypes(type)
					.setQuery(this.filterFactory.buildQueryFromFilters(filterList)).addSort(sortBuilder)
					.setFrom(startIndex).setSize(batchSize).get();

			List<SearchPage> list = new ArrayList<SearchPage>();
			for (SearchHit searchHit : searchResponse.getHits()) {
				String esDetailPageJSON = searchHit.getSourceAsString();
				ObjectMapper mapper = new ObjectMapper();
				SearchPage detailPage = mapper.readValue(esDetailPageJSON, SearchPage.class);
				list.add(detailPage);
			}
			return list;
		} catch (IOException | InvalidFilterException e) {
			throw new ESSearchServiceException(e);
		}
	}

}
