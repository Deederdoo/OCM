package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.FactorDefaultDao;
import com.orgfitech.model.FactorDefaultDTO;

@Named("factorDefaultController")
@ApplicationScoped
public class FactorDefaultController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected ExternalContext externalContext;
	
	protected FactorDefaultDao factorDefaultDao;
	
	protected List<FactorDefaultDTO> factorDefaults;

	@Inject
	protected FactorDefaultDTO factorDefault;

	@Inject
	public FactorDefaultController(FactorDefaultDao factorDefaultDao) {
		this.factorDefaultDao = factorDefaultDao;
	}
	
	public List<FactorDefaultDTO> getFactorDefaults() {
		return factorDefaults;
	}

	public void setFactorDefaults(List<FactorDefaultDTO> factorDefaults) {
		this.factorDefaults = factorDefaults;
	}
	
	public void loadDefaultFactors() {
		setFactorDefaults(factorDefaultDao.readAllFactors());
	}
	
	public String createQuestionFactors() {
		loadDefaultFactors();
		return "create_factors";
	}
}
