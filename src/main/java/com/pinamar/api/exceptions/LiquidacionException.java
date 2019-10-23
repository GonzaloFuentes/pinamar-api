package com.pinamar.api.exceptions;

import org.springframework.core.NestedRuntimeException;

public class LiquidacionException extends NestedRuntimeException{

	private static final long serialVersionUID = -8638247666260794955L;

	public LiquidacionException(String msg) {
		super(msg);
	}

}
