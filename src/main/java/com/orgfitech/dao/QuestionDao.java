package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.QuestionDTO;

public interface QuestionDao {

	public List<QuestionDTO> readAllQuestions();
}
