package com.glints.backend.request;

import java.io.Serializable;

public class TransactionDetailsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3_021_114_086_563_789_683L;
	
	private String fetchType;

	public String getFetchType() {
		return fetchType;
	}

	public void setFetchType(String fetchType) {
		this.fetchType = fetchType;
	}

}
