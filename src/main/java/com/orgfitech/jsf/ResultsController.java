package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.chart.AdminBarChart;
import com.orgfitech.dao.AdminChartDao;
import com.orgfitech.model.AdminChartDTO;
import com.orgfitech.model.FactorResultDTO;

@Named("resultsController")
@SessionScoped
public class ResultsController implements Serializable{
	private static final long serialVersionUID = 1L;

	protected int assessmentID;
	
	@Inject
    protected ExternalContext externalContext;
	
	protected AdminChartDao adminDao;
	
	protected List<AdminChartDTO> adminCharts;
	
	protected List<FactorResultDTO> factorResults;
	
	@Inject
	protected AdminChartDTO adminChart;
	
	@Inject
	protected FactorResultDTO factorResult;
	
	@Inject
	public ResultsController(AdminChartDao dao) {
		this.adminDao = dao;
	}

	public void loadTestValues() {
		
		adminCharts = new ArrayList<>();
		
		for(int i = 0; i < 30; i++) {
			
			adminChart = new AdminChartDTO();
			
			adminChart.setUserID(i + 1);
			adminChart.setFirstName("John");
			adminChart.setLastName("Doe");
			adminChart.setDepartment("Shipping");
			adminChart.setGender("Other");
			adminChart.setAgeGroup("");
			adminChart.setPcm((66 * 4) / (i + 1));
			
			adminCharts.add(adminChart);
		}
		
		
		System.out.println("RAN");
	}
	
	public void loadUsers(int assID) {
		
		setAdminCharts(adminDao.readAllTables(assID));
	}
	
	public String goToResults(int assID) {
		
		this.assessmentID = assID;
		//loadUsers(assID);
		loadTestValues();
		
		return "results";
	}
	
	public String loadUserResults(int userID) {
		
		setFactorResults(adminDao.readAllCharts(userID, assessmentID));
		System.out.println("ID: " + userID);
		
		return "results";
	}
	
	//----------Getters and Setters-------------
	
	public AdminChartDTO getAdminChart() {
		return adminChart;
	}

	public void setAdminChart(AdminChartDTO adminChart) {
		this.adminChart = adminChart;
	}

	public List<AdminChartDTO> getAdminCharts() {
		return adminCharts;
	}

	public void setAdminCharts(List<AdminChartDTO> adminCharts) {
		this.adminCharts = adminCharts;
	}

	public List<FactorResultDTO> getFactorResults() {
		return factorResults;
	}

	public void setFactorResults(List<FactorResultDTO> factorResults) {
		this.factorResults = factorResults;
	}

	public FactorResultDTO getFactorResult() {
		return factorResult;
	}

	public void setFactorResult(FactorResultDTO factorResult) {
		this.factorResult = factorResult;
	}

	public int getAssessmentID() {
		return assessmentID;
	}

	public void setAssessmentID(int assessmentID) {
		this.assessmentID = assessmentID;
	}
}
