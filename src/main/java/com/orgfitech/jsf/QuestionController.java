package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.QuestionDao;
import com.orgfitech.model.QuestionDTO;
import com.orgfitech.model.QuestionDefaultDTO;

@Named("questionController")
@ApplicationScoped
public class QuestionController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected ExternalContext externalContext;

	protected QuestionDao questionDao;

	protected List<QuestionDTO> questions;

	@Inject
	protected QuestionDTO question;

	@Inject
	public QuestionController(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}

	public List<QuestionDTO> getQuestions() {
		return questions;
	}

	public QuestionDTO getQuestion() {
		return question;
	}

	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}

	public void loadQuestions() {
		setQuestions(questionDao.readAllQuestions());
	}

	public String displayQuestions() {
		loadQuestions();
		return "";
	}
	
	public String createQuestion() {
		
		List<List<QuestionDefaultDTO>> tempQues = QuestionDefaultController.questionsMap.get("cleanedQuestions");
		List<QuestionDTO> tempQuestion = new ArrayList<>();
		
		for(int i = 0; i < tempQues.size(); i++) {
			
			System.out.println("QUEST COUNT: " + i);
			
			for(int j = 0; j < tempQues.get(i).size(); j++) {
				
				QuestionDTO temp = new QuestionDTO();
				temp.setFactorID(tempQues.get(i).get(j).getFactorID());
				temp.setDetails(tempQues.get(i).get(j).getDetails());
				
				tempQuestion.add(temp);
				questionDao.createQuestion(AssessmentController.assIdMap.get("assId"), tempQuestion.get(j));
			}
		}
		
		AssessmentController.assIdMap.clear();
		
		return "main_assessments?faces-redirect=true";
	}

}
