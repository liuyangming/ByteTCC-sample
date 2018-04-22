package com.bytesvc.consumer.basic;

public class RestResult<T> {

	private boolean success = true;
	private T result;
	private String message;

	public RestResult() {
	}

	public RestResult(T result) {
		this.result = result;
	}

	public RestResult(boolean success, String message) {
		this(success, null, message);
	}

	public RestResult(boolean success, T result, String message) {
		this.success = success;
		this.result = result;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
