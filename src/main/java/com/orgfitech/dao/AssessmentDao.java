package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.AssessmentDTO;

public interface AssessmentDao {

	public List<AssessmentDTO> readAllAsessments();
	
	public String createAssessment(AssessmentDTO assessment);
}
