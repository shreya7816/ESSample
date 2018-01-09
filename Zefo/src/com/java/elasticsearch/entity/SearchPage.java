package com.java.elasticsearch.entity;

import java.util.Date;

import lombok.Data;

public class SearchPage {

	private Double screenSize;
	private String condition;
	private String screenType;
	private String brandName;
	private Double price;
	private String feature;
	
	private Double popularity;
	private Long arrivalDate;
	private Double discount;
	public Double getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(Double screenSize) {
		this.screenSize = screenSize;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getScreenType() {
		return screenType;
	}
	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public Double getPopularity() {
		return popularity;
	}
	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}
	public Long getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Long arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	

}
