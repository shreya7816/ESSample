package com.java.elasticsearch.app;

import java.util.logging.Level;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.elasticsearch.dao.ESInsertDataServiceImpl;
import com.java.elasticsearch.util.ESUtils;
import com.sun.istack.internal.logging.Logger;

/**
 * Main class which creates index and writes data into ES
 * It takes utility methods from ESUtils class to prepare sample instances of SearchPage and inserts into ES
 * @author 
 *
 */
public class ESPrepareDataApp {
	
	public static void main(String args[]) throws Exception {

		try {
		ESInsertDataServiceImpl insertDataService = new ESInsertDataServiceImpl(ESUtils.getClient());
		ObjectMapper objectMapper = new ObjectMapper();
		insertDataService.createIndex(ESUtils.INDEX, ESUtils.TYPE);
		for (int i = 1; i < 10; i++) {
			insertDataService.insertDocument(ESUtils.INDEX, ESUtils.TYPE,
					objectMapper.writeValueAsString(ESUtils.getSearchPageInstance(i)));
		}
	}catch(Exception e) {
		throw e;
	}
	}
}
