package com.bdma.proxy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ProxyServicesException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProxyServicesException(String message) {
		super(message);
	}
}

