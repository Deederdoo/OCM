package com.orgfitech.model;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("questionDTO")
@ViewScoped
public class QuestionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int questionID;
	
	private FactorDTO factor;
	
	private String details;

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getFactorID() {
		return factor.getFactorID();
	}

	public void setFactorID(int factorid) {
		this.factor.setFactorID(factorid);
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
