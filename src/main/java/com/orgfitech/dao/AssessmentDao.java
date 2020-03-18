package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.AssessmentDTO;
import com.orgfitech.model.UserDTO;

public interface AssessmentDao {

	public List<UserDTO> readFinished();
	
	public int getIdByName(String name);
	
	public List<AssessmentDTO> readAllAsessments();
	
	public void createAssessment(AssessmentDTO assessment);
	
	public void deleteAssessmentById(int id);
}
