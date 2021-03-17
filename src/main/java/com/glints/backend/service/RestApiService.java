package com.glints.backend.service;

import org.springframework.core.ParameterizedTypeReference;

public interface RestApiService {

	public <T> T getApiCall(String url, Object request, ParameterizedTypeReference<T> typeRef);
	
	public <T> T postApiCall(String url, Object request, ParameterizedTypeReference<T> typeRef);
	
	
}
