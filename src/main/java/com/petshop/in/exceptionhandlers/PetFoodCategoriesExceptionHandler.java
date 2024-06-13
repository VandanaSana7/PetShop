package com.petshop.in.exceptionhandlers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierIdNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.CategoryNameNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.NoPetFoodFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodBrandNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodIdNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodNameNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodQuantityNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodTypeNotFoundException;

@ControllerAdvice
public class PetFoodCategoriesExceptionHandler {

	@ExceptionHandler(PetFoodIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(PetFoodIdNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(PetFoodNameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(PetFoodNameNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(PetFoodBrandNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(PetFoodBrandNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(PetFoodNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(PetFoodNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(PetFoodTypeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(PetFoodTypeNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(PetFoodQuantityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(PetFoodQuantityNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(NoPetFoodFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(NoPetFoodFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(CategoryNameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerexception(CategoryNameNotFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
}
