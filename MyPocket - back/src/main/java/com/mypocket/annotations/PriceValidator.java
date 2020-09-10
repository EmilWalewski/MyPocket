package com.mypocket.annotations;

import com.mypocket.storeManagement.validators.PriceRangeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PriceRangeValidator.class)
public @interface PriceValidator {

    public String message() default "{java.math.BigDecimal.range.error}";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};

    long minPrecision() default 1;
    long maxPrecision() default 6;
    int scale() default 2;
}
