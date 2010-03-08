package com.zenika.wicket.contrib.test.page;

import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.zenika.wicket.contrib.jsr303validators.JSR303FormValidator;
import com.zenika.wicket.contrib.test.bean.BeanObject;

/**
 * @author ophelie salm (zenika)
 * 
 */
public class JSR303FormValidatorTestPage extends WebPage {

	private BeanObject beanObject;

	private Form<BeanObject> testForm;

	public JSR303FormValidatorTestPage() {

		beanObject = new BeanObject();

		addFormWithModel();
	}

	public void addFormWithModel() {
		testForm = new Form<BeanObject>("testForm",
				new CompoundPropertyModel<BeanObject>(beanObject));

		TextField<String> stringField = new TextField<String>("string");

		TextField<String> stringNotNullField = new TextField<String>(
				"stringNotNull");
		TextField<Date> dateField = new TextField<Date>("dateNotNull");
		TextField<Date> datePastField = new TextField<Date>("datePast");
		TextField<Date> dateFutureField = new TextField<Date>("dateFuture");
		TextField<String> objField = new TextField<String>("object.field");

		testForm.add(datePastField);
		testForm.add(dateFutureField);
		testForm.add(stringField);
		testForm.add(stringNotNullField);
		testForm.add(dateField);
		testForm.add(objField);
		testForm.add(new JSR303FormValidator());
		add(testForm);

	}

	/**
	 * @return beanObject
	 */
	public BeanObject getBeanObject() {
		return beanObject;
	}

	/**
	 * @param beanObject
	 *            , beanObject
	 */
	public void setBeanObject(BeanObject beanObject) {
		this.beanObject = beanObject;
	}
}
