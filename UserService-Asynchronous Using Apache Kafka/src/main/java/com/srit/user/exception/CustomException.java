package com.srit.user.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomException {
	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<List<Object>> hnadlNotFound(RecordNotFoundException ex){
		 List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("Record Not Found", details);
	        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = EntityEmptyException.class)
	public ResponseEntity<List<Object>> hnadlNoContent(EntityEmptyException ex){
		 List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("Input Empty or Missing field", details);
	        return new ResponseEntity(error, HttpStatus.NO_CONTENT);
	}
}
