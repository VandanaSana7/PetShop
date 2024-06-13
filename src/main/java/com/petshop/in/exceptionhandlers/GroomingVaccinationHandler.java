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
import com.petshop.in.exceptions.groomingvaccination.GroomingServiceInvalidInputException;
import com.petshop.in.exceptions.groomingvaccination.ServiceAvailableException;
import com.petshop.in.exceptions.groomingvaccination.ServiceIdNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.ServiceUnavailableException;
import com.petshop.in.exceptions.groomingvaccination.SevicesNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationInvalidInputException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsIdNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsNotFoundException;


@ControllerAdvice
public class GroomingVaccinationHandler extends ResponseEntityExceptionHandler {
	 
		// Services Not Found Exception
		@ExceptionHandler(SevicesNotFoundException .class)
		public ResponseEntity<ErrorResponse> handleServicesNotFoundException(SevicesNotFoundException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
	
		// ServiceId Not Found Exception
		@ExceptionHandler(ServiceIdNotFoundException.class)
		public ResponseEntity<ErrorResponse> handleServiceIdNotFoundException(ServiceIdNotFoundException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		// Service Available Exception
		@ExceptionHandler(ServiceAvailableException.class)
		public ResponseEntity<ErrorResponse> handleServiceAvailableException(ServiceAvailableException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		// Service Unavailable Exception
		@ExceptionHandler(ServiceUnavailableException.class)
		public ResponseEntity<ErrorResponse> handleServiceUnavailableException(ServiceUnavailableException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		
		// Grooming Service Add Exception
		@ExceptionHandler(GroomingServiceInvalidInputException.class)
		public ResponseEntity<ErrorResponse> handleGroomingServiceAddedException (GroomingServiceInvalidInputException  exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	   }

		// Vaccinations Not Found Exception
		@ExceptionHandler(VaccinationsNotFoundException.class)
		public ResponseEntity<ErrorResponse> handleVaccinationsNotFoundException(VaccinationsNotFoundException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}


		
		// Vaccination Id Not Found Exception
		@ExceptionHandler(VaccinationsIdNotFoundException.class)
		public ResponseEntity<ErrorResponse> handleVaccinationsIdNotFoundException(VaccinationsIdNotFoundException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		
		// Vaccination Service Add Exception
		@ExceptionHandler(VaccinationInvalidInputException .class)
		public ResponseEntity<ErrorResponse> handleVaccinationServiceInvalidInputException (VaccinationInvalidInputException   exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatus(HttpStatus.NOT_FOUND.toString());
			error.setTimestamp(LocalDate.now());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	   }
		
		
//		@ExceptionHandler
//		@Order(Ordered.LOWEST_PRECEDENCE)
//			public ResponseEntity<ErrorResponse>handleGlobalException(Exception exception)
//			{
//				ErrorResponse err=new ErrorResponse();
//				err.setMessage("Validation Failed");
//				err.setStatus(HttpStatus.BAD_REQUEST.toString());
//				err.setTimestamp(LocalDate.now());
//				return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
//			}
	 
}
