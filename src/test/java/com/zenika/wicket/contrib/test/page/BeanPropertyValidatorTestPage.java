package com.zenika.wicket.contrib.test.page;

import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.zenika.wicket.contrib.jsr303validators.BeanPropertyValidator;
import com.zenika.wicket.contrib.test.bean.BeanObject;
import com.zenika.wicket.contrib.test.bean.SuperBeanObject;

/**
 * @author ophelie salm (zenika)
 * 
 */
public class BeanPropertyValidatorTestPage extends WebPage {

	private BeanObject beanObject;

	private Form<? extends SuperBeanObject> testForm;

	public BeanPropertyValidatorTestPage() {

		beanObject = new BeanObject();
		addForm();
	}

	private void addForm() {
		testForm = new Form<BeanObject>("testForm",
				new CompoundPropertyModel<BeanObject>(beanObject));

		TextField<String> stringField = new TextField<String>("string");
		stringField.add(new BeanPropertyValidator<String>(BeanObject.class,
				"string"));

		TextField<String> stringNotNullField = new TextField<String>(
				"stringNotNull");
		stringNotNullField.add(new BeanPropertyValidator<String>(
				BeanObject.class, "stringNotNull"));

		TextField<Date> dateField = new TextField<Date>("dateNotNull");
		dateField.add(new BeanPropertyValidator<Date>(BeanObject.class,
				"dateNotNull"));

		TextField<Date> datePastField = new TextField<Date>("datePast");
		datePastField.add(new BeanPropertyValidator<Date>(BeanObject.class,
				"datePast"));

		TextField<Date> dateFutureField = new TextField<Date>("dateFuture");
		dateFutureField.add(new BeanPropertyValidator<Date>(BeanObject.class,
				"dateFuture"));

		TextField<String> objField = new TextField<String>("object.field");
		objField.add(new BeanPropertyValidator<String>(BeanObject.class,
				"object.field"));

		TextField<String> noValidationField = new TextField<String>(
				"noValidation", String.class);

		testForm.add(datePastField);
		testForm.add(dateFutureField);
		testForm.add(stringField);
		testForm.add(stringNotNullField);
		testForm.add(dateField);
		testForm.add(objField);
		testForm.add(noValidationField);
		add(testForm);

	}

}
