package com.example.simplespringjava.model;

import com.example.simplespringjava.config.AppConstant;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Response<T> {

    private AppConstant.COMPLETION_STATUS status;
    private String code;
    private String message;
    private T data;

}
