package com.example.simplespringjava.exception.definition;

import com.example.simplespringjava.config.AppConstant;
import com.example.simplespringjava.exception.model.ApiFault;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class CommonException extends Exception{

    private AppConstant.COMPLETION_STATUS status;
    private HttpStatus httpStatus;
    private String code;
    private String type;
    private String displayMessage;

    public AppConstant.COMPLETION_STATUS getStatus() {
        return status;
    }

    public void setStatus(AppConstant.COMPLETION_STATUS status) {
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public CommonException(AppConstant.COMPLETION_STATUS status,HttpStatus httpStatus,String code,String type,String displayMessage) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.code = code;
        this.type = type;
        this.displayMessage = displayMessage;
    }

    public CommonException(Exception e) {
        super(e.getMessage());
        this.status = AppConstant.COMPLETION_STATUS.SYSTEM_ERROR;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = "99";
        this.type = e.getClass().getSimpleName();
        this.displayMessage = "Unknown Error: " + e.getClass().getSimpleName();
    }

    public ApiFault getApiFault() {
        return ApiFault.builder()
                .status(status)
                .code(code)
                .type(type)
                .message(displayMessage)
                .detail(type + ":" + super.getMessage())
                .build();
    }
}
