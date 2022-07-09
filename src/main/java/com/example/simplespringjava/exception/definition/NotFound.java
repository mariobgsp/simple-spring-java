package com.example.simplespringjava.exception.definition;

import com.example.simplespringjava.config.AppConstant;
import org.springframework.http.HttpStatus;

public class NotFound extends CommonException{

    public static final String CODE = "04";
    public static final String TYPE = "NotFoundException";

    public NotFound(String message) {
        super(AppConstant.COMPLETION_STATUS.BUSINESS_ERROR,HttpStatus.NOT_FOUND,CODE,TYPE,message);
    }
}
