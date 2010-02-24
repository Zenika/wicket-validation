package com.zenika.wicket.contrib.test.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * <p>
 * Simple POJO used in tests.
 * 
 * @author ophelie (zenika)
 * 
 */
public class BeanObject extends SuperBeanObject implements Serializable {

    private static final long serialVersionUID = 1L;

    // public static final SimpleDateFormat sdf = new SimpleDateFormat(
    // "yyyy-MM-dd");

    @NotNull
    private String stringNotNull;

    @NotNull
    private Date dateNotNull;

    @Past
    private Date datePast;

    @Future
    private Date dateFuture;

    @Valid
    @NotNull
    private AssociatedBeanObject object;

    /**
     * Default Constructor.
     * <p>
     * Constructs an empty BeanObject.
     * 
     * @throws ParseException
     */
    public BeanObject() {
	// this("string", new Date(0), null, null, new AssociatedBeanObject());
    }

    /**
     * @param string
     * @param stringNotNull
     * @param date
     * @param datePast
     * @param dateFuture
     */
    public BeanObject(String stringNotNull, final Date dateNotNull,
	    final Date datePast, final Date dateFuture,
	    final AssociatedBeanObject object) {
	this.setString("string");
	this.stringNotNull = stringNotNull;
	this.dateNotNull = dateNotNull;
	this.datePast = datePast;
	this.dateFuture = dateFuture;
	this.object = object;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!(obj instanceof BeanObject))
	    return false;
	BeanObject other = (BeanObject) obj;

	return stringNotNull == null ? other.getStringNotNull() == null
		: stringNotNull.equals(other.getStringNotNull())
			&& dateFuture == null ? other.getDateFuture() == null
			: dateFuture.equals(other.getDateFuture())
				&& datePast == null ? other.getDatePast() == null
				: datePast.equals(other.getDatePast())
					&& object == null ? other.getObject() == null
					: object.equals(other.getObject())
						&& dateNotNull == null ? other
						.getDate() == null
						: dateNotNull.equals(other
							.getDate());
    }

    /**
     * @return date
     */
    public Date getDate() {
	return dateNotNull;
    }

    /**
     * @return dateFuture
     */
    public Date getDateFuture() {
	return dateFuture;
    }

    /**
     * @return datePast
     */
    public Date getDatePast() {
	return datePast;
    }

    /**
     * @return innerObj
     */
    public AssociatedBeanObject getObject() {
	return object;
    }

    /**
     * @return stringNotNull
     */
    public String getStringNotNull() {
	return stringNotNull;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((dateFuture == null) ? 0 : dateFuture.hashCode());
	result = prime * result
		+ ((dateNotNull == null) ? 0 : dateNotNull.hashCode());
	result = prime * result
		+ ((datePast == null) ? 0 : datePast.hashCode());
	result = prime * result + ((object == null) ? 0 : object.hashCode());
	result = prime * result
		+ ((stringNotNull == null) ? 0 : stringNotNull.hashCode());
	return result;
    }

    /**
     * @param date
     *            , date
     */
    public void setDate(Date date) {
	dateNotNull = date;
    }

    /**
     * @param dateFuture
     *            , dateFuture
     */
    public void setDateFuture(Date dateFuture) {
	this.dateFuture = dateFuture;
    }

    /**
     * @param datePast
     *            , datePast
     */
    public void setDatePast(Date datePast) {
	this.datePast = datePast;
    }

    /**
     * @param innerObj
     *            , innerObj
     */
    public void setObject(AssociatedBeanObject object) {
	this.object = object;
    }

    /**
     * @param stringNotNull
     *            , stringNotNull
     */
    public void setStringNotNull(String stringNotNull) {
	this.stringNotNull = stringNotNull;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("BeanObject [").append(super.toString());
	stringBuilder.append(", stringNotNull=").append(stringNotNull);
	stringBuilder.append(", date= ").append(dateNotNull);
	stringBuilder.append(", datePast=").append(datePast);
	stringBuilder.append(", dateFuture=").append(dateFuture);
	stringBuilder.append(", innerObject=").append(
		object == null ? null : object.toString());
	stringBuilder.append("]");
	return stringBuilder.toString();
    }

}
