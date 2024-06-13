package com.petshop.in.exceptionhandlers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.customer.CustomerCannotBeUpdatedException;
import com.petshop.in.exceptions.customer.CustomerCityNotFoundException;
import com.petshop.in.exceptions.customer.CustomerFirstnameLastnameNotFoundException;
import com.petshop.in.exceptions.customer.CustomerIdNotFoundException;
import com.petshop.in.exceptions.customer.CustomerNotFoundException;
import com.petshop.in.exceptions.customer.CustomerStateNotFoundException;
import com.petshop.in.exceptions.customer.CustomerTransactionCustomerIdNotFoundException;
import com.petshop.in.exceptions.customer.CustomerTransactionStatusNotFoundException;
import com.petshop.in.exceptions.customer.CustomersCannotBeAddedException;


@ControllerAdvice
public class CustomerExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerexception(CustomerIdNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(CustomerNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerCityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(CustomerCityNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerStateNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(CustomerStateNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CustomerTransactionCustomerIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(CustomerTransactionCustomerIdNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CustomerTransactionStatusNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(CustomerTransactionStatusNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerFirstnameLastnameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(CustomerFirstnameLastnameNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerCannotBeUpdatedException .class)
	public ResponseEntity<ErrorResponse> handlerexception(CustomerCannotBeUpdatedException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CustomersCannotBeAddedException.class)
	public ResponseEntity<ErrorResponse> handlerexception(CustomersCannotBeAddedException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler
//	//@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<ErrorResponse>handleException(CustomerIdNotFoundException exe)
//	{
//		 
//	ErrorResponse err=new ErrorResponse();
//	err.setMessage(exe.getMessage());
//	err.setStatus(HttpStatus.NOT_FOUND.toString());
//	err.setTimestamp(LocalDate.now());	 
//	return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
//	 
//	}
//	@ExceptionHandler
//	public ResponseEntity<ErrorResponse>handleException(Exception exe)
//	{
//
//		ErrorResponse err=new ErrorResponse();
//		err.setMessage("Validation Failed");
//		err.setStatus(HttpStatus.BAD_REQUEST.toString());
//		err.setTimestamp(LocalDate.now());
//
//		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
//	}
//	
	

}
