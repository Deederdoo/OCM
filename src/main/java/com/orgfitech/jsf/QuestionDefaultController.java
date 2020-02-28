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

	public boolean[] getBoolarray() {
		return boolarray;
	}

	public void setBoolarray(boolean[] boolarray) {
		this.boolarray = boolarray;
	}
}