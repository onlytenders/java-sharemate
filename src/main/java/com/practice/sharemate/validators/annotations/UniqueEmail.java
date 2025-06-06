package com.practice.sharemate.validators.annotations;

import com.practice.sharemate.validators.validation.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

    String message() default "Пользователь с такой почтой уже существует";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
