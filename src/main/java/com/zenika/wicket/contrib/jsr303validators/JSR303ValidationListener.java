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

import javax.validation.Constraint;

import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentOnBeforeRenderListener;
import org.apache.wicket.markup.html.form.AbstractTextComponent;
import org.apache.wicket.model.AbstractPropertyModel;

/**
 * <p>
 * Listener that adds a {@link BeanPropertyValidator} on
 * {@link org.apache.wicket.markup.html.form.AbstractTextComponent
 * AbstractTextFormComponents} right after the {@link Component#onAfterRender()}
 * has been invoked.
 * 
 * <p>
 * Usage in {@link org.apache.wicket.protocol.http.WebApplication
 * WebApplication} overrides :
 * 
 * <pre>
 * addPostComponentOnBeforeRenderListener(new JSR303ValidationListener());
 * 
 * @see org.apache.wicket.application.IComponentOnAfterRenderListener
 * 
 * @author ophelie salm (zenika)
 * 
 */
public class JSR303ValidationListener implements
		IComponentOnBeforeRenderListener {

	/**
	 * {@inheritDoc}
	 */
	public void onBeforeRender(Component component) {
		if ((component instanceof AbstractTextComponent<?>)
				&& !component.hasBeenRendered()) {
			processComponent((AbstractTextComponent<?>) component);
		}
	}

	/**
	 * @param component
	 */
	@SuppressWarnings("unchecked")
	private void processComponent(AbstractTextComponent<?> component) {

		if (component.getModel() instanceof AbstractPropertyModel<?>) {
			AbstractPropertyModel<?> model = (AbstractPropertyModel<?>) component
					.getModel();

			if ((model.getChainedModel() != null)
					&& (model.getChainedModel().getObject() != null)) {
				Class<?> modelObjectClass = model.getChainedModel().getObject()
						.getClass();

				component.add(new BeanPropertyValidator(modelObjectClass, model
						.getPropertyExpression()));
			}
		}

	}
}
