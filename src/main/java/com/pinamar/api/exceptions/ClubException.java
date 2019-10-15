package com.pinamar.api.exceptions;

import org.springframework.core.NestedRuntimeException;

public class ClubException extends NestedRuntimeException {
	
	private static final long serialVersionUID = -7320996607306500661L;

	public ClubException(String msg) {
		super(msg);
	}
}
