package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	protected String assName;

	public static HashMap<Integer, String> assNameMap;

	public static Map<String, Integer> assIdMap;

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

	public String getAssName() {

		return assName;
	}

	public void setAssName(String assName) {

		this.assName = assName;
	}

	public void loadAssessments() {
		setAssessments(assessmentDao.readAllAsessments());
		
		if(assNameMap != null) {
			
			System.out.println("HERE:1");
			assNameMap.clear();
		}
		
		if(assessments != null && !assessments.isEmpty() && assessments.size() > 0) {
			System.out.println("HERE:2");
			System.out.println("ASSSIZE: " + assessments.size());
			assNameMap = new HashMap<>();
		
			// Loads the assessment name to later compare in validator
			for (int i = 0; i < assessments.size(); i++) {

				System.out.println("ASSTEST: " + i + ", " + assessments.get(i).getAssessmentName());
				assNameMap.put(i, assessments.get(i).getAssessmentName());
			}
		}
		
		System.out.println("HERE:3");
	}

	public String displayAssessments() {
		loadAssessments();

		return "main_assessments";
	}

	public int getAssIdByName(String name) {

		int id = assessmentDao.getIdByName(name);

		return id;
	}

	public void createAssessment() {

		assessment = new AssessmentDTO();

		assessment.setAssessmentName(assName);
		assessment.setDate(new Date());
		assessment.setLegacy(false);
		assessment.setAvgPCM(0);
		assessmentDao.createAssessment(assessment);

		assIdMap = new HashMap<>();
		assIdMap.clear();
		assIdMap.put("assId", getAssIdByName(assName));
	}
}
