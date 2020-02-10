package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.inject.Inject;

import com.orgfitech.dao.FactorDao;
import com.orgfitech.model.FactorDTO;

public class FactorController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected ExternalContext externalContext;
	
	protected FactorDao factorDao;
	
	protected List<FactorDTO> factors;

	@Inject
	protected FactorDTO factor;

	@Inject
	public FactorController(FactorDao factorDao) {
		this.factorDao = factorDao;
	}
	
	public void setFactors(List<FactorDTO> factors) {
		this.factors = factors;
	}
	
	public List<FactorDTO> getFactors() {
		return factors;
	}
	
	public FactorDTO getFactor() {
		return factor;
	}
	
	public void setFactor(FactorDTO factor) {
		this.factor = factor;
	}
	
	public void loadFactors() {
		setFactors(factorDao.readAllFactors());
	}
	
	public String displayFactors() {
		loadFactors();
		return "";
	}
}
