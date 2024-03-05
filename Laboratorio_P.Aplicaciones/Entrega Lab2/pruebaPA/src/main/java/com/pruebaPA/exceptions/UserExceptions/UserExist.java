package com.pruebaPA.exceptions.UserExceptions;

public class UserExist extends Exception {
	private static final long serialVersionUID = 1L;
	public UserExist() {}
	
	public UserExist(String msg) {
		super(msg);
	}
}
