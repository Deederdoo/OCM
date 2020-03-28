package com.orgfitech.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.MeterGaugeChartModel;

@Named("adminGauge")
@RequestScoped
public class AdminGauge implements Serializable{
	private static final long serialVersionUID = 1L;

	private MeterGaugeChartModel model;
	
	private List<Number> intervals;
	
	public AdminGauge() {
		
		createGauge(0);
	}
	
	public void createGauge(int value) {
		
		intervals = new ArrayList<>();
		intervals.add(20);
		intervals.add(40);
		intervals.add(60);
		intervals.add(80);
		intervals.add(100);
		
		model = new MeterGaugeChartModel(value, intervals);
		model.setValue(value);
		model.setSeriesColors("456ba1,537fbe,6193db,6da6f7,8abafe");
		model.setGaugeLabel(String.valueOf(value));
		//model.setGaugeLabelPosition("bottom");
		model.setShowTickLabels(true);
		
	}

	public MeterGaugeChartModel getModel() {
		return model;
	}

	public void setModel(MeterGaugeChartModel model) {
		this.model = model;
	}
	
	
}
