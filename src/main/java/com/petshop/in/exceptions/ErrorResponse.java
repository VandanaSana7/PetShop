package com.petshop.in.exceptions;

import java.time.LocalDate;

public class ErrorResponse {
	private String message;
	private String status;
	private LocalDate timestamp;
	public ErrorResponse(String message, String status, LocalDate time) {
		this.message = message;
		this.status = status;
		this.timestamp =time;
	}
	public ErrorResponse() {
	}
	public String getMessage() {
		return message;
	}
	public String getStatus() {
		return status;
	}
	public LocalDate getTimestamp() {
		return timestamp;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStatus(String notFound) {
		this.status = notFound;
	}
	public void setTimestamp(LocalDate time) {
		this.timestamp = time;
	}
	
}