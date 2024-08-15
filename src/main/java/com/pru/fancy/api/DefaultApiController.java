package com.pru.fancy.api;

import com.pru.fancy.model.UploadFile200Response;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import com.pru.fancy.services.DefaultApiServiceI;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class DefaultApiController implements DefaultApi {

	@Autowired
	DefaultApiServiceI defaultApiServiceI;


public ResponseEntity<UploadFile200Response> uploadFile(@Parameter(name = "file", description = "The file to be uploaded.") @RequestPart(value = "file", required = false) MultipartFile file){
        UploadFile200Response res  = defaultApiServiceI.uploadFile(file);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    

}
