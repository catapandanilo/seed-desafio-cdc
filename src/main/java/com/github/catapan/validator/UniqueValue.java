package com.github.catapan.validator;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueValueValidator.class } )
@Documented
public @interface UniqueValue {

	String message() default "Já existe cadastro com o valor informado";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	String field();

	Class<?> entity();

} 
