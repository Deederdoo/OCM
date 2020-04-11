package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.QuestionDefaultDao;
import com.orgfitech.model.FactorDefaultDTO;
import com.orgfitech.model.QuestionDefaultDTO;

@Named("questionDefaultController")
@SessionScoped
public class QuestionDefaultController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static Map<String, List<List<QuestionDefaultDTO>>> questionsMap;
	
	protected boolean facQues1, facQues2, facQues3, facQues4, facQues5, facQues6, facQues7, facQues8, facQues9, facQues10 = false;
	protected boolean[] boolarray = {facQues1, facQues2, facQues3, facQues4, facQues5
			, facQues6, facQues7, facQues8, facQues9, facQues10};

	protected List<FactorDefaultDTO> fac;
	protected List<QuestionDefaultDTO> q1, q2, q3, q4, q5, q6, q7, q8, q9, q10;
	
	public List<FactorDefaultDTO> getFac() {
		return fac;
	}

	public void setFac(List<FactorDefaultDTO> fac) {
		this.fac = fac;
	}

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
		
		QuestionDefaultDTO qD = new QuestionDefaultDTO();
		List<QuestionDefaultDTO> def = new ArrayList<>();
		qD.setQuestionID(1);
		qD.setFactorID(1);
		qD.setDetails("Do you believe decisions on your career development are easily accessible, or made by your immediate manager?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(2);
		qD.setFactorID(1);
		qD.setDetails("Do you feel you have sufficient input on your career decisions?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(3);
		qD.setFactorID(2);
		qD.setDetails("Do you feel creativity is encouraged while not penalizing mistakes in that pursuit?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(4);
		qD.setFactorID(3);
		qD.setDetails("Do you feel you are given the opportunity to set goals independently or collaboratively?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(5);
		qD.setFactorID(3);
		qD.setDetails("Does the organization actively promote collaboration?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(6);
		qD.setFactorID(4);
		qD.setDetails("Do you feel the organization promotes a balanced work and personal life operating style?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(7);
		qD.setFactorID(4);
		qD.setDetails("Does the organization provide sufficient time off for important personal events?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(8);
		qD.setFactorID(4);
		qD.setDetails("Does the organization provide sufficient opportunities for recreation?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(9);
		qD.setFactorID(5);
		qD.setDetails("Do you feel business decisions are made transparently?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(10);
		qD.setFactorID(6);
		qD.setDetails("Do you feel you are provided adequate compensation and benefits for your role?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(11);
		qD.setFactorID(6);
		qD.setDetails("Do you as an individual find the compensation to be competitive to similar organizations?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(12);
		qD.setFactorID(6);
		qD.setDetails("Are you being financially recognized relative to how you believe you are contributing?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(13);
		qD.setFactorID(7);
		qD.setDetails("Do you feel there is clear and preferred leadership style?");
		def.add(qD);
		
		qD = new QuestionDefaultDTO();
		qD.setQuestionID(14);
		qD.setFactorID(17);
		qD.setDetails("Do you feel the leadership style is one that promotes accessibility?");
		def.add(qD);
		
		setQuestionDefaults(def);
	}
	
	public String createDefaultQuestions() {
		loadDefaultQuestions();
		toggleBoxes();
		return "create_questions?faces-redirect=true";
	}
	
	//gets the questions with the given factor ID
	public List<QuestionDefaultDTO> goGetter(int id) {
		
		//Use this to assign empty rows with a factor id
		int factorId = -1;
		
		List<QuestionDefaultDTO> output = new ArrayList<>();
		
		for(int i = 0; i < questionDefaults.size(); i++) {
			
			if(questionDefaults.get(i).getFactorID() == id) {
				
				factorId = id;
				output.add(questionDefaults.get(i));
			}
		}
		
		//adds an empty row for missing default questions
		while(output.size() < 3) {
			
			QuestionDefaultDTO temp = new QuestionDefaultDTO();
			temp.setFactorID(id);
			
			output.add(temp);
		}
		
		return output;
	}
	
	public void setQuestions(int id){
		
		if(id == 1) {
			
			setQ1(goGetter(id));
			
		}else if(id == 2) {
			
			setQ2(goGetter(id));
			
		}else if(id == 3) {
			
			setQ3(goGetter(id));
			
		}else if(id == 4) {
			
			setQ4(goGetter(id));
			
		}else if(id == 5) {
			
			setQ5(goGetter(id));
			
		}else if(id == 6) {
			
			setQ6(goGetter(id));
			
		}else if(id == 7) {
			
			setQ7(goGetter(id));
			
		}else if(id == 8) {
			
			setQ8(goGetter(id));
			
		}else if(id == 9) {
			
			setQ9(goGetter(id));
			
		}else if(id == 10) {
			
			setQ10(goGetter(id));
			
		}
	}
	
	public void mapQuestions() {
		
		questionsMap = new HashMap<>();
		questionsMap.put("cleanedQuestions", emptyQuestionCleaner());
	}
	
	public List<List<QuestionDefaultDTO>> emptyQuestionCleaner() {
		
		List<Integer> removeCount = new ArrayList<>();
		List<List<QuestionDefaultDTO>> toClean = new ArrayList<>();
		
		toClean.add(q1);
		toClean.add(q2);
		toClean.add(q3);
		toClean.add(q4);
		toClean.add(q5);
		toClean.add(q6);
		toClean.add(q7);
		toClean.add(q8);
		toClean.add(q9);
		toClean.add(q10);
		
		for(int i = 0; i < toClean.size(); i++) {
			
			try {
				
				for(int j = toClean.get(i).size() - 1; j > 0; j--) {
					
					if(toClean.get(i).get(j).getDetails().equals(null) || toClean.get(i).get(j).getDetails().isEmpty() || toClean.get(i).get(j).getDetails().equals(" ")){
						
						System.out.println("CLEANING...");
						System.out.println(toClean.get(i).get(j));
						
						toClean.get(i).remove(j);
					}
				}
				
			}catch(NullPointerException e) {
				
				System.out.println("NULL AT I: " + i);
				removeCount.add(i);
			}
		}
		
		for(int i = removeCount.size() - 1; i >= 0; i--) {
			
			System.out.println("REMOVED: " + removeCount.get(i));
			toClean.remove(removeCount.get(i).intValue());
		}
		
		return toClean;
	}
	
	public void toggleBoxes() {
		
		fac = FactorDefaultController.sizeMap.get("fdMap");
		
		for(int i = 0; i < fac.size(); i++) {
			
			boolarray[i] = true;
			setQuestions(i + 1);
		}
	}

	public boolean[] getBoolarray() {
		return boolarray;
	}

	public void setBoolarray(boolean[] boolarray) {
		this.boolarray = boolarray;
	}
	
	
	
	//Getters and Setters for all the available questions--------------------------------

	public List<QuestionDefaultDTO> getQ1() {
		return q1;
	}

	public void setQ1(List<QuestionDefaultDTO> q1) {
		this.q1 = q1;
	}

	public List<QuestionDefaultDTO> getQ2() {
		return q2;
	}

	public void setQ2(List<QuestionDefaultDTO> q2) {
		this.q2 = q2;
	}

	public List<QuestionDefaultDTO> getQ3() {
		return q3;
	}

	public void setQ3(List<QuestionDefaultDTO> q3) {
		this.q3 = q3;
	}

	public List<QuestionDefaultDTO> getQ4() {
		return q4;
	}

	public void setQ4(List<QuestionDefaultDTO> q4) {
		this.q4 = q4;
	}

	public List<QuestionDefaultDTO> getQ5() {
		return q5;
	}

	public void setQ5(List<QuestionDefaultDTO> q5) {
		this.q5 = q5;
	}

	public List<QuestionDefaultDTO> getQ6() {
		return q6;
	}

	public void setQ6(List<QuestionDefaultDTO> q6) {
		this.q6 = q6;
	}

	public List<QuestionDefaultDTO> getQ7() {
		return q7;
	}

	public void setQ7(List<QuestionDefaultDTO> q7) {
		this.q7 = q7;
	}

	public List<QuestionDefaultDTO> getQ8() {
		return q8;
	}

	public void setQ8(List<QuestionDefaultDTO> q8) {
		this.q8 = q8;
	}

	public List<QuestionDefaultDTO> getQ9() {
		return q9;
	}

	public void setQ9(List<QuestionDefaultDTO> q9) {
		this.q9 = q9;
	}

	public List<QuestionDefaultDTO> getQ10() {
		return q10;
	}

	public void setQ10(List<QuestionDefaultDTO> q10) {
		this.q10 = q10;
	}
}