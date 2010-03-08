package com.zenika.wicket.contrib.test.page;

import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenika.wicket.contrib.jsr303validators.JSR303FormValidator;
import com.zenika.wicket.contrib.test.bean.AssociatedBeanObject;
import com.zenika.wicket.contrib.test.bean.BeanObject;

/**
 * @author ophelie salm (zenika)
 * 
 */
public class JSR303NestedFormsTestPage extends WebPage {

	private final Logger log = LoggerFactory
			.getLogger(JSR303NestedFormsTestPage.class);

	private Form<BeanObject> testForm;

	private Form<AssociatedBeanObject> nestedForm;

	private BeanObject beanObject;

	private AssociatedBeanObject assocbeanObject;

	/**
     * 
     */
	public JSR303NestedFormsTestPage() {
		beanObject = new BeanObject();
		assocbeanObject = new AssociatedBeanObject();
		beanObject.setObject(assocbeanObject);
		addForm();
	}

	/**
     * 
     */
	private void addForm() {
		testForm = new Form<BeanObject>("testForm",
				new CompoundPropertyModel<BeanObject>(beanObject));

		nestedForm = new Form<AssociatedBeanObject>("nestedForm",
				new CompoundPropertyModel<AssociatedBeanObject>(beanObject
						.getObject()));

		testForm.add(nestedForm);
		testForm.add(new TextField<String>("string"));
		testForm.add(new TextField<String>("stringNotNull"));
		testForm.add(new TextField<Date>("dateNotNull"));
		testForm.add(new TextField<Date>("datePast"));
		testForm.add(new TextField<Date>("dateFuture"));
		nestedForm.add(new TextField<String>("field"));
		testForm.add(new JSR303FormValidator());

		add(testForm);
	}

}