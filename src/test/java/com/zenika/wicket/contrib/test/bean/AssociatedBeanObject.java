package com.zenika.wicket.contrib.test.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class AssociatedBeanObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@FrenchZipCode
	private String field;

	/**
	 * <p>
	 * Default constructor.
	 */
	public AssociatedBeanObject() {
		// this("field");
	}

	public AssociatedBeanObject(String field) {
		this.field = field;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AssociatedBeanObject))
			return false;
		AssociatedBeanObject other = (AssociatedBeanObject) obj;
		return (field.equals(other.getField()));
	}

	/**
	 * @return field
	 */
	public String getField() {
		return field;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		return result;
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
		return "AssocBeanObject [field=" + field + "]";
	}
}