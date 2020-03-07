package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.AdminChartDao;
import com.orgfitech.model.AdminChartDTO;

@Named("resultsController")
@SessionScoped
public class ResultsController implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
    protected ExternalContext externalContext;
	
	protected AdminChartDao adminDao;
	
	protected List<AdminChartDTO> adminCharts;
	
	@Inject
	protected AdminChartDTO adminChart;
	
	@Inject
	public ResultsController(AdminChartDao dao) {
		this.adminDao = dao;
	}

	public void loadTestValues() {
		
		adminCharts = new ArrayList<>();
		adminChart = new AdminChartDTO();
		
		adminChart.setId(1);
		adminChart.setFirstName("John");
		adminChart.setLastName("Doe");
		adminChart.setDepartment("Shipping");
		adminChart.setGender("Other");
		adminChart.setAgeGroup("");
		adminChart.setPcm(66);
		
		adminCharts.add(adminChart);
		
		System.out.println("RAN");
	}
	
	public String goToResults() {
		
		loadTestValues();
		
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
}
