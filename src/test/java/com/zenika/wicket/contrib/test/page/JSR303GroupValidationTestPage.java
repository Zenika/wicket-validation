package com.zenika.wicket.contrib.test.page;

import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.zenika.wicket.contrib.jsr303validators.JSR303FormValidator;
import com.zenika.wicket.contrib.test.bean.BeanObject;
import com.zenika.wicket.contrib.test.bean.ValidationGroup;

/**
 * @author ophelie salm (zenika)
 * 
 */
public class JSR303GroupValidationTestPage extends WebPage {

    private Form<BeanObject> testForm;
    private BeanObject beanObject;

    /**
     * 
     */
    public JSR303GroupValidationTestPage() {
	beanObject = new BeanObject();
	addForm();
    }

    /**
     * 
     */
    private void addForm() {
	testForm = new Form<BeanObject>("testForm",
		new CompoundPropertyModel<BeanObject>(beanObject));

	testForm.add(new TextField<String>("string"));
	testForm.add(new TextField<String>("stringNotNull"));
	testForm.add(new TextField<Date>("dateNotNull"));
	testForm.add(new TextField<Date>("datePast"));
	testForm.add(new TextField<Date>("dateFuture"));
	testForm.add(new TextField<String>("object.field"));

	testForm.add(new JSR303FormValidator(ValidationGroup.class));

	add(testForm);
    }
}
