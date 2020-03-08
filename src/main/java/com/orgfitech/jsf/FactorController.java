package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.FactorDao;
import com.orgfitech.model.FactorDTO;
import com.orgfitech.model.FactorDefaultDTO;

@Named("factorController")
@ApplicationScoped
public class FactorController implements Serializable {
	private static final long serialVersionUID = 1L;

	public static Map<Integer, Integer> factorScore;
	
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
	
	public void genUserLoadFactors(int assID) {
		setFactors(factorDao.readAllFactorsByID(assID));
	}

	public String genUserDisplayFactors(int assID) {
		genUserLoadFactors(assID);
		
		return "generaluser_factors";
	}

	public void createFactors() {

		List<FactorDefaultDTO> tempFac = FactorDefaultController.sizeMap.get("fdMap");

		for (int i = 0; i < tempFac.size(); i++) {

			FactorDTO temp = new FactorDTO();
			temp.setAssessmentID(AssessmentController.assIdMap.get("assId"));
			temp.setFactorID(tempFac.get(i).getFactorID());
			temp.setDetails(tempFac.get(i).getDetails());
			temp.setAvgFactorPCM(0);
			System.out.println("TEMPFACTOR: " + temp.toString());

			factorDao.createFactor(temp);
			System.out.println("PASSED: " + i);
		}
	}
	
	//Saved the user entered factors to this map for later use
	public void submitScore() {
		
		factorScore = new HashMap<>();
		
		for(int i = 0; i < factors.size(); i++) {
			
			factorScore.put(i, factors.get(i).getFactorScore());
		}
	}
	
	
}
