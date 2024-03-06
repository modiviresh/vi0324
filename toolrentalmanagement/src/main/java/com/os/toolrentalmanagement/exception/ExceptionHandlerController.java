package com.os.toolrentalmanagement.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.os.toolrentalmanagement.payload.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(ClassifiedException.class)
	ResponseEntity<ErrorResponse> handleClassifiedException(HttpServletRequest req, ClassifiedException ex){
		return ResponseEntity
				.status(ex.getError().getStatus())
				.body(ex.getErrorMessageObject(req.getRequestURL().toString()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException ex){

		Map<String,String> fieldErrorsMap = ex.getBindingResult()
												.getFieldErrors()
												.stream()
												.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
										
		ErrorResponse errorResponse = ErrorResponse.builder()
													.message("Validation error")
													.fieldErrors(fieldErrorsMap)
													.status(ex.getStatusCode().value())
													.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
													.success(Boolean.FALSE)
													.code(String.valueOf(ex.getStatusCode().value()))
													.timestamp(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()))
													.path(req.getRequestURI())
													.build();
		return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
	}
}
