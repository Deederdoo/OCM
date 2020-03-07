package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.AdminChartDTO;

public interface AdminChartDao {

	public List<AdminChartDTO> readAllCharts();
}
