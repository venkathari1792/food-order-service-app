package com.glints.backend.request;

import java.io.Serializable;

public class DataLoadRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1_490_150_108_554_632_655L;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
