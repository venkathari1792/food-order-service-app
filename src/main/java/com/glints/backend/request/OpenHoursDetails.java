package com.glints.backend.request;

import java.io.Serializable;

public class OpenHoursDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2_928_799_121_724_909_206L;
	
	private Long openTime;
	
	private Long closeTime;

	public Long getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Long openTime) {
		this.openTime = openTime;
	}

	public Long getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Long closeTime) {
		this.closeTime = closeTime;
	}

}
