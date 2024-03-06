package com.os.toolrentalmanagement.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.os.toolrentalmanagement.component.MessageTranslator;
import com.os.toolrentalmanagement.payload.response.ErrorResponse;

import lombok.Getter;

@Getter
public class ClassifiedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private final BusinessErrorCodes error;

	public ClassifiedException(BusinessErrorCodes classifiedError) {
		super(MessageTranslator.getMessage(classifiedError.getErrorMsg()));
		this.error = classifiedError;
	}

	public ClassifiedException(BusinessErrorCodes classifiedError, String errorMessageTemplate) {
		super(errorMessageTemplate);
		this.error = classifiedError;
	}

	public ErrorResponse getErrorMessageObject(String path) {
		return ErrorResponse.builder()
							.message(this.getMessage())
							.status(getError().getStatus())
							.error(getError().getErrorMsg())
							.code(this.getMessage())
							.timestamp(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()))
							.path(path)
							.build();
	}
}
