package com.pru.fancy.services.impl;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pru.fancy.model.UploadFile200Response;
import org.springframework.web.multipart.MultipartFile;
import com.pru.fancy.services.DefaultApiServiceI;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class DefaultApiServiceImpl implements DefaultApiServiceI {

    private static final Logger logger = LoggerFactory.getLogger(DefaultApiServiceImpl.class);

    @Override
    @Retry(name = "mainService", fallbackMethod = "uploadFile_fallback")
    public UploadFile200Response uploadFile(MultipartFile file) {
        //TODO: Implement Business logic
        return null;
    }

    public UploadFile200Response uploadFile_fallback(MultipartFile file, Throwable exception) {
        logger.info("fallback method triggered with exception : {}" + exception.getMessage());
        return new UploadFile200Response();
    }
}
