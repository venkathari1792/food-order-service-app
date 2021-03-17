package com.glints.backend.request;

import java.io.Serializable;

public class UserDetailsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4_280_506_343_904_570_901L;
	
	private String fromDate;
	
	private String toDate;
	
	private Integer topUsers;
	
	private Double amount;
	
	private String relation;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Integer getTopUsers() {
		return topUsers;
	}

	public void setTopUsers(Integer topUsers) {
		this.topUsers = topUsers;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}
