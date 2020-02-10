package com.orgfitech.model;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("factorDTO")
@ViewScoped
public class FactorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int factorID;
	
	private int assessmentID;
	
	private String details;
	
	private int avgFactorPCM;

	/**
	 * @return the factorID
	 */
	public int getFactorID() {
		return factorID;
	}

	/**
	 * @param factorID the factorID to set
	 */
	public void setFactorID(int factorID) {
		this.factorID = factorID;
	}

	/**
	 * @return the surveyID
	 */
	public int getAssessmentID() {
		return assessmentID;
	}

	/**
	 * @param surveyID the surveyID to set
	 */
	public void setAssessmentID(int assessmentID) {
		this.assessmentID = assessmentID;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return the avgFactorPCM
	 */
	public int getAvgFactorPCM() {
		return avgFactorPCM;
	}

	/**
	 * @param avgFactorPCM the avgFactorPCM to set
	 */
	public void setAvgFactorPCM(int avgFactorPCM) {
		this.avgFactorPCM = avgFactorPCM;
	}

	@Override
	public String toString() {
		return "Factor [factorID=" + factorID + ", assessmentID=" + assessmentID + ", details=" + details + ", avgFactorPCM="
				+ avgFactorPCM + "]";
	}
}
