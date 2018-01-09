package com.java.elasticsearch.interfaces;

import java.io.IOException;
import java.util.List;

public interface ESInsertDataService {

	/**
	 * 
	 * @param index
	 * @param type
	 * @throws IOException
	 */
	public void createIndex(String index, String type)  throws IOException;

	/**
	 * 
	 * @param index
	 * @param type
	 * @param docList
	 * @return
	 */
	public Boolean insertBulkDocuments(String index, String type, List<String> docList);

	/**
	 * 
	 * @param index
	 * @param type
	 * @param doc
	 */
	public void insertDocument(String index, String type, String doc);

}
