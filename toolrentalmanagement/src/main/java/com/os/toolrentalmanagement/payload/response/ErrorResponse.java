package com.os.toolrentalmanagement.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;

@Getter
@Setter
@Builder
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String timestamp;

	private Integer status;

	private String error;

	private String code;

	private boolean success;

	private String message;

	private String path;

	private Map<String,String> fieldErrors;

}
