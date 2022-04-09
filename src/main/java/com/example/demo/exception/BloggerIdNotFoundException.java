package com.example.demo.exception;

public class BloggerIdNotFoundException extends RuntimeException {

	public BloggerIdNotFoundException() {
		super();
		
	}

	public BloggerIdNotFoundException(String message) {
		super(message);
		
	}

	public BloggerIdNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
