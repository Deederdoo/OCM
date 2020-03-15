package com.orgfitech.chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

import com.orgfitech.jsf.QuestionController;

@Named("adminHoriBarChart")
@RequestScoped
public class AdminHoriBarChart {

	HorizontalBarChartModel hbarModel;
	
	public AdminHoriBarChart() {
		
		hbarModel = new HorizontalBarChartModel();
	    ChartData data = new ChartData();
	    
	    HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
	    hbarDataSet.setLabel("Score");
	     
	    List<Number> values = new ArrayList<>();
	    
	    hbarDataSet.setData(values);
	     
	    List<String> bgColor = new ArrayList<>();
	    bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
	    hbarDataSet.setBackgroundColor(bgColor);
	     
	    List<String> borderColor = new ArrayList<>();
	    borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");;
	    hbarDataSet.setBorderColor(borderColor);
	    hbarDataSet.setBorderWidth(1);
	     
	    data.addChartDataSet(hbarDataSet);
	     
	    List<String> labels = new ArrayList<>();
	    labels.add("Factor 1");
	    labels.add("Factor 2");
	    labels.add("Factor 3");
	    labels.add("Factor 4");
	    labels.add("Factor 5");
	    labels.add("Factor 6");
	    labels.add("Factor 7");
	    data.setLabels(labels);
	    hbarModel.setData(data);
	     
	    //Options
	    BarChartOptions options = new BarChartOptions();
	    CartesianScales cScales = new CartesianScales();
	    CartesianLinearAxes linearAxes = new CartesianLinearAxes();
	    CartesianLinearTicks ticks = new CartesianLinearTicks();
	    ticks.setBeginAtZero(true);
	    ticks.setMax(10);
	    linearAxes.setTicks(ticks);
	    cScales.addXAxesData(linearAxes);
	    options.setScales(cScales);
	     
	    Title title = new Title();
	    title.setDisplay(true);
	    title.setText("PCM Factor Average Score");
	    title.setFontSize(24);
        title.setFontFamily("Cairo Regular");
        title.setFontColor("black");
	    options.setTitle(title);
	    
	    Legend legend = new Legend();
        legend.setDisplay(false);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
	     
	    hbarModel.setOptions(options);
	}
	
	public void updateModel(List<Double> usrScore) {
		
	    ChartData data = new ChartData();
	    
	    HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
	    hbarDataSet.setLabel("Score");
	     
	    List<Number> values = new ArrayList<>();
	    
	    for(int i = 0; i < usrScore.size(); i++) {
	    	
	    	values.add(usrScore.get(i));
	    }
	    
	    hbarDataSet.setData(values);
	     
	    List<String> bgColor = new ArrayList<>();
	    bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
        bgColor.add("rgba(69, 107, 161, 0.8)");
	    hbarDataSet.setBackgroundColor(bgColor);
	     
	    List<String> borderColor = new ArrayList<>();
	    borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
        borderColor.add("rgb(10, 2, 0)");
	    hbarDataSet.setBorderColor(borderColor);
	    hbarDataSet.setBorderWidth(1);
	     
	    data.addChartDataSet(hbarDataSet);
	     
	    List<String> labels = new ArrayList<>();
	    
	    for(int i = 0; i < usrScore.size(); i++) {
	    	
	    	labels.add("Factor " + (i + 1));
	    }
	    
	    data.setLabels(labels);
	    this.hbarModel.setData(data);
	     
	    //Options
	    BarChartOptions options = new BarChartOptions();
	    CartesianScales cScales = new CartesianScales();
	    CartesianLinearAxes linearAxes = new CartesianLinearAxes();
	    CartesianLinearTicks ticks = new CartesianLinearTicks();
	    ticks.setBeginAtZero(true);
	    ticks.setMax(10);
	    linearAxes.setTicks(ticks);
	    cScales.addXAxesData(linearAxes);
	    options.setScales(cScales);
	     
	    Title title = new Title();
	    title.setDisplay(true);
	    title.setText("PCM Factor Average Score");
	    title.setFontSize(24);
        title.setFontFamily("Cairo Regular");
        title.setFontColor("black");
	    options.setTitle(title);
	    
	    Legend legend = new Legend();
        legend.setDisplay(false);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
	     
        this.hbarModel.setOptions(options);
	}

	public HorizontalBarChartModel getHbarModel() {
		return hbarModel;
	}
}
