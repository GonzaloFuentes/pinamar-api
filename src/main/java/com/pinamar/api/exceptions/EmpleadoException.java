package com.pinamar.api.exceptions;

import org.springframework.core.NestedRuntimeException;

public class EmpleadoException extends NestedRuntimeException{

	private static final long serialVersionUID = -8472943040097377979L;

	public EmpleadoException(String msg) {
		super(msg);
	}

}