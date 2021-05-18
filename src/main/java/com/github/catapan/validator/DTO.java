package com.github.catapan.validator;

import javax.validation.ConstraintValidatorContext;

public interface DTO {

	default boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
		return true;
	}
}
