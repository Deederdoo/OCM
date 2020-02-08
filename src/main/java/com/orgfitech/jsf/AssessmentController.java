package com.orgfitech.jsf;

import java.io.Serializable;
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
	
	public List<AssessmentDTO> getAsses(){
		return assessments;
	}
	
	public void setAsses(List<AssessmentDTO> assessments) {
		this.assessments = assessments;
	}
	
	public AssessmentDTO getAss() {
		return assessment;
	}
	
	public void setAss(AssessmentDTO assessment) {
		this.assessment = assessment;
	}
	
	public void loadAssessments() {
		setAsses(assessmentDao.readAllAsessments());
	}
	
	public String displayAssessments() {
		loadAssessments();
		return "main_assessments";
	}
}
