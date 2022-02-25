package com.example.health;

public class BaseController {
	public BaseResponse buildResponse(String status,String message, String resultCode,Object data) {
		return new BaseResponse(status,message,resultCode,data);
	}
}
