package com.github.catapan.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { ExistsValueValidator.class } )
@Documented
public @interface ExistsValue {

    String message() default "O valor informado não foi encontrado na base de dados";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String field();

    Class<?> entity();
    
}
