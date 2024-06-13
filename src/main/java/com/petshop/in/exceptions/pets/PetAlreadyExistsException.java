package com.petshop.in.exceptions.pets;

public class PetAlreadyExistsException extends Throwable{
	public PetAlreadyExistsException(String message) {
		super(message);
	}
	
}
