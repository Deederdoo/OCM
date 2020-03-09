package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.QuestionDTO;

public interface QuestionDao {

	public void createQuestion(int quesId, QuestionDTO question);
	
	public List<QuestionDTO> readAllQuestions();
	
	public List<QuestionDTO> readAllQuestionsByAssID(int assID);
}
