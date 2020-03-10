package com.orgfitech.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

import com.orgfitech.jsf.QuestionController;

@Named("adminBarChart")
@RequestScoped
public class AdminBarChart implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private BarChartModel model;
	
	protected int pcm;
	
	public AdminBarChart() {
		
		model = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Score");
        
        List<Number> values = new ArrayList<>();
        values.add(QuestionController.resultsPCM.get("pcm")); //PCM
        
        barDataSet.setData(values);
         
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(78, 66, 173, 0.8)"); //PCM
        
        barDataSet.setBackgroundColor(bgColor);
         
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(10, 2, 0)"); //PCM
        
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(0.5);
         
        data.addChartDataSet(barDataSet);
         
        List<String> labels = new ArrayList<>();
        labels.add("PCM"); //PCM
        
        data.setLabels(labels);
        model.setData(data);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        ticks.setMax(100);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(true);
        title.setText("OCM Bar Chart");
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
 
        model.setOptions(options);
	}
		
	public void createModel(int value) {
		
		this.model = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Score");
        
        List<Number> values = new ArrayList<>();
        values.add(value); //PCM
        
        barDataSet.setData(values);
         
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(78, 66, 173, 0.8)"); //PCM
        
        barDataSet.setBackgroundColor(bgColor);
         
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(10, 2, 0)"); //PCM
        
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(0.5);
         
        data.addChartDataSet(barDataSet);
         
        List<String> labels = new ArrayList<>();
        labels.add("PCM"); //PCM
        
        data.setLabels(labels);
        this.model.setData(data);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        ticks.setMax(100);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(true);
        title.setText("OCM Bar Chart");
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
 
        this.model.setOptions(options);
	}
	
	public BarChartModel getModel() {
		
		return model;
	}

	public int getPcm() {
		return pcm;
	}

	public void setPcm(int pcm) {
		this.pcm = pcm;
	}
}
