package com.github.catapan.validator;

import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.ConstraintViolation;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ConstraintViolationImpl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Path do atributo, ex: dataInicio, autor.endereco.nome", required = false)
	private final String attribute;

	@Schema(description = "Mensagem descritiva do erro possívelmente associado ao path", required = true)
	private final String message;

	private ConstraintViolationImpl(ConstraintViolation<?> violation) {
		this.message = violation.getMessage();
		this.attribute = Stream.of(violation.getPropertyPath().toString().split("\\.")).skip(2)
				.collect(Collectors.joining("."));
	}

	public ConstraintViolationImpl(String attribute, String message) {
		this.attribute = attribute;
		this.message = message;
	}

	public static ConstraintViolationImpl of(ConstraintViolation<?> violation) {
		return new ConstraintViolationImpl(violation);
	}

	public static ConstraintViolationImpl of(String violation) {
		return new ConstraintViolationImpl(null, violation);
	}

	public String getMessage() {
		return message;
	}

	public String getTribute() {
		return attribute;
	}

}
