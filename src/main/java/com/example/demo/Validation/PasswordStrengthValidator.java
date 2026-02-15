package com.example.demo.Validation;

import com.example.demo.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator,String> {
    List<String> weakpassword;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakpassword = Arrays.asList("12345","password");
    }

    @Override
    public boolean isValid(String PasswordFiled, ConstraintValidatorContext constraintValidatorContext) {
        return PasswordFiled != null && (!weakpassword.contains(PasswordFiled));
    }
}
