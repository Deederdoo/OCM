package com.orgfitech.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("questionDTO")
@SessionScoped
public class QuestionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int assessmentId;
	
	private int questionID;
	
	private int factorid;
	
	private String details;
	
	private int score;

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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {

		return "Factor [ "+ "AssessmentId=" + assessmentId + "QuestionID=" + questionID + ", FactorID=" + factorid + ", Details=" + details + "SCORE: " + score + "]";
	}
}
