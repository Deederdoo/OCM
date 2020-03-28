package com.orgfitech.chart;

import java.io.Serializable;
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
import com.orgfitech.model.UserDTO;

@Named("adminHoriBarChart")
@RequestScoped
public class AdminHoriBarChart implements Serializable {
	private static final long serialVersionUID = 1L;

	HorizontalBarChartModel hbarModel;

	public AdminHoriBarChart() {

		hbarModel = new HorizontalBarChartModel();
	}

	public void updateModel(List<UserDTO> usrScore, List<String> facLabels, int id) {

		ChartData data = new ChartData();

		HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
		hbarDataSet.setLabel("Score");

		List<Number> values = new ArrayList<>();

		for (int i = 0; i < usrScore.size(); i++) {

			if (usrScore.get(i).getId() == id) {

				values.add(usrScore.get(i).getScore());
			}
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

		data.setLabels(facLabels);
		this.hbarModel.setData(data);

		// Options
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
	
	public void category(List<Integer> amount, int type) {
		
		if(type == 1) {
			
			department(amount);
			
		}else if(type == 2) {
			
			gender(amount);
			
		}else {
			
			ageGroup(amount);
		}
	}
	
	private void department(List<Integer> amount) {
		
		List<Number> values = new ArrayList<>();
		
		for(int i = 0; i < amount.size(); i++) {
			
			values.add(amount.get(i));
		}
		
		List<String> labels = new ArrayList<>();
		
		updateCompare(values, labels, "Department");
	}

	private void gender(List<Integer> amount) {

		List<Number> values = new ArrayList<>();

		for (int i = 0; i < amount.size(); i++) {

			values.add(amount.get(i));
		}

		List<String> labels = new ArrayList<>();

		labels.add("Male");
		labels.add("Female");
		labels.add("Other");

		updateCompare(values, labels, "Gender");
	}
	
	private void ageGroup(List<Integer> amount) {
		
		List<Number> values = new ArrayList<>();
		
		for(int i = 0; i < amount.size(); i++) {
			
			values.add(amount.get(i));
		}
		
		List<String> labels = new ArrayList<>();
		
		labels.add("Under 18");
		labels.add("18 to 24");
		labels.add("25 to 39");
		labels.add("40 to 64");
		labels.add("65 and Over");
		
		updateCompare(values, labels, "Age Group");
	}

	private void updateCompare(List<Number> values, List<String> labels, String titleValue) {

		ChartData data = new ChartData();

		HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
		hbarDataSet.setLabel("Score");

		hbarDataSet.setData(values);

		List<String> bgColor = new ArrayList<>();

		for (int i = 0; i < values.size(); i++) {

			bgColor.add("rgba(69, 107, 161, 0.8)");
		}

		hbarDataSet.setBackgroundColor(bgColor);

		List<String> borderColor = new ArrayList<>();

		for (int i = 0; i < values.size(); i++) {

			borderColor.add("rgb(10, 2, 0)");
		}

		hbarDataSet.setBorderColor(borderColor);
		hbarDataSet.setBorderWidth(1);

		data.addChartDataSet(hbarDataSet);

		data.setLabels(labels);
		this.hbarModel.setData(data);

		// Options
		BarChartOptions options = new BarChartOptions();
		CartesianScales cScales = new CartesianScales();
		CartesianLinearAxes linearAxes = new CartesianLinearAxes();
		CartesianLinearTicks ticks = new CartesianLinearTicks();
		ticks.setBeginAtZero(true);
		
		int max = 0;
		
		for(int i = 0; i < values.size(); i++) {
			
			if(max < values.get(i).intValue()) {
				
				max = values.get(i).intValue();
			}
		}
		
		ticks.setMax(max + 1);
		linearAxes.setTicks(ticks);
		cScales.addXAxesData(linearAxes);
		options.setScales(cScales);

		Title title = new Title();
		title.setDisplay(true);

		title.setText(titleValue);
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
