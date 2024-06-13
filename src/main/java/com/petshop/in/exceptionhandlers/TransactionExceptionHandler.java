package com.petshop.in.exceptionhandlers;

import java.time.LocalDate;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.categorytransaction.CustomerIdNotFoundException;
import com.petshop.in.exceptions.categorytransaction.FailedTransNotFoundException;
import com.petshop.in.exceptions.categorytransaction.SuccessTransNotFoundException;
import com.petshop.in.exceptions.categorytransaction.TransactionsIdNotFoundException;
import com.petshop.in.exceptions.categorytransaction.TransactionsNotFoundException;


@ControllerAdvice
public class TransactionExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(TransactionsNotFoundException exception) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.toString());
		error.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	//for id
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(TransactionsIdNotFoundException exception) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.toString());
		error.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	//for id
		@ExceptionHandler
		public ResponseEntity<ErrorResponse> handlerException(CustomerIdNotFoundException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		//success
		@ExceptionHandler
		public ResponseEntity<ErrorResponse> handlerException(SuccessTransNotFoundException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		

		@ExceptionHandler
		public ResponseEntity<ErrorResponse> handlerException(FailedTransNotFoundException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		
//		@ExceptionHandler
//		@Order(Ordered.LOWEST_PRECEDENCE)
//		public ResponseEntity<ErrorResponse> handlerException(Exception exe)
//		{
//	
//			ErrorResponse err=new ErrorResponse();
//			err.setMessage("Validation Failed");
//			err.setStatus(HttpStatus.BAD_REQUEST.toString());
//			err.setTimestamp(LocalDate.now());
//	
//			return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
//		}
}

