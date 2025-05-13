package com.vpp.powerplant.Exception;

public class NoRequestFoundException  extends RuntimeException {
	
	public NoRequestFoundException(String message) {
		super(message);
	}
	
	public NoRequestFoundException(String message, Throwable ex) {
		super (message,ex);
	}

}
