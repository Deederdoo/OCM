package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.AdminChartDTO;
import com.orgfitech.model.FactorResultDTO;
import com.orgfitech.model.UserDTO;

public interface AdminChartDao {

	public List<UserDTO> readAvgFac(int assID);
	
	public List<AdminChartDTO> readAllTables(int assID);
	
	public List<FactorResultDTO> readAllCharts(int userID, int assID);
}
