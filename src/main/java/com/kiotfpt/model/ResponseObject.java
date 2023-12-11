package com.kiotfpt.model;

public class ResponseObject {
	private boolean result;
	private String statusCode;
	private String message;
	private Object data;

	public ResponseObject() {

	}

	public ResponseObject(boolean result, String statusCode, String message, Object data) {
		super();
		this.result = result;
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
