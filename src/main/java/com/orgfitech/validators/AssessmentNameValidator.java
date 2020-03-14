package com.orgfitech.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.orgfitech.jsf.AssessmentController;

@FacesValidator("assessmentNameValidator")
public class AssessmentNameValidator implements Validator<String>{
	
	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
		
		FacesMessage msg = new FacesMessage();
		
		/*
		 * 
		 * Pull assessment name from db to compare
		 * 
		 * */
		if(AssessmentController.assNameMap != null) {
			
			for(int i = 0; i < AssessmentController.assNameMap.size(); i++) {
				
				if(value.equals(AssessmentController.assNameMap.get(i))) {
					
					msg = new FacesMessage("Assessment Name Already Exists");
					
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					//context.addMessage(null, msg);
					throw new ValidatorException(msg);
				}
			}
		}
	}

}
