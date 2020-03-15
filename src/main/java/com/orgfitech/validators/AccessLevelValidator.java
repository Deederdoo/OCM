package com.orgfitech.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("accessLevelValidator")
public class AccessLevelValidator implements Validator<Integer> {

	@Override
	public void validate(FacesContext context, UIComponent component, Integer value) throws ValidatorException {

		FacesMessage msg = new FacesMessage();

		if (value != null) {

			if (value > 2 || value < 1) {

				msg = new FacesMessage("Value must be 1 or 2");

				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				// context.addMessage(null, msg);
				throw new ValidatorException(msg);
			}
			
		} else {

			msg = new FacesMessage("Please fill");

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			// context.addMessage(null, msg);
			throw new ValidatorException(msg);
		}
	}

}
