package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.QuestionDefaultDao;
import com.orgfitech.model.FactorDefaultDTO;
import com.orgfitech.model.QuestionDefaultDTO;

@Named("questionDefaultController")
@ApplicationScoped
public class QuestionDefaultController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected boolean facQues1, facQues2, facQues3, facQues4, facQues5, facQues6, facQues7, facQues8, facQues9, facQues10 = false;
	protected boolean[] boolarray = {facQues1, facQues2, facQues3, facQues4, facQues5
			, facQues6, facQues7, facQues8, facQues9, facQues10};
	protected List<FactorDefaultDTO> fac;
	
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
		setQuestionDefaults(questionDefaultDao.readAllDefaultQuestions());
	}
	
	public String createDefaultQuestions() {
		loadDefaultQuestions();
		toggleBoxes();
		return "create_questions";
	}
	
	public void toggleBoxes() {
		
		fac = FactorDefaultController.sizeMap.get("fdMap");
		
		for(int i = 0; i < fac.size(); i++) {
			
			boolarray[i] = true;
		}
	}
	
	//Setters and Getters for displaying the factors and questions--------------------------------------------------------------------------------

	public boolean isFacQues1() {
		return facQues1;
	}

	public void setFacQues1(boolean facQues1) {
		this.facQues1 = facQues1;
	}

	public boolean isFacQues2() {
		return facQues2;
	}

	public void setFacQues2(boolean facQues2) {
		this.facQues2 = facQues2;
	}

	public boolean isFacQues3() {
		return facQues3;
	}

	public void setFacQues3(boolean facQues3) {
		this.facQues3 = facQues3;
	}

	public boolean isFacQues4() {
		return facQues4;
	}

	public void setFacQues4(boolean facQues4) {
		this.facQues4 = facQues4;
	}

	public boolean isFacQues5() {
		return facQues5;
	}

	public void setFacQues5(boolean facQues5) {
		this.facQues5 = facQues5;
	}

	public boolean isFacQues6() {
		return facQues6;
	}

	public void setFacQues6(boolean facQues6) {
		this.facQues6 = facQues6;
	}

	public boolean isFacQues7() {
		return facQues7;
	}

	public void setFacQues7(boolean facQues7) {
		this.facQues7 = facQues7;
	}

	public boolean isFacQues8() {
		return facQues8;
	}

	public void setFacQues8(boolean facQues8) {
		this.facQues8 = facQues8;
	}

	public boolean isFacQues9() {
		return facQues9;
	}

	public void setFacQues9(boolean facQues9) {
		this.facQues9 = facQues9;
	}

	public boolean isFacQues10() {
		return facQues10;
	}

	public void setFacQues10(boolean facQues10) {
		this.facQues10 = facQues10;
	}
}