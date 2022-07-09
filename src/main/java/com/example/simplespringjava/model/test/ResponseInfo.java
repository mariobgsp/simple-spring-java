package com.example.simplespringjava.model.test;

public class ResponseInfo {

    private String status;
    private String requestAt;
    private String message;
    private ResponseData data;

    public ResponseInfo(String status,String requestAt,String message,ResponseData data) {
        this.status = status;
        this.requestAt = requestAt;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestAt() {
        return requestAt;
    }

    public void setRequestAt(String requestAt) {
        this.requestAt = requestAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }
}
