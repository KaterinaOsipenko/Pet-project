package com.work.authorization.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Constraint(validatedBy = EmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EmailValid {

    String message() default "Invalid email!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
