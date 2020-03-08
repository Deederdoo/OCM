package com.orgfitech.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
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

import com.orgfitech.model.FactorResultDTO;

@Named("adminBarChart")
@SessionScoped
public class AdminBarChart implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private BarChartModel model;
	
	public AdminBarChart() {
		
		model = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("hide");
        
        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        barDataSet.setData(values);
         
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.6)");
        bgColor.add("rgba(255, 159, 64, 0.6)");
        bgColor.add("rgba(255, 205, 86, 0.6)");
        bgColor.add("rgba(75, 192, 192, 0.6)");
        bgColor.add("rgba(54, 162, 235, 0.6)");
        bgColor.add("rgba(153, 102, 255, 0.6)");
        bgColor.add("rgba(201, 203, 207, 0.6)");
        bgColor.add("rgba(191, 63, 191, 0.6)");
        bgColor.add("rgba(63, 191, 63, 0.6)");
        bgColor.add("rgba(255, 75, 10, 0.6)");
        
        barDataSet.setBackgroundColor(bgColor);
         
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        borderColor.add("rgb(191, 63, 191)");
        borderColor.add("rgb(63, 191, 63)");
        borderColor.add("rgb(255, 75, 10)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(3);
         
        data.addChartDataSet(barDataSet);
         
        List<String> labels = new ArrayList<>();
        labels.add("Factor 1");
        labels.add("Factor 2");
        labels.add("Factor 3");
        labels.add("Factor 4");
        labels.add("Factor 5");
        labels.add("Factor 6");
        labels.add("Factor 7");
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		model = new BarChartModel();
//		
//		List<FactorResultDTO> myFacs = new ArrayList<>();
//		
//		Random rand = new Random();
//		
//		for(int i = 0; i < 10; i++) {
//			
//			FactorResultDTO fr = new FactorResultDTO();
//			
//			fr.setDetails("" + i + 1);
//			fr.setFactorPCM((i + 1) * rand.nextInt(10));
//			
//			myFacs.add(fr);
//		}
//		
//		ChartSeries fac1 = new ChartSeries();
//		fac1.setLabel("Factor 1");
//		fac1.set(myFacs.get(0).getDetails(), myFacs.get(0).getFactorPCM());
//		
//		ChartSeries fac2 = new ChartSeries();
//		fac2.setLabel("Factor 2");
//		fac2.set(myFacs.get(1).getDetails(), myFacs.get(1).getFactorPCM());
//		
//		ChartSeries fac3 = new ChartSeries();
//		fac3.setLabel("Factor 3");
//		fac3.set(myFacs.get(2).getDetails(), myFacs.get(2).getFactorPCM());
//		
//		ChartSeries fac4 = new ChartSeries();
//		fac4.setLabel("Factor 4");
//		fac4.set(myFacs.get(3).getDetails(), myFacs.get(3).getFactorPCM());
//		
//		ChartSeries fac5 = new ChartSeries();
//		fac5.setLabel("Factor 5");
//		fac5.set(myFacs.get(4).getDetails(), myFacs.get(4).getFactorPCM());
//		
//		ChartSeries fac6 = new ChartSeries();
//		fac6.setLabel("Factor 6");
//		fac6.set(myFacs.get(5).getDetails(), myFacs.get(5).getFactorPCM());
//		
//		ChartSeries fac7 = new ChartSeries();
//		fac7.setLabel("Factor 7");
//		fac7.set(myFacs.get(6).getDetails(), myFacs.get(6).getFactorPCM());
//		
//		ChartSeries fac8 = new ChartSeries();
//		fac8.setLabel("Factor 8");
//		fac8.set(myFacs.get(7).getDetails(), myFacs.get(7).getFactorPCM());
//		
//		ChartSeries fac9 = new ChartSeries();
//		fac9.setLabel("Factor 9");
//		fac9.set(myFacs.get(8).getDetails(), myFacs.get(8).getFactorPCM());
//		
//		ChartSeries fac10 = new ChartSeries();
//		fac10.setLabel("Factor 10");
//		fac10.set(myFacs.get(9).getDetails(), myFacs.get(9).getFactorPCM());
//		
//		model.addSeries(fac1);
//		model.addSeries(fac2);
//		model.addSeries(fac3);
//		model.addSeries(fac4);
//		model.addSeries(fac5);
//		model.addSeries(fac6);
//		model.addSeries(fac7);
//		model.addSeries(fac8);
//		model.addSeries(fac9);
//		model.addSeries(fac10);
//		
//        model.setTitle("OCM Bar Chart");
//        model.setLegendPosition("ne");
//        model.setLegendPlacement(LegendPlacement.OUTSIDE);
//        model.setExtender("chartExtention");
//        Axis xAxis = model.getAxis(AxisType.X);
//        xAxis.setLabel("Factors");
//        Axis yAxis = model.getAxis(AxisType.Y);
//        yAxis.setLabel("Score");
//        yAxis.setMin(0);	
//        yAxis.setMax(100);
	}
	
	public BarChartModel getModel() {
		
		return model;
	}
}
