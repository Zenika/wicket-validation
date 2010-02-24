package com.zenika.wicket.contrib.test.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author ophelie (zenika)
 * 
 */
public abstract class SuperBeanObject {

    @NotNull(groups = ValidationGroup.class)
    @Size(groups = ValidationGroup.class, max = 5)
    private String string;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!(obj instanceof SuperBeanObject))
	    return false;
	SuperBeanObject other = (SuperBeanObject) obj;
	if (string == null) {
	    if (other.string != null)
		return false;
	} else if (!string.equals(other.string))
	    return false;
	return true;
    }

    /**
     * @return string
     */
    public String getString() {
	return string;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((string == null) ? 0 : string.hashCode());
	return result;
    }

    /**
     * @param string
     *            , string
     */
    public void setString(String string) {
	this.string = string;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
	return "SuperBeanObject [string=" + string + "]";
    }
}
