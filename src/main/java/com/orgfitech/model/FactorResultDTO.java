package com.orgfitech.model;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("factorResultDTO")
@ViewScoped
public class FactorResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int factorAnswerID;
    private int userID;
    private int surveyID;
    private int factorRank;
    private int factorPCM;
    private int factorID;
    
    public int getFactorAnswerID() {
        return factorAnswerID;
    }
    public void setFactorAnswerID(int factorAnswerID) {
        this.factorAnswerID = factorAnswerID;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getSurveyID() {
        return surveyID;
    }
    public void setSurveyID(int surveyID) {
        this.surveyID = surveyID;
    }
    public int getFactorRank() {
        return factorRank;
    }
    public void setFactorRank(int factorRank) {
        this.factorRank = factorRank;
    }
    public int getFactorPCM() {
        return factorPCM;
    }
    public void setFactorPCM(int factorPCM) {
        this.factorPCM = factorPCM;
    }
    public int getFactorID() {
        return factorID;
    }
    public void setFactorID(int factorID) {
        this.factorID = factorID;
    }
    
    @Override
    public String toString() {
        return "FactorResultDTO [factorAnswerID=" + factorAnswerID + ", userID=" + userID + ", surveyID=" + surveyID
                + ", factorRank=" + factorRank + ", factorPCM=" + factorPCM + ", factorID=" + factorID + "]";
    }
    
    
}
