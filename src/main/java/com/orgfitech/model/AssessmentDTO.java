package com.orgfitech.model;

public class AssessmentDTO {

	private int assessmentID;
	private String assessmentName;
	private String date;
	private boolean legacy;
	private double avgPCM;
	
	public AssessmentDTO() {
		this(0, "", "", false, 0);
	}
	
	public AssessmentDTO(String surveyName) {
		this(0, surveyName, "", false, 0);
	}

	public AssessmentDTO(int assessmentID, String assessmentName, String date, boolean legacy, double avgPCM) {
		this.assessmentID = assessmentID;
		this.assessmentName = assessmentName;
		this.setDate(date);
		this.legacy = legacy;
		this.avgPCM = avgPCM;
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
	 * @return the surveyName
	 */
	public String getAssessmentName() {
		return assessmentName;
	}

	/**
	 * @param surveyName the surveyName to set
	 */
	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}

	/**
	 * @return the legacy
	 */
	public boolean isLegacy() {
		return legacy;
	}

	/**
	 * @param legacy the legacy to set
	 */
	public void setLegacy(boolean legacy) {
		this.legacy = legacy;
	}
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the avgPCM
	 */
	public double getAvgPCM() {
		return avgPCM;
	}

	/**
	 * @param avgPCM the avgPCM to set
	 */
	public void setAvgPCM(double avgPCM) {
		this.avgPCM = avgPCM;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Assessment [assessmentID=" + assessmentID + ", assessmentName=" + assessmentName + ", date=" + date + ", legacy=" + legacy
				+ ", avgPCM=" + avgPCM + "]";
	}
}
