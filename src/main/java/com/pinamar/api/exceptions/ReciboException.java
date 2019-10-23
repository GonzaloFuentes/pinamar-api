package com.pinamar.api.exceptions;

import org.springframework.core.NestedRuntimeException;

public class ReciboException extends NestedRuntimeException {
	
	private static final long serialVersionUID = -8465748148979415960L;

	public ReciboException(String msg) {
		super(msg);
	}

}
