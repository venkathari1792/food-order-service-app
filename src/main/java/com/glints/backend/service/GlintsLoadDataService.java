package com.glints.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.glints.backend.request.DataLoadRequest;

public interface GlintsLoadDataService {

	public Boolean loadData(DataLoadRequest dataLoadRequest, String type)
			throws JsonMappingException, JsonProcessingException, Exception;

}
