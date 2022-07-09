package com.example.simplespringjava.model;

import com.example.simplespringjava.exception.model.ApiFault;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.List;
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GeneralResponse<T> {

    @JsonIgnore
    private HttpStatus httpStatus;
    @JsonIgnore
    private HttpHeaders httpHeaders;
    private String requestAt;
    private String requestId;
    private Response<T> response;
    @JsonIgnore
    private List<ApiFault> faults;

}
