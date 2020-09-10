package com.mypocket.storeManagement.validators;

import com.mypocket.annotations.PriceValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PriceRangeValidator implements ConstraintValidator<PriceValidator, Object> {


    private long maxPrecision;
    private long minPrecision;
    private int scale;

    @Override
    public void initialize(PriceValidator constraintAnnotation) {
        maxPrecision = constraintAnnotation.maxPrecision();
        minPrecision = constraintAnnotation.minPrecision();
        scale = constraintAnnotation.scale();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext cvc) {

        boolean isValid;

        if (o == null) isValid = false;
        else{
            BigDecimal bigDecimal = new BigDecimal(o.toString());
            int actualPrecision = bigDecimal.precision();
            int actualScale = bigDecimal.scale();
            isValid = actualPrecision >= minPrecision && actualPrecision <= maxPrecision && actualScale <= scale;

            if (!isValid) {
                cvc.disableDefaultConstraintViolation();
                cvc.buildConstraintViolationWithTemplate("Precision expected (minimun : " + minPrecision + ", maximum : " + maxPrecision + "). Maximum scale expected : " + scale + ". Found precision : " + actualPrecision + ", scale : " + actualScale).addConstraintViolation();
            }
        }

        return isValid;
    }
}
