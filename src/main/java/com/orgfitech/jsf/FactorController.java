package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.SessionMap;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.FactorDao;
import com.orgfitech.model.FactorDTO;

@Named("factorController")
@SessionScoped
public class FactorController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected ExternalContext externalContext;
	
	protected FactorDao factorDao;
	
	protected List<FactorDTO> factors;

    @Inject    @SessionMap
    protected Map<String, Object> sessionMap;
    
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
	
	public void loadFactors() {
		setFactors(factorDao.readAllFactors());
	}
	
	public String displayFactors() {
		loadFactors();
		return "";
	}
}
