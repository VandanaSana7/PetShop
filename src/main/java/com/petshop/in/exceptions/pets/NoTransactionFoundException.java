package com.petshop.in.exceptions.pets;

public class NoTransactionFoundException extends Throwable{
	public NoTransactionFoundException(String message) {
		super(message);
	}
	
}
