package com.orgfitech.model;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("assessmentDTO")
@ViewScoped
public class AssessmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int assessmentID;
	protected String assessmentName;
	protected Date date;
	protected boolean legacy;
	protected double avgPCM;

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
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
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
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + assessmentID;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserDTO)) {
            return false;
        }
        UserDTO other = (UserDTO) obj;
        if (assessmentID != other.id) {
            return false;
        }
        return true;
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
