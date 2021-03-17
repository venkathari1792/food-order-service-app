package com.glints.backend.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class TransactionCountResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1_664_672_261_255_257_817L;
	
	@JsonInclude(value = Include.NON_NULL)
	private Long totalCount;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double transactionAmount;
	
	private String restaurantName;
	

	public TransactionCountResponse(Double transactionAmount, String restaurantName, String fetchType) {
		this.transactionAmount = transactionAmount;
		this.restaurantName = restaurantName;
	}

	public TransactionCountResponse(Long totalCount, String restaurantName, String fetchType) {
		this.totalCount = totalCount;
		this.restaurantName = restaurantName;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	
	

}
