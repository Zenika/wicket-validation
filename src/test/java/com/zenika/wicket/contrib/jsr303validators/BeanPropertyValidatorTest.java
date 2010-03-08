package com.zenika.wicket.contrib.jsr303validators;

import java.util.Locale;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenika.wicket.contrib.test.page.BeanPropertyValidatorTestPage;

/**
 * <p>
 * TestCase for the BeanPropertyValidator.
 * 
 * @author ophelie salm (zenika)
 * 
 */
public class BeanPropertyValidatorTest extends TestCase {

	private final Logger log = LoggerFactory
			.getLogger(BeanPropertyValidatorTest.class);

	private WicketTester tester;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setUp() throws Exception {
		Locale.setDefault(Locale.ENGLISH);

		tester = new WicketTester();
		tester.startPage(BeanPropertyValidatorTestPage.class);
		tester.assertRenderedPage(BeanPropertyValidatorTestPage.class);

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

	public void testFieldsValidationWithGoodValues() {
		FormTester formTester = tester.newFormTester("testForm");

		formTester.setValue("string", "string");
		formTester.setValue("stringNotNull", "the string is not null");
		formTester.setValue("dateNotNull", "23/12/10");
		formTester.setValue("dateFuture", "10/10/20");
		formTester.setValue("datePast", "10/10/86");
		formTester.setValue("object.field", "75015");
		formTester.setValue("noValidation", "something");
		formTester.submit();
		tester.assertRenderedPage(BeanPropertyValidatorTestPage.class);
		tester.assertNoErrorMessage();
	}

	public void testFieldsWithBadValues() {
		FormTester formTester = tester.newFormTester("testForm");
		formTester.setValue("string", ""); // no validation
		formTester.setValue("stringNotNull", ""); // may not be null
		formTester.setValue("dateNotNull", ""); // may not be null
		formTester.setValue("dateFuture", "10/10/1987"); // must be in the
		// future
		formTester.setValue("datePast", "10/05/2015"); // must be in the past
		formTester.setValue("object.field", ""); // may not be null
		formTester.submit();

		tester.assertErrorMessages(new String[] {
				"datePast must be in the past",
				"dateFuture must be in the future",
				"stringNotNull may not be null", "dateNotNull may not be null",
				"object.field may not be null" });

	}
}