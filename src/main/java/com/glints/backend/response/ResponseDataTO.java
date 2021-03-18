package com.glints.backend.response;

public class ResponseDataTO<T> implements BaseResponseTO<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4_606_935_863_081_605_928L;

	private final T response;

	private final StatusTO status;

	public ResponseDataTO(T response, StatusTO status) {
		this.response = response;
		this.status = status;
	}

	@Override
	public T getResponse() {
		return response;
	}

	@Override
	public StatusTO getStatus() {
		return status;
	}

}
