package com.orgfitech.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.orgfitech.jsf.AssessmentController;

@FacesValidator("assessmentNameValidator")
public class assessmentNameValidator implements Validator<String>{
	
	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
		
		FacesMessage msg = new FacesMessage();
		
		/*
		 * 
		 * Pull assessment name from db to compare
		 * 
		 * */
		System.out.println("VAL:1");
		if(AssessmentController.assNameMap != null) {
			
			System.out.println("VAL:2");
			for(int i = 0; i < AssessmentController.assNameMap.size(); i++) {
				
				System.out.println("VAL:3 " + i);
				if(value.equals(AssessmentController.assNameMap.get(i))) {
					
					System.out.println("VAL:4");
					msg = new FacesMessage("Assessment Name Already Exists");
					
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					//context.addMessage(null, msg);
					throw new ValidatorException(msg);
				}
			}
			
			System.out.println("VAL:5");
		}
	}

}
