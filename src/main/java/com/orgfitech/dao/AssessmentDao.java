package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.AssessmentDTO;

public interface AssessmentDao {

	public List<Integer> readFinished();
	
	public int getIdByName(String name);
	
	public List<AssessmentDTO> readAllAsessments();
	
	public void createAssessment(AssessmentDTO assessment);
}
