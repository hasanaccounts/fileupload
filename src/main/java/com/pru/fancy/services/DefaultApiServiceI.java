package com.pru.fancy.services;

import com.pru.fancy.model.UploadFile200Response;
import org.springframework.web.multipart.MultipartFile;

public interface DefaultApiServiceI {

    UploadFile200Response uploadFile(MultipartFile file);


}
