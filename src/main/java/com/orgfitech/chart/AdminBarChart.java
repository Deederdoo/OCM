package com.orgfitech.chart;

import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;

@Named("adminBarChart")
public class AdminBarChart {

	private BarChartModel model;
	
	public AdminBarChart() {
		
		model = new BarChartModel();
		
		ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);
        model.addSeries(boys);
        model.addSeries(girls);
        model.setTitle("Bar Chart Test");
//        model.setLegendPosition("s");
//        model.setLegendPlacement(LegendPlacement.OUTSIDE);
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Gender");
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);
	}
	
	public BarChartModel getModel() {
		
		return model;
	}
}
