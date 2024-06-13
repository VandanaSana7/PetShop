package com.petshop.in.exceptions;

public class ResourceNotFoundException extends Throwable{
	public ResourceNotFoundException(String message) {
		super(message);
	}
	public ResourceNotFoundException(String message,Throwable e) {
		super(message,e);
	}
	public ResourceNotFoundException(String message,Throwable e,boolean enablesupression,boolean enablestacktrace) {
		super( message,e,enablesupression,enablestacktrace);
	}
}
