package com.example.demo.annotation;

import com.example.demo.Validation.FieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FieldValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValueValidator {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "Fields values don't match!";

    String field();

    String fieldMatch();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldValueValidator[] value();
    }
 }
