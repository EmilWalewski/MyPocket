package com.mypocket.storeManagement.validators;

import org.springframework.validation.Errors;

public class ValidationBuilder {

    public static Validation buildErrorMessage(Errors errors){

        Validation validation = new Validation();

        errors.getAllErrors().forEach(error -> validation.addMessage(error.getDefaultMessage()));

        return validation;
    }
}
