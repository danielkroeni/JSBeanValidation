package ch.fhnw.imvs.jsbeanvalidation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = JSConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSConstraint {
    String value();
    String var() default "i";
    String message() default "Value not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
