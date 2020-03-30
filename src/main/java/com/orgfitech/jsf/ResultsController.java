package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.AdminChartDao;
import com.orgfitech.model.AdminChartDTO;
import com.orgfitech.model.FactorResultDTO;
import com.orgfitech.model.UserDTO;

@Named("resultsController")
@SessionScoped
public class ResultsController implements Serializable{
	private static final long serialVersionUID = 1L;

	public static Map<String, Integer> avgAssScore;
	
	protected boolean toggleHoriBar = false;
	
	protected boolean toggleGauge = false;
	
	protected boolean toggleGauge2 = true;
	
	protected boolean toggleBar = false;
	
	protected int assessmentID;
	
	protected int assessmentAVGScore;
	
	protected List<UserDTO> scores;
	
	protected List<String> factorLabels;
	
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
	
	public void loadUsers(int assID) {
		
		setAdminCharts(adminDao.readAllTables(assID));
		setScores(adminDao.readAvgFac(assID));
		setFactorLabels(adminDao.readFactorLabels(assID));
		avgAssScore();
	}
	
	public String goToResults(int assID) {
		
		this.assessmentID = assID;
		loadUsers(assID);
		resetToggles();
		
		return "results";
	}
	
	public String loadUserResults(int userID) {
		
		setFactorResults(adminDao.readAllCharts(userID, assessmentID));
		System.out.println("ID: " + userID);
		
		return "results";
	}
	
	public void avgAssScore() {
		
		avgAssScore = new HashMap<>();
		int count = 0;
		
		for(int i = 0; i < adminCharts.size(); i ++) {
			
			count += adminCharts.get(i).getPcm();
		}
		
		if(adminCharts.size() > 0) {
			
			count = (count / adminCharts.size());
			
			assessmentAVGScore = count;
			avgAssScore.put("score", assessmentAVGScore);
		}
	}
	
	public void toggle(boolean type) {
		
		if(type) {
			
			if(!toggleGauge) {
				
				setToggleGauge(!isToggleGauge());
			}
			
			if(!toggleHoriBar) {
				
				setToggleHoriBar(!isToggleHoriBar());
			}
			
			if(toggleBar) {
				
				setToggleBar(!isToggleBar());
			}
			
			if(toggleGauge2) {
				
				setToggleGauge2(false);
			}
			
		}else {
			
			if(!toggleBar) {
				
				setToggleBar(!isToggleBar());
			}
			
			if(!toggleHoriBar) {
				
				setToggleHoriBar(!isToggleHoriBar());
			}
			
			if(toggleGauge) {
				
				setToggleGauge(!isToggleGauge());
			}
			
			if(toggleGauge2) {
				
				setToggleGauge2(false);
			}
		}
	}
	
	private void resetToggles() {
		
		toggleHoriBar = false;
		
		toggleGauge = false;
		
		toggleGauge2 = true;
		
		toggleBar = false;
	}
	
	//----------Getters and Setters-------------

	public boolean isToggleGauge2() {
		return toggleGauge2;
	}

	public void setToggleGauge2(boolean toggleGauge2) {
		this.toggleGauge2 = toggleGauge2;
	}

	public boolean isToggleHoriBar() {
		return toggleHoriBar;
	}

	public void setToggleHoriBar(boolean toggleHoriBar) {
		this.toggleHoriBar = toggleHoriBar;
	}
	
	public int getAssessmentAVGScore() {
		return assessmentAVGScore;
	}

	public void setAssessmentAVGScore(int assessmentAVGScore) {
		this.assessmentAVGScore = assessmentAVGScore;
	}

	public List<String> getFactorLabels() {
		return factorLabels;
	}

	public void setFactorLabels(List<String> factorLabels) {
		this.factorLabels = factorLabels;
	}

	public boolean isToggleGauge() {
		return toggleGauge;
	}

	public void setToggleGauge(boolean toggleGauge) {
		this.toggleGauge = toggleGauge;
	}

	public boolean isToggleBar() {
		return toggleBar;
	}

	public void setToggleBar(boolean toggleBar) {
		this.toggleBar = toggleBar;
	}

	public List<UserDTO> getScores() {
		return scores;
	}

	public void setScores(List<UserDTO> scores) {
		this.scores = scores;
	}

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
