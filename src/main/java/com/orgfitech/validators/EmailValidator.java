package com.orgfitech.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator<String>{

	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
		
		FacesMessage msg = new FacesMessage();
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		
		if(!matcher.matches() || value.isEmpty()) {
			
			if(!matcher.matches()) {
				
			msg = new FacesMessage("Email format is incorrect");
			}
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			//context.addMessage(null, msg);
			throw new ValidatorException(msg);
		}
	}
}