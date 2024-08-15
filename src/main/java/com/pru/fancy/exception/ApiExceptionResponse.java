package com.pru.fancy.exception;
@SuppressWarnings("rawtypes")
public class ApiExceptionResponse {
	
	private int code;	
	private Class dataType;
	private String message;	
	
	private ApiExceptionResponse(int code,Class dataType, String message) {
		super();
		this.code = code;
		this.dataType = dataType;
		this.message = message;
	}


	public static ApiExceptionResponse getInstance(int code, Class dataType, String message){		
		return new ApiExceptionResponse(code,dataType,message);		
	}

	public int getCode() {
		return code;
	}

	public Class getDataType() {
		return dataType;
	}

	public String getMessage() {
		return message;
	}

	
}
