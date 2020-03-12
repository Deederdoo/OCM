package com.orgfitech.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("factorDefaultDTO")
@SessionScoped
public class FactorDefaultDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int factorID;

	protected String details;

//	public FactorDefaultDTO(String details) {
//
//		this.details = details;
//	}

	public int getFactorID() {
		return factorID;
	}

	public void setFactorID(int factorID) {
		this.factorID = factorID;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {

		return "Factor [FactorID=" + factorID + ", Details=" + details + "]";
	}
}
