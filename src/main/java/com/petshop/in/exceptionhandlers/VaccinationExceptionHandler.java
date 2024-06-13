package com.petshop.in.exceptionhandlers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petshop.in.exceptions.ErrorResponse;
@ControllerAdvice
public class VaccinationExceptionHandler {
//	@ExceptionHandler
//	public ResponseEntity<ErrorResponse>handleException(Exception exe)
//	{
//
////		ErrorResponse err=new ErrorResponse();
////		err.setMessage("Validation Failed");
////		err.setStatus(HttpStatus.BAD_REQUEST.toString());
////		err.setTimestamp(LocalDate.now());
//
//		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
//	}

}
