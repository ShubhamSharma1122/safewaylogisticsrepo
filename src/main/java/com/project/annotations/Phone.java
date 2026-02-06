package com.project.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneConstraintValidator.class)
@Target({ FIELD, PARAMETER, LOCAL_VARIABLE })
public @interface Phone {

	String message() default "Phone number is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
