package com.zenika.wicket.contrib.test.bean;

import javax.validation.constraints.NotNull;

/**
 * @author ophelie (zenika)
 * 
 */
public class BeanObjectBis extends SuperBeanObject {

    @NotNull
    private String field;

    /**
     * 
     */
    public BeanObjectBis() {
    }

    /**
     * @return field
     */
    public String getField() {
	return field;
    }

    /**
     * @param field
     *            , field
     */
    public void setField(String field) {
	this.field = field;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
	return "BeanObjectBis [field=" + field + "]";
    }

}
