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
		
		FactorDefaultDTO fD = new FactorDefaultDTO();
		List<FactorDefaultDTO> def = new ArrayList<>();
		fD.setFactorID(1);
		fD.setDetails("How career development decisions are made");
		def.add(fD);
		
		fD = new FactorDefaultDTO();
		fD.setFactorID(2);
		fD.setDetails("Does the organization encourage creativity");
		def.add(fD);
		
		fD = new FactorDefaultDTO();
		fD.setFactorID(3);
		fD.setDetails("How flexible the organization is in the way one works");
		def.add(fD);
		
		fD = new FactorDefaultDTO();
		fD.setFactorID(4);
		fD.setDetails("Is a balanced work and personal life valued here");
		def.add(fD);
		
		fD = new FactorDefaultDTO();
		fD.setFactorID(5);
		fD.setDetails("How business decisions are made");
		def.add(fD);
		
		fD = new FactorDefaultDTO();
		fD.setFactorID(6);
		fD.setDetails("The compensation meets my requirements");
		def.add(fD);
		
		fD = new FactorDefaultDTO();
		fD.setFactorID(7);
		fD.setDetails("How leaders behave is important to this organization");
		def.add(fD);
		
		setFactorDefaults(def);
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
