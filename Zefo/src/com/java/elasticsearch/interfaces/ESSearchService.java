package com.java.elasticsearch.interfaces;

import java.util.List;
import java.util.Map;

import com.java.elasticsearch.entity.SearchPage;
import com.java.elasticsearch.entity.SortDirection;
import com.java.elasticsearch.exception.ESSearchServiceException;
import com.java.elasticsearch.filter.BaseFilter;

public interface ESSearchService {

	/**
	 * 
	 * @param index
	 * @param type
	 * @param filterList
	 * @param orderByProperties
	 * @param startIndex
	 * @param batchSize
	 * @return
	 * @throws ESSearchServiceException
	 */
	public List<SearchPage> getData(String index, String type, List<BaseFilter> filterList,
			Map<String, SortDirection> orderByProperties, Integer startIndex, Integer batchSize)
			throws ESSearchServiceException;
	
	/**
	 * 
	 * @param index
	 * @param type
	 * @return
	 * @throws ESSearchServiceException
	 */
	public List<SearchPage> getAllData(String index, String type) throws ESSearchServiceException;

}
