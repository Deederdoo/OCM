package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.AssessmentDTO;

public interface AssessmentDao {

	public int getIdByName(String name);
	
	public List<AssessmentDTO> readAllAsessments();
	
	public String createAssessment(AssessmentDTO assessment);
}
