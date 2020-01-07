package com.br.curso.resource.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timesStamp) {
		super(status, msg, timesStamp);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String name, String message) {
		errors.add(new FieldMessage(name, message));
	}

	
}
