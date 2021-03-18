package com.glints.backend.response;

import java.io.Serializable;

public interface BaseResponseTO<T> extends Serializable {
	
	public T getResponse();
	
	public StatusTO getStatus();
}
