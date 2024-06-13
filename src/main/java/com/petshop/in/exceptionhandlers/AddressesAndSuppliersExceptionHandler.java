package com.petshop.in.exceptionhandlers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressIdNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressInputInvalidException;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierCityNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierIdNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierInputInvalidException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierNameNotFoundException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierNotFoundException;

@ControllerAdvice
public class AddressesAndSuppliersExceptionHandler {
	@ExceptionHandler(SupplierIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerException(SupplierIdNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(SupplierNameNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(SupplierCityNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(SupplierNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(AddressIdNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(AddressNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(SupplierInputInvalidException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(AddressInputInvalidException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(MismatchDataTypeException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}

	
	

	
}
