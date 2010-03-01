/* 
 * Copyright (c) 2010 Zenika
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zenika.wicket.contrib.jsr303validators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validates constraints on a given bean property with a possible value.
 * <p>
 * This validator should be added on a <code>FormComponent</code>.
 * 
 * @see javax.validation.Validator#validateValue(Class, String, Object,
 *      Class...)
 * 
 * @author ophelie salm (zenika)
 * 
 * @param <T>
 *            the type of the property value to validate
 */
public class BeanPropertyValidator<T> implements INullAcceptingValidator<T> {

    private final Logger log = LoggerFactory
	    .getLogger(BeanPropertyValidator.class);

    private Class<?> beanClass;

    private String propertyName;

    private Class<?>[] groups;

    /**
     * Serialisation ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param beanClass
     *            the class of the object we want to validate
     * @param propertyName
     *            the name of the object's property we want to validate
     * @param groups
     *            group or list of groups targeted for validation
     */
    public BeanPropertyValidator(final Class<?> beanClass, String propertyName,
	    final Class<?>... groups) {
	this.beanClass = beanClass;
	this.propertyName = propertyName;
	this.groups = groups;
    }

    /**
     * {@inheritDoc}
     * 
     * Validates an annotated bean property, would its value be
     * <code>validatable.getValue()</code>.
     */
    public void validate(IValidatable<T> validatable) {

	T value = validatable.getValue();

	// Building the Validator Factory
	final Validator validator = Validation.buildDefaultValidatorFactory()
		.getValidator();

	// We validate the property against the value
	Set<?> violations = validator.validateValue(beanClass, propertyName,
		value, groups);

	// For each violations we set the component state to ERROR
	for (Object v : violations) {
	    ConstraintViolation<?> violation = (ConstraintViolation<?>) v;
	    validatable.error(new ValidationError().setMessage(propertyName
		    + " " + violation.getMessage()));
	    log.error("Violation = " + propertyName + " "
		    + violation.getMessage() + " - value was " + value);
	}

    }
}