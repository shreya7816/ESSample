package com.java.elasticsearch.dao;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;

import com.java.elasticsearch.interfaces.ESInsertDataService;

/**
 * 
 * Service for creating index and writing documents in elasticsearch.
 * 
 * @author 
 *
 */
public class ESInsertDataServiceImpl implements ESInsertDataService{

	private Client client;

	public ESInsertDataServiceImpl(Client client) {
		this.client = client;
	}
	
	@Override
	public void createIndex(String index, String type) throws IOException {
		XContentBuilder xcb = XContentFactory.jsonBuilder().startObject().startObject("properties")
				.startObject("screenSize").field("type", "number").endObject().startObject("condition")
				.field("type", "text").endObject().startObject("screen").field("type", "text").endObject()
				.startObject("brand").field("type", "text").endObject().startObject("price").field("type", "number")
				.endObject().startObject("feature").field("type", "text").endObject().startObject("popularity")
				.field("type", "number").endObject().startObject("date").field("type", "number").endObject()
				.startObject("discount").field("type", "number").endObject().

				endObject().endObject();
		
		IndexRequest indexRequest = new IndexRequest(index, type);
		indexRequest.source(xcb);
		client.index(indexRequest).actionGet();
	}
	
	@Override
	public Boolean insertBulkDocuments(String index, String type, List<String> docList) {
		BulkRequestBuilder request = client.prepareBulk();
		docList.forEach(doc -> request.add(client.prepareIndex(index, type).setSource(doc,XContentType.JSON)));
		return request.get().hasFailures();
	}

	@Override
	public void insertDocument(String index, String type, String doc) {
		client.prepareIndex(index, type).setSource(doc, XContentType.JSON).get();
	}
}
