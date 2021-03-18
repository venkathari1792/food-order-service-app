package com.glints.backend.response;

import java.io.Serializable;

import com.glints.backend.constants.ApplicationConstant;

public class StatusTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7_118_018_283_677_235_209L;

	private Integer code;

	private String message;

	private String status;
		

	public StatusTO(Integer code, String message, String status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}

	public StatusTO() {
		this.code = 200;
		this.status = ApplicationConstant.SUCCESS;
	}

	public StatusTO(Integer code, String status) {
		this.code = code;
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
