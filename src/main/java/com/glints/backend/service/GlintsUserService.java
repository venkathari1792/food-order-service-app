package com.glints.backend.service;

import java.util.List;

import com.glints.backend.request.UserDetailsRequest;
import com.glints.backend.response.UserDetailsResponse;

public interface GlintsUserService {
	
	public List<UserDetailsResponse> fetchTopUsers(UserDetailsRequest userDetailsRequest);
	
	public UserDetailsResponse fetchTopUsersByAmount(UserDetailsRequest userDetailsRequest);

}
