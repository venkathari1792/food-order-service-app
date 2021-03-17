package com.glints.backend.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UserDetailsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3_034_895_495_859_397_983L;

	@JsonInclude(value = Include.NON_NULL)
	private Double total;

	@JsonInclude(value = Include.NON_NULL)
	private Long userId;

	@JsonInclude(value = Include.NON_NULL)
	private String userName;
	
	@JsonInclude(value = Include.NON_NULL)
	private Long totalUsers;

	
	public UserDetailsResponse() {
	}

	public UserDetailsResponse(Double total, Long userId, String userName) {
		this.total = total;
		this.userId = userId;
		this.userName = userName;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}

}
