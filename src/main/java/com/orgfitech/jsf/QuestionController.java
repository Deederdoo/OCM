package com.orgfitech.jsf;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.QuestionDao;
import com.orgfitech.model.FactorDTO;
import com.orgfitech.model.QuestionDTO;
import com.orgfitech.model.QuestionDefaultDTO;

@Named("questionController")
@ApplicationScoped
public class QuestionController implements Serializable {
	private static final long serialVersionUID = 1L;

	public static Map<Integer, List<QuestionDTO>> questionScore;
	public static Map<String, Integer> resultsPCM;
	public static Map<Integer, Double> resultsAVGFactor;
	
	protected boolean facQues1, facQues2, facQues3, facQues4, facQues5, facQues6, facQues7, facQues8, facQues9,
			facQues10 = false;
	protected boolean[] boolarray = { facQues1, facQues2, facQues3, facQues4, facQues5, facQues6, facQues7, facQues8,
			facQues9, facQues10 };

	protected List<QuestionDTO> q1, q2, q3, q4, q5, q6, q7, q8, q9, q10;

	protected List<FactorDTO> qFactors;

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

	public void loadQuestionsByAssID(int assID) {
		setQuestions(questionDao.readAllQuestionsByAssID(assID));
		genToggleBoxes();
	}

	public String displayQuestionsByID(int assID) {
		loadQuestionsByAssID(assID);

		return "generaluser_questions";
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

		for (int i = 0; i < tempQues.size(); i++) {

			for (int j = 0; j < tempQues.get(i).size(); j++) {

				QuestionDTO temp = new QuestionDTO();
				temp.setFactorID(tempQues.get(i).get(j).getFactorID());
				temp.setDetails(tempQues.get(i).get(j).getDetails());

				questionDao.createQuestion(AssessmentController.assIdMap.get("assId"), temp);
			}
		}

		AssessmentController.assIdMap.clear();

		return "main_assessments?faces-redirect=true";
	}

	public void genToggleBoxes() {

		qFactors = FactorController.factorGenMap.get("fgMap");

		for (int i = 0; i < qFactors.size(); i++) {

			boolarray[i] = true;
			genSetQuestions(i + 1);
		}
	}

	public void genSetQuestions(int id) {

		if (id == 1) {

			setQ1(goGetter(id));

		} else if (id == 2) {

			setQ2(goGetter(id));

		} else if (id == 3) {

			setQ3(goGetter(id));

		} else if (id == 4) {

			setQ4(goGetter(id));

		} else if (id == 5) {

			setQ5(goGetter(id));

		} else if (id == 6) {

			setQ6(goGetter(id));

		} else if (id == 7) {

			setQ7(goGetter(id));

		} else if (id == 8) {

			setQ8(goGetter(id));

		} else if (id == 9) {

			setQ9(goGetter(id));

		} else if (id == 10) {

			setQ10(goGetter(id));

		}
	}

	// gets the questions with the given factor ID
	public List<QuestionDTO> goGetter(int id) {

		List<QuestionDTO> output = new ArrayList<>();

		for (int i = 0; i < questions.size(); i++) {

			if (questions.get(i).getFactorID() == id) {

				output.add(questions.get(i));
			}
		}

		return output;
	}

	public void submitQScore() {
		
		List<List<QuestionDTO>> tempList = new ArrayList<>();
		resultsAVGFactor = new HashMap<>();
		resultsPCM = new HashMap<>();
		DecimalFormat deci = new DecimalFormat("###.##");
		double totalWeightedFactor = 0;
		
		tempList.add(q1);
		tempList.add(q2);
		tempList.add(q3);
		tempList.add(q4);
		tempList.add(q5);
		tempList.add(q6);
		tempList.add(q7);
		tempList.add(q8);
		tempList.add(q9);
		tempList.add(q10);

		questionScore = new HashMap<>();
		
		for(int i = 0; i < tempList.size(); i++) {
			
			if(boolarray[i]) {
				
				double calc = 0;
				double count = 0;
				
				for(int j = 0; j < tempList.get(i).size(); j++) {
					
					calc += tempList.get(i).get(j).getScore();
					count++;
				}
				
				calc = (calc / count);
				String tempCalc = deci.format(calc);
				calc = Double.parseDouble(tempCalc);
				
					double finalScore = 0;
					
					finalScore = (calc * FactorController.factorScore.get(i) / 10);
					String tempStringDub = deci.format(finalScore);
					finalScore = Double.parseDouble(tempStringDub);
					questionDao.createQuestionAnswer(finalScore);
					resultsAVGFactor.put(i, finalScore);
					
					totalWeightedFactor += finalScore;
			}
		}
		
		int factorSum = 0;
		
		for(int i = 0; i < FactorController.factorScore.size(); i++) {
			
			factorSum += FactorController.factorScore.get(i);
		}
		
		double tempPCM = (totalWeightedFactor / factorSum) * 100;
		int pcm = (int)tempPCM;
		questionDao.createQuestionPersonAnswer(pcm);
		resultsPCM.put("pcm", pcm);
	}

	public List<FactorDTO> getqFactors() {
		return qFactors;
	}

	public void setqFactors(List<FactorDTO> qFactors) {
		this.qFactors = qFactors;
	}

	public boolean[] getBoolarray() {
		return boolarray;
	}

	public void setBoolarray(boolean[] boolarray) {
		this.boolarray = boolarray;
	}

	// Getters and Setters for the Questions

	public List<QuestionDTO> getQ1() {
		return q1;
	}

	public void setQ1(List<QuestionDTO> q1) {
		this.q1 = q1;
	}

	public List<QuestionDTO> getQ2() {
		return q2;
	}

	public void setQ2(List<QuestionDTO> q2) {
		this.q2 = q2;
	}

	public List<QuestionDTO> getQ3() {
		return q3;
	}

	public void setQ3(List<QuestionDTO> q3) {
		this.q3 = q3;
	}

	public List<QuestionDTO> getQ4() {
		return q4;
	}

	public void setQ4(List<QuestionDTO> q4) {
		this.q4 = q4;
	}

	public List<QuestionDTO> getQ5() {
		return q5;
	}

	public void setQ5(List<QuestionDTO> q5) {
		this.q5 = q5;
	}

	public List<QuestionDTO> getQ6() {
		return q6;
	}

	public void setQ6(List<QuestionDTO> q6) {
		this.q6 = q6;
	}

	public List<QuestionDTO> getQ7() {
		return q7;
	}

	public void setQ7(List<QuestionDTO> q7) {
		this.q7 = q7;
	}

	public List<QuestionDTO> getQ8() {
		return q8;
	}

	public void setQ8(List<QuestionDTO> q8) {
		this.q8 = q8;
	}

	public List<QuestionDTO> getQ9() {
		return q9;
	}

	public void setQ9(List<QuestionDTO> q9) {
		this.q9 = q9;
	}

	public List<QuestionDTO> getQ10() {
		return q10;
	}

	public void setQ10(List<QuestionDTO> q10) {
		this.q10 = q10;
	}

}
