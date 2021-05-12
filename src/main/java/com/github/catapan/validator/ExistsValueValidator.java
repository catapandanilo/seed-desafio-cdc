package com.github.catapan.validator;

import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsValueValidator implements ConstraintValidator<ExistsValue, Object> {

    private String domainAttribute;
    private Class<?> domainClass;

    @Inject
    private EntityManager manager;

    @Override
    public void initialize(ExistsValue constraintAnnotation) {
        this.domainAttribute = constraintAnnotation.field();
        this.domainClass = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager
                .createQuery("SELECT 1 FROM " + domainClass.getName() + " WHERE " + domainAttribute + " = :value");
        query.setParameter("value", value);
        List<?> result = query.getResultList();

        Assert.state(result.size() <= 1, String.format("Foi encontrado mais de um %s com o atributo %s = %s",
                domainClass.getSimpleName(), domainAttribute, value));

        return !result.isEmpty();
    }

} 
