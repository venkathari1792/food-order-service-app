package com.glints.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestApiServiceImpl implements RestApiService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public <T> T getApiCall(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		final HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		final HttpEntity<?> requestEntity = new HttpEntity<>(request, requestHeaders);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);
		ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, typeRef);
		return response.getBody();
	}

	@Override
	public <T> T postApiCall(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		final HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		final HttpEntity<?> requestEntity = new HttpEntity<>(request, requestHeaders);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);
		ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, typeRef);
		return response.getBody();
	}

}
