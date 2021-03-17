package com.glints.backend.response;

import java.io.Serializable;

public class DataLoadResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3_817_622_349_209_536_107L;

	private String status;
	
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
