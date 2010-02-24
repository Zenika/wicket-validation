package com.zenika.wicket.contrib.jsr303validators;

import java.util.Locale;

import junit.framework.TestCase;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenika.wicket.contrib.test.bean.BeanObject;
import com.zenika.wicket.contrib.test.page.JSR303FormValidatorTestPage;
import com.zenika.wicket.contrib.test.page.JSR303FormWithoutModelTestPage;
import com.zenika.wicket.contrib.test.page.JSR303GroupValidationTestPage;
import com.zenika.wicket.contrib.test.page.JSR303NestedFormsTestPage;

/**
 * <p>
 * TestCase for the JSR303FormValidator.
 * 
 * @author ophelie salm (zenika)
 * 
 */
public class JSR303FormValidatorTest extends TestCase {

    private static final Logger LOG = LoggerFactory
	    .getLogger(JSR303FormValidatorTest.class);

    private WicketTester tester;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
	Locale.setDefault(Locale.ENGLISH);
	tester = new WicketTester();
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

    @Test
    public void testGroupValidation() {
	tester.startPage(JSR303GroupValidationTestPage.class);
	tester.assertRenderedPage(JSR303GroupValidationTestPage.class);

	FormTester formTester = tester.newFormTester("testForm");
	formTester.setValue("string", "");
	formTester.submit();

	tester.assertErrorMessages(new String[] { "string may not be null" });

    }

    /**
     * If the form doesn't provide a model, a WicketRuntimeException must be
     * thrown.
     */
    @Test
    public void testValidationFieldsWithoutModel() {
	try {
	    tester.startPage(JSR303FormWithoutModelTestPage.class);
	    fail("A Wicket Runtime Exception must be caught !");
	} catch (WicketRuntimeException e) {
	    assertEquals(WicketRuntimeException.class, e.getClass());
	}
    }

    @Test
    public void testValidationWithBadValues() {

	tester.startPage(JSR303FormValidatorTestPage.class);
	tester.assertRenderedPage(JSR303FormValidatorTestPage.class);

	FormTester formTester = tester.newFormTester("testForm");

	LOG.debug("The model object on the tested form before validation = "
		+ formTester.getForm().getDefaultModelObjectAsString());

	formTester.setValue("string", ""); // no validation
	formTester.setValue("stringNotNull", "");// may not be null
	formTester.setValue("dateNotNull", ""); // may not be null
	formTester.setValue("dateFuture", "10/10/87"); // must be in the future
	formTester.setValue("datePast", "10/5/2015"); // must be in the past
	formTester.setValue("object.field", ""); // may not be null
	formTester.submit();

	LOG.debug("The model object on the tested form after validation = "
		+ formTester.getForm().getDefaultModelObjectAsString());

	// TODO i18n : récupérer les messages à partir de la resource par défaut
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
	tester.startPage(JSR303FormValidatorTestPage.class);
	tester.assertRenderedPage(JSR303FormValidatorTestPage.class);

	FormTester formTester = tester.newFormTester("testForm");

	LOG.debug("The model object on the tested form before validation = "
		+ formTester.getForm().getDefaultModelObjectAsString());

	formTester.setValue("string", "");
	formTester.setValue("stringNotNull", "string is not null");
	formTester.setValue("dateNotNull", "23/06/10");
	formTester.setValue("dateFuture", "10/10/12");
	formTester.setValue("datePast", "10/05/86");
	formTester.setValue("object.field", "75015");
	formTester.submit();

	LOG.debug("The model object on the tested form after validation = "
		+ formTester.getForm().getDefaultModelObjectAsString());

	tester.assertNoErrorMessage();
    }

    @Test
    public void testValidationWithNestedForms() {
	tester.startPage(JSR303NestedFormsTestPage.class);
	tester.assertRenderedPage(JSR303NestedFormsTestPage.class);

	FormTester formTester = tester.newFormTester("testForm");

	LOG.debug("The model object on the tested form before validation = "
		+ formTester.getForm().getDefaultModelObjectAsString());

	FormTester innerFormTester = tester
		.newFormTester("testForm:nestedForm");

	innerFormTester.setValue("field", "75015"); // may not be
	// null

	formTester.setValue("string", ""); // no validation
	formTester.setValue("stringNotNull", "string is not null");
	formTester.setValue("dateNotNull", "23/06/10"); // may not be null
	formTester.setValue("dateFuture", "10/10/12"); // must be in the future
	formTester.setValue("datePast", "10/05/86"); // must be in the past
	formTester.submit();

	LOG.debug("The model object on the tested form after validation = "
		+ formTester.getForm().getDefaultModelObjectAsString());

	tester.assertNoErrorMessage();
    }
}