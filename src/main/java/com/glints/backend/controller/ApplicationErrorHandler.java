package com.glints.backend.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.glints.backend.constants.ApplicationConstant;
import com.glints.backend.error.ErrorCode;
import com.glints.backend.response.ResponseDataTO;
import com.glints.backend.response.StatusTO;

@ControllerAdvice
public class ApplicationErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ Exception.class })
	protected ResponseEntity<Object> handleSystemErrors(final Exception e, final HttpServletResponse resp,
			final WebRequest request) {
		resp.setHeader(ApplicationConstant.CONTENT_DISPOSITION, null);
		final StatusTO error = new StatusTO(ErrorCode.DEFAULT_SYSTEM_ERROR.getCode(),
				ErrorCode.DEFAULT_SYSTEM_ERROR.getDescription());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(e, new ResponseDataTO<>(null, error), httpHeaders,
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
