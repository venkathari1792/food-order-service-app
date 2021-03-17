package com.glints.backend.request;

import java.io.Serializable;

public class RestaurantsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7_462_785_966_397_092_117L; 
	
	private String inputDate;
	
	private String hours;
	
	private String duration;
	
	private String relation;
	
	private Double fromPrice;
	
	private Double toPrice;
	
	private Integer noOfDishes;

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Double getFromPrice() {
		return fromPrice;
	}

	public void setFromPrice(Double fromPrice) {
		this.fromPrice = fromPrice;
	}

	public Double getToPrice() {
		return toPrice;
	}

	public void setToPrice(Double toPrice) {
		this.toPrice = toPrice;
	}

	public Integer getNoOfDishes() {
		return noOfDishes;
	}

	public void setNoOfDishes(Integer noOfDishes) {
		this.noOfDishes = noOfDishes;
	}

}
