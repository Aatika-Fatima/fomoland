package com.fomo.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fomo.dto.error.ErrorDetails;
import com.fomo.dto.error.ValidationError;
import com.fomo.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetail = new ErrorDetails();
		errorDetail.setTimestamp(new Date().getTime());
		errorDetail.setStatus(status.value());
		errorDetail.setTitle("Message Not Readable");
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
 		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimestamp(new Date().getTime());
		errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		/*String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri", 0);
		if (requestPath == null) {
			//requestPath = ((HttpServletRequest) request).getRequestURI();
			request.getContextPath();
		}*/
		errorDetails.setTitle("Validation Fatiled");
		errorDetails.setDetail("Input validation failed");
		errorDetails.setDeveloperMessage(manve.getClass().getName());

		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
		for (FieldError fe : fieldErrors) {
			List<ValidationError> validationErrorList = errorDetails.getErrors().get(fe.getField());
			if (validationErrorList == null) {
				validationErrorList = new ArrayList<ValidationError>();
				errorDetails.getErrors().put(fe.getField(), validationErrorList);
			}
			ValidationError validationError = new ValidationError();
			validationError.setCode(fe.getCode());
			validationError.setMessage(messageSource.getMessage(fe, null));
			validationErrorList.add(validationError);
		}
		return handleExceptionInternal(manve, errorDetails, headers, status, request);
	}
/*	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ErrorDetails handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimestamp(new Date().getTime());
		errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestPath == null) {
			requestPath = request.getRequestURI();
		}
		errorDetails.setTitle("Validation Fatiled");
		errorDetails.setDetail("Input validation failed");
		errorDetails.setDeveloperMessage(manve.getClass().getName());

		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
		for (FieldError fe : fieldErrors) {
			List<ValidationError> validationErrorList = errorDetails.getErrors().get(fe.getField());
			if (validationErrorList == null) {
				validationErrorList = new ArrayList<ValidationError>();
				errorDetails.getErrors().put(fe.getField(), validationErrorList);
			}
			ValidationError validationError = new ValidationError();
			validationError.setCode(fe.getCode());
			validationError.setMessage(messageSource.getMessage(fe, null));
			validationErrorList.add(validationError);
		}
		return errorDetails;
	}*/

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimestamp(new Date().getTime());
		errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetails.setTitle("Resource Not Found");
		errorDetails.setDetail(rnfe.getMessage());
		errorDetails.setDeveloperMessage(rnfe.getClass().getName());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
