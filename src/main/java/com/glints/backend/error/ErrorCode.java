package com.glints.backend.error;

public enum ErrorCode {

	DEFAULT_SYSTEM_ERROR(9999, "System Error, Please Contact Support Team"),

	INVALID_INPUT_FORMAT(1000, "Invalid Input Format Specified"),
	
	FETCH_DATA_ERROR(1001, "Error in Data Fetch...Please check input if URL overridden or try again later");

	private final Integer code;

	private final String description;

	private ErrorCode(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
