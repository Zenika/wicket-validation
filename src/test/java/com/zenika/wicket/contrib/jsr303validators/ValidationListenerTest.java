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
package com.zenika.wicket.contrib.jsr303validators;

import java.util.Locale;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenika.wicket.contrib.test.bean.BeanObject;
import com.zenika.wicket.contrib.test.page.ValidationListenerFormWithoutModelTestPage;
import com.zenika.wicket.contrib.test.page.ValidationListenerTestPage;

/**
 * @author ophelie salm (zenika)
 * 
 */
public class ValidationListenerTest extends TestCase {

    private WicketTester tester;

    private final transient Logger log = LoggerFactory
	    .getLogger(ValidationListenerTest.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
	Locale.setDefault(Locale.ENGLISH);
	tester = new WicketTester();
	tester.getApplication().getComponentPostOnBeforeRenderListeners().add(
		new JSR303ValidationListener());
	super.setUp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
	tester.destroy();
	super.tearDown();
    }

    /**
     * Nothing should happen
     */
    @Test
    public void testValidationOnFormWithoutModel() {
	tester.startPage(ValidationListenerFormWithoutModelTestPage.class);
	tester.assertRenderedPage(ValidationListenerFormWithoutModelTestPage.class);

	FormTester formTester = tester.newFormTester("testFormWithoutModel");
	formTester.submit();

	tester.assertNoErrorMessage();

    }

    @Test
    public void testValidationWithBadValues() {
	tester.startPage(ValidationListenerTestPage.class);
	tester.assertRenderedPage(ValidationListenerTestPage.class);
	FormTester formTester = tester.newFormTester("testForm");

	log.debug("The model object on the tested form before validation = "
		+ formTester.getForm().getDefaultModelObjectAsString());

	formTester.setValue("string", ""); // no validation
	formTester.setValue("stringNotNull", "");// may not be null
	formTester.setValue("dateNotNull", ""); // may not be null
	formTester.setValue("dateFuture", "10/10/87"); // must be in the future
	formTester.setValue("datePast", "10/5/2015"); // must be in the past
	formTester.setValue("object.field", ""); // may not be null
	formTester.submit();

	log.debug("The model object on the tested form after validation = "
		+ formTester.getForm().getDefaultModelObjectAsString());

	tester.assertErrorMessages(new String[] {
		"datePast must be in the past",
		"dateFuture must be in the future",
		"dateNotNull may not be null", "stringNotNull may not be null",
		"object.field may not be null" });

	// The resulting model object shouldn't have changed
	BeanObject beanObject = new BeanObject();
	assertEquals(beanObject, formTester.getForm().getDefaultModelObject());
    }

    @Test
    public void testValidationWithGoodValues() {
	tester.startPage(ValidationListenerTestPage.class);
	tester.assertRenderedPage(ValidationListenerTestPage.class);

	FormTester formTester = tester.newFormTester("testForm");

	log.debug("The model object on the tested form before validation = "
		+ formTester.getForm().getDefaultModelObjectAsString());

	formTester.setValue("string", "");
	formTester.setValue("stringNotNull", "string is not null");
	formTester.setValue("dateNotNull", "23/06/10");
	formTester.setValue("dateFuture", "10/10/12");
	formTester.setValue("datePast", "10/05/86");
	formTester.setValue("object.field", "75015");
	formTester.submit();

	log.debug("The model object on the tested form after validation = "
		+ formTester.getForm().getDefaultModelObjectAsString());

	tester.assertNoErrorMessage();
    }
}
