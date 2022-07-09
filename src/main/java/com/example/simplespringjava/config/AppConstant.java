package com.example.simplespringjava.config;

public class AppConstant {
    public static final String DATE_TIME_ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public enum STATUS {
        ok,
        failed,
        error
    }

    public enum COMPLETION_STATUS {
        SUCCESS,
        BUSINESS_ERROR,
        SYSTEM_ERROR
    }
}
