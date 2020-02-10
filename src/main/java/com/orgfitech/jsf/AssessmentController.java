package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.AssessmentDao;
import com.orgfitech.model.AssessmentDTO;

@Named("assessmentController")
@ApplicationScoped
public class AssessmentController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected ExternalContext externalContext;
	
	protected AssessmentDao assessmentDao;
	
	protected List<AssessmentDTO> assessments;

	@Inject
	protected AssessmentDTO assessment;

	@Inject
	public AssessmentController(AssessmentDao assesmentDao) {
		this.assessmentDao = assesmentDao;
	}
	
	public void setAssessments(List<AssessmentDTO> assessments) {
		this.assessments = assessments;
	}
	
	public List<AssessmentDTO> getAssessments() {
		return assessments;
	}
	
	public AssessmentDTO getAssessment() {
		return assessment;
	}
	
	public void setAssessment(AssessmentDTO assessment) {
		this.assessment = assessment;
	}
	
	public void loadAssessments() {
		setAssessments(assessmentDao.readAllAsessments());
	}
	
	public String displayAssessments() {
		loadAssessments();
		return "main_assessments";
	}
	
	public String createAssessment(String assessmentName, boolean isLegacy, double avgPCM) {
		
		assessment = new AssessmentDTO();
		
		assessment.setAssessmentName(assessmentName);
		assessment.setDate(new Date().toString());
		assessment.setLegacy(isLegacy);
		assessment.setAvgPCM(avgPCM);
		assessmentDao.createAssessment(assessment);
		
		return "create_questions";
	}
}
