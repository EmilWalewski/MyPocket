package com.mypocket;

import com.mypocket.annotations.TestValidationAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TestValidationClass implements ConstraintValidator<TestValidationAnnotation, Object> {


    @Override
    public void initialize(TestValidationAnnotation constraintAnnotation) {

        System.out.println("sdfsdf");

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate("your message");

        return false;
    }
}
