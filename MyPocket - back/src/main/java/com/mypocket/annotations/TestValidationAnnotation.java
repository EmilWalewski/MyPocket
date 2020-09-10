package com.mypocket.annotations;

import com.mypocket.TestValidationClass;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TestValidationClass.class)
public @interface TestValidationAnnotation {

    public String message() default "{java.math.BigDecimal.range.error}";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};

}
