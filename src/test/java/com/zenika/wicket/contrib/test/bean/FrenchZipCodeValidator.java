package com.zenika.wicket.contrib.test.bean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ophelie salm (zenika)
 * 
 */
public class FrenchZipCodeValidator implements
		ConstraintValidator<FrenchZipCode, String> {

	/**
	 * {@inheritDoc}
	 */
	public void initialize(FrenchZipCode constraintAnnotation) {
		// empty
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return true;
	}

}
