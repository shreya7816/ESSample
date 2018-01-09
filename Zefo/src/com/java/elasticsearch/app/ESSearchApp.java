package com.java.elasticsearch.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.elasticsearch.dao.ESSearchServiceImpl;
import com.java.elasticsearch.entity.SearchPage;
import com.java.elasticsearch.entity.SortDirection;
import com.java.elasticsearch.filter.BaseFilter;
import com.java.elasticsearch.util.ESUtils;
import com.sun.istack.internal.logging.Logger;

/**
 * Main class which does a search in the elasticsearch cluster.
 * Before running search from here, we should run ESPrepareDataApp first so that the test data is available.
 * 
 * In here, first we getAllData on console and then later we get the filtered and sorted data and print on console to match.
 * 
 * @author 
 *
 */
public class ESSearchApp {

	public static void main(String args[]) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		try {
			ESSearchServiceImpl searchService = new ESSearchServiceImpl(ESUtils.getClient());
			List<SearchPage> alllist = searchService.getAllData(ESUtils.INDEX, ESUtils.TYPE);
			for (SearchPage page : alllist) {
				System.out.println(mapper.writeValueAsString(page));
			}
			System.out.println("-----------------");
			List<BaseFilter> filterList = new ArrayList<BaseFilter>();
			filterList.add(ESUtils.getScreenSizeRangeFilter(21D, 31D));

			Map<String, SortDirection> orderByProperties = new HashMap<String, SortDirection>();
			orderByProperties.put("price", SortDirection.DESC);

			List<SearchPage> list = searchService.getData(ESUtils.INDEX, ESUtils.TYPE, filterList, orderByProperties, 0,
					1000);

			for (SearchPage page : list) {
				System.out.println(mapper.writeValueAsString(page));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
