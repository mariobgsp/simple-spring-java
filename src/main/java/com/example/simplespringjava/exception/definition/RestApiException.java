package com.example.simplespringjava.exception.definition;

import com.example.simplespringjava.config.AppConstant;
import org.springframework.http.HttpStatus;

public class RestApiException extends CommonException{

    public static final String TYPE = "BoredApiException";

    public RestApiException(AppConstant.COMPLETION_STATUS status,HttpStatus httpStatus,String code,String displayMessage) {
        super(status,httpStatus,code,TYPE,displayMessage);
    }
}
