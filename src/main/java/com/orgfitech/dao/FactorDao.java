package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.FactorDTO;

public interface FactorDao {

	public void createFactor(FactorDTO factor);
	
	public List<FactorDTO> readAllFactors();
}
