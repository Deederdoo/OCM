package com.orgfitech.model;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("questionDTO")
@ViewScoped
public class QuestionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int assessmentId;
	
	private int questionID;
	
	private int factorid;
	
	private String details;

	public int getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getFactorID() {
		return factorid;
	}

	public void setFactorID(int factorid) {
		this.factorid = factorid;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {

		return "Factor [ "+ "AssessmentId=" + assessmentId + "QuestionID=" + questionID + ", FactorID=" + factorid + ", Details=" + details + "]";
	}
}
