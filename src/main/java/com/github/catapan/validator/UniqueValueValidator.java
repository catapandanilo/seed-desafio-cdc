package com.github.catapan.validator;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

	String domainAttribute;
	Class<?> domainClass;

	@Inject
	EntityManager manager;

	@Override
	public void initialize(UniqueValue params) {
		this.domainAttribute = params.field();
		this.domainClass = params.entity();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager
				.createQuery("SELECT 1 FROM " + domainClass.getName() + " WHERE " + domainAttribute + " = :value");
		query.setParameter("value", value);
		List<?> result = query.getResultList();

		Assert.state(result.size() <= 1, String.format("Foi encontrado mais de um %s com o atributo %s = %s",
				domainClass.getSimpleName(), domainAttribute, value));

		return result.isEmpty();
	}

}