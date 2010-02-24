package com.zenika.wicket.contrib.test.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

import com.zenika.wicket.contrib.jsr303validators.JSR303FormValidator;
import com.zenika.wicket.contrib.test.bean.BeanObject;

/**
 * @author ophelie (zenika)
 * 
 */
public class JSR303FormWithoutModelTestPage extends WebPage {

    private BeanObject beanObject;

    private Form<BeanObject> testForm;

    /**
     * 
     */
    public JSR303FormWithoutModelTestPage() {

	beanObject = new BeanObject();

	addFormWithoutModel();
    }

    /**
     * 
     */
    private void addFormWithoutModel() {
	testForm = new Form<BeanObject>("testFormWithoutModel",
		new Model<BeanObject>(beanObject));
	testForm.add(new JSR303FormValidator());
	add(testForm);

    }

}
