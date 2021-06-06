package com.glints.backend.service;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.glints.backend.constants.ApplicationConstant;
import com.glints.backend.dao.service.DaoService;
import com.glints.backend.request.UserDetailsRequest;
import com.glints.backend.response.UserDetailsResponse;

@Service
public class GlintsUserServiceImpl implements GlintsUserService {

	@Autowired
	private DaoService daoService;

	@Override
	public List<UserDetailsResponse> fetchTopUsers(UserDetailsRequest userDetailsRequest) {
		return daoService.fetchTopUsers(userDetailsRequest.getFromDate(), userDetailsRequest.getToDate(),
				userDetailsRequest.getTopUsers());

	}

	@Override
	public UserDetailsResponse fetchTopUsersByAmount(UserDetailsRequest userDetailsRequest) {
		List<UserDetailsResponse> userDetailsList = null; 
		if(ApplicationConstant.MORE.equalsIgnoreCase(userDetailsRequest.getRelation())) {
		userDetailsList = daoService.fetchTopUsersByAmountHigher(userDetailsRequest.getFromDate(),
				userDetailsRequest.getToDate(), userDetailsRequest.getAmount());
		} else if(ApplicationConstant.LESS.equalsIgnoreCase(userDetailsRequest.getRelation())) {
			userDetailsList = daoService.fetchTopUsersByAmountLower(userDetailsRequest.getFromDate(),
					userDetailsRequest.getToDate(), userDetailsRequest.getAmount());
			}
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		if (null != userDetailsList) {
			userDetailsResponse.setTotalUsers((long) userDetailsList.size());
		}
		return userDetailsResponse;
	}

}
