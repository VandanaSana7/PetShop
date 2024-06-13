package com.petshop.in.exceptionhandlers;

import java.time.LocalDate;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.Employees.EmployeeCannotBeAddedException;
import com.petshop.in.exceptions.Employees.EmployeeIdNotFoundException;
import com.petshop.in.exceptions.Employees.EmployeeNameNotFoundException;
import com.petshop.in.exceptions.Employees.EmployeeNotFoundException;
import com.petshop.in.exceptions.Employees.EmployeePositionNotFoundException;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EmployeeExceptionHandler {
	@ExceptionHandler
	//@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse>handleException(EmployeeNotFoundException exe)
	{
		 
	ErrorResponse err=new ErrorResponse();
	err.setMessage(exe.getMessage());
	err.setStatus(HttpStatus.NOT_FOUND.toString());
	err.setTimestamp(LocalDate.now());	 
	return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	 
	}

	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse>handleException(EmployeeIdNotFoundException exe)
	{
		 
	ErrorResponse err=new ErrorResponse();
	err.setMessage(exe.getMessage());
	err.setStatus(HttpStatus.NOT_FOUND.toString());
	err.setTimestamp(LocalDate.now());	 
	return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	 
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse>handleException(EmployeeNameNotFoundException exe)
	{
		 
	ErrorResponse err=new ErrorResponse();
	err.setMessage(exe.getMessage());
	err.setStatus(HttpStatus.NOT_FOUND.toString());
	err.setTimestamp(LocalDate.now());	 
	return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	 
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse>handleException(EmployeePositionNotFoundException exe)
	{
		 
	ErrorResponse err=new ErrorResponse();
	err.setMessage(exe.getMessage());
	err.setStatus(HttpStatus.NOT_FOUND.toString());
	err.setTimestamp(LocalDate.now());	 
	return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	 
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse>handleException(EmployeeCannotBeAddedException exe)
	{
		 
	ErrorResponse err=new ErrorResponse();
	err.setMessage(exe.getMessage());
	err.setStatus(HttpStatus.NOT_FOUND.toString());
	err.setTimestamp(LocalDate.now());	 
	return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	 
	}
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

}

