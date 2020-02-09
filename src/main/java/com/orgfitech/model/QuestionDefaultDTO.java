package com.orgfitech.model;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("questionDefaultDTO")
@ViewScoped
public class QuestionDefaultDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int questionID;
	protected FactorDefaultDTO factor = new FactorDefaultDTO();
	protected String details;

//	public QuestionDefaultDTO(FactorDefaultDTO factor, String details) {
//
//		this.factor = factor;
//		this.details = details;
//	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public FactorDefaultDTO getFactorID() {
		return factor;
	}

	public void setFactorID(int factor) {
		this.factor.setFactorID(factor);
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {

		return "Factor [QuestionID=" + questionID + ", FactorID=" + factor + ", Details=" + details + "]";
	}
}
