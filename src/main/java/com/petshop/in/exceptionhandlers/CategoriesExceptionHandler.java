package com.petshop.in.exceptionhandlers;

  
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.categorytransaction.CategoryAddException;
import com.petshop.in.exceptions.categorytransaction.CategoryIdNotFoundException;
import com.petshop.in.exceptions.categorytransaction.CategoryNotFoundException;
 


@ControllerAdvice
public class CategoriesExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	
		//Category Id 
		@ExceptionHandler(CategoryIdNotFoundException.class)
		@ResponseStatus(HttpStatus.NOT_FOUND)
		public ResponseEntity<ErrorResponse> handleCategoryIdNotFoundException(CategoryIdNotFoundException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		
		//all
		 
			@ExceptionHandler
		   public ResponseEntity<ErrorResponse> handlerexception(CategoryNotFoundException exception) {
		        ErrorResponse error = new ErrorResponse();
		        error.setMessage(exception.getMessage());
		        error.setTimestamp(LocalDate.now());
		        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		    }

			//add exception
			@ExceptionHandler(CategoryAddException.class)
		    public ResponseEntity<ErrorResponse> handleCategoryAddException(CategoryAddException exception) {
		        ErrorResponse errorResponse = new ErrorResponse();
		        errorResponse.setTimestamp(LocalDate.now());
		        errorResponse.setMessage(exception.getMessage());
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		    }

}
