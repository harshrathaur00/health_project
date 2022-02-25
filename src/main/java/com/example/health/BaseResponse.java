package com.example.health;

public class BaseResponse {
	
	private String status;
	private String message;
	private String resultCode;
	private Object data;
	
	public BaseResponse(String status, String message, String resultCode, Object data) {
		this.status = status;
		this.message = message;
		this.resultCode = resultCode;
		this.data = data;
	}
	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getResultCode() {
		return resultCode;
	}


	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "BaseResponse [status=" + status + ", message=" + message + ", resultCode=" + resultCode + ", data="
				+ data + "]";
	}
	
	
}
