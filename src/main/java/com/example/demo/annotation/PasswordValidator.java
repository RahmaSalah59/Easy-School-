package com.example.demo.annotation;
import  com.example.demo.Validation.PasswordStrengthValidator;
import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.FIELD , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Please choose Strong Password";
    Class<?>[] groups() default {};
    Class<? extends Payload > [] payload() default {};
 }
