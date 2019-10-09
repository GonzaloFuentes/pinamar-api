package com.pinamar.api.exceptions;

import org.springframework.core.NestedRuntimeException;

public class PlayerException extends NestedRuntimeException{

	private static final long serialVersionUID = 1856094531731924317L;
	
	public PlayerException(String msg) {
		super(msg);
	}

}
