package com.corep.webapp.exceptions;

import com.corep.webapp.models.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StudentGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleStudentNotFoundException(StudentNotFoundException exception){
		StudentErrorResponse error = new StudentErrorResponse();
		error.setMessage(exception.getMessage());
		error.setCode(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleStudentGlobalException(Exception exception){
		StudentErrorResponse error = new StudentErrorResponse();
		error.setMessage(exception.getMessage());
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
