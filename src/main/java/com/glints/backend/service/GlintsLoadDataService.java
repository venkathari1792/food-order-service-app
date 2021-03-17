package com.glints.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.glints.backend.request.DataLoadRequest;
import com.glints.backend.response.DataLoadResponse;

public interface GlintsLoadDataService {

	public DataLoadResponse loadData(DataLoadRequest dataLoadRequest, String type)
			throws JsonMappingException, JsonProcessingException;

}
