package com.example.health;

public enum ApiResponseEnum {
	
	SUCCESS("success","default" ,"200");
	//Apointment
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String status;
	private String message;
	private String resultCode;
	
	ApiResponseEnum(String status,String message,String resultcode){
		this.status=status;
		this.message=message;
		this.resultCode=resultCode;
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
	
}
