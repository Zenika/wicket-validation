package com.zenika.wicket.contrib.test.bean;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author ophelie salm (zenika)
 * 
 */
@Documented
@Constraint(validatedBy = { FrenchZipCodeValidator.class })
@Target( { METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Pattern(regexp = "[0-9]*")
@Size(min = 5, max = 5, message = "Zipcode should be of size {max}")
public @interface FrenchZipCode {

    Class<?>[] groups() default {};

    String message() default "is not a valid french zipcode";

    Class<? extends Payload>[] payload() default {};
}
