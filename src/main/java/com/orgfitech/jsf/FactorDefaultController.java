package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.FactorDefaultDao;
import com.orgfitech.model.FactorDefaultDTO;

@Named("factorDefaultController")
@SessionScoped
public class FactorDefaultController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String details;
	public static Map<String, List<FactorDefaultDTO>> sizeMap;
	
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
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void loadDefaultFactors() {
		
		setFactorDefaults(factorDefaultDao.readAllDefaultFactors());
	}
	
	public String createQuestionFactors() {
		loadDefaultFactors();
		return "create_factors?faces-redirect=true";
	}
	
	public void submitFactors() {
		
		sizeMap = new HashMap<String, List<FactorDefaultDTO>>();
		
		sizeMap.put("fdMap", factorDefaults);
	}
	
	public String addFactor() {
		
		FactorDefaultDTO fac = new FactorDefaultDTO();
		
		if(factorDefaults.size() < 10) {
			
			fac.setFactorID(factorDefaults.size() + 1);
			fac.setDetails(this.details);
			
			factorDefaults.add(fac);
			setDetails(null);
		}
		
		return null;
	}
	
	public String removeFactor(FactorDefaultDTO fd) {
		
		factorDefaults.remove(fd);
		
		for(int i = 0; i < factorDefaults.size(); i++) {
			
			factorDefaults.get(i).setFactorID(i + 1);
		}
		
		return null;
	}
}
