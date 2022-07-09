package com.example.simplespringjava.exception.model;

import com.example.simplespringjava.config.AppConstant;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiFault {
	
	private AppConstant.COMPLETION_STATUS status;
	private String code;
    private String type;
    private String message;
    private String detail;

}
