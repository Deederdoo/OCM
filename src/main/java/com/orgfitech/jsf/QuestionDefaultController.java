package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.QuestionDefaultDao;
import com.orgfitech.model.QuestionDefaultDTO;

@Named("questionDefaultController")
@ApplicationScoped
public class QuestionDefaultController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected ExternalContext externalContext;
	
	protected QuestionDefaultDao questionDefaultDao;
	
	protected List<QuestionDefaultDTO> questionDefaults;

	@Inject
	protected QuestionDefaultDTO questionDefault;

	@Inject
	public QuestionDefaultController(QuestionDefaultDao questionDefaultDao) {
		this.questionDefaultDao = questionDefaultDao;
	}
	
	public List<QuestionDefaultDTO> getQuestionDefaults() {
		return questionDefaults;
	}

	public void setQuestionDefaults(List<QuestionDefaultDTO> questionDefaults) {
		this.questionDefaults = questionDefaults;
	}
	
	public void loadDefaultQuestions() {
		setQuestionDefaults(questionDefaultDao.readAllDefaultQuestions());
	}
	
	public String createDefaultQuestions() {
		loadDefaultQuestions();
		return "create_questions";
	}
}