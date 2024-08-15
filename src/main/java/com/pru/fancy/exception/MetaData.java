package com.pru.fancy.exception;

import java.util.HashMap;
import java.util.Map;

import com.pru.fancy.exception.ApiExceptionResponse;
import com.pru.fancy.exception.ErrorStatusCode;
import com.pru.fancy.model.*;
public class MetaData {

	public static final Map<String,Map<ErrorStatusCode, ApiExceptionResponse>> apiExceptions;

        private static String BAD_REQUEST_NO_FILE_WAS_UPLOADED_OR_AN_ERROR_OCCURRED= "Bad request. No file was uploaded or an error occurred.";

	private MetaData() {
        throw new AssertionError();
    }
	
	static {		
		apiExceptions = new HashMap<String,Map<ErrorStatusCode, ApiExceptionResponse>>();

		Map<ErrorStatusCode, ApiExceptionResponse> responses; 
		try {
		
		 responses = new HashMap<ErrorStatusCode, ApiExceptionResponse>();
		 responses.put(ErrorStatusCode.getStatusCode(400),ApiExceptionResponse.getInstance(400,Void.class, BAD_REQUEST_NO_FILE_WAS_UPLOADED_OR_AN_ERROR_OCCURRED ));
		 
		 apiExceptions.put("uploadFile",responses);
		 
		
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
