/* 
 * Copyright (C) 2010 Zenika
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
package com.zenika.wicket.contrib.test.page;

import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.zenika.wicket.contrib.test.bean.BeanObject;

/**
 * @author ophelie salm (zenika)
 * 
 */
public class ValidationListenerTestPage extends WebPage {

    private BeanObject beanObject;

    private Form<BeanObject> testForm;

    /**
     * 
     */
    public ValidationListenerTestPage() {
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
	add(testForm);

    }
}
