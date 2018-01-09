package com.java.elasticsearch.filter;

public class RangeFilter extends BaseFilter {

	private Double before;
	private Double after;

	public Double getBefore() {
		return before;
	}
	
	public Double getAfter() {
		return after;
	}
	
	public void setBefore(Double before) {
		this.before=before;
	}
	
	public void setAfter(Double after) {
		this.after=after;
	}
	

}
