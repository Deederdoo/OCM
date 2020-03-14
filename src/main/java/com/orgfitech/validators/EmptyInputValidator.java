package com.orgfitech.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emptyInputValidator")
public class EmptyInputValidator implements Validator<String>{
	
	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
		
		FacesMessage msg = new FacesMessage();
		
		if(value == null || value.equals(null) || value.isEmpty()) {
			
			msg = new FacesMessage("Please fill");
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			//context.addMessage(null, msg);
			throw new ValidatorException(msg);
		}
	}

}
