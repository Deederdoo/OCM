package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.model.AdminChartDTO;

@Named("compareController")
@SessionScoped
public class CompareController implements Serializable {
	private static final long serialVersionUID = 1L;

	public static Map<String, String> dropDownMap;
	
	protected List<Integer> avgAmount;
	protected List<Integer> typeAmount;
	
	protected int type;

	protected String selectedItem;
	
	protected String selectedGender;
	
	protected String selectedAge;

	protected List<String> dropDownItems;
	
	protected List<String> genderDrop;
	
	protected List<String> ageDrop;

	@Inject
	protected ExternalContext externalContext;

	@PostConstruct
	public void buildDropDownItems() {

		dropDownItems = new ArrayList<>();

		//dropDownItems.add("Department"); WIP
		dropDownItems.add("Gender");
		dropDownItems.add("Age Group");
		
		//-------------------------------For generaluser_type
		genderDrop = new ArrayList<>();
		ageDrop = new ArrayList<>();
		
		genderDrop.add("Male");
		genderDrop.add("Female");
		genderDrop.add("Other");
		
		ageDrop.add("Under 18");
		ageDrop.add("18 to 24");
		ageDrop.add("25 to 39");
		ageDrop.add("40 to 64");
		ageDrop.add("65 and Over");
	}
	
	public String selectedItems() {
		
		dropDownMap = new HashMap<>();
		dropDownMap.put("gender", selectedGender);
		dropDownMap.put("ageGroup", selectedAge);
		
		return "generaluser_factors";
	}

	public void compare(List<AdminChartDTO> users) {

		int maleCount = 0, femaleCount = 0, otherCount = 0;
		int maleAVG = 0, femaleAVG = 0, otherAVG = 0;
		int under18 = 0, to1824 = 0, to2539 = 0, to4064 = 0, over65 = 0;
		int under18AVG = 0, to1824AVG = 0, to2539AVG = 0, to4064AVG = 0, over65AVG = 0;
		
		if (selectedItem.equals("Department")) { // Not yet implemented

			// users.get(i).getDepartment();

		} else if (selectedItem.equals("Gender")) {
			
			typeAmount = new ArrayList<>();
			avgAmount = new ArrayList<>();
			
			for (int i = 0; i < users.size(); i++) {

				if (users.get(i).getGender().equals("Male")) {

					maleAVG = maleAVG + users.get(i).getPcm();
					maleCount++;

				} else if (users.get(i).getGender().equals("Female")) {

					femaleAVG = femaleAVG + users.get(i).getPcm();
					femaleCount++;

				} else { // Other
					
					otherAVG = otherAVG + users.get(i).getPcm();
					otherCount++;
				}
			}
			
			if(maleCount != 0)
			maleAVG = (maleAVG / maleCount);
			if(femaleCount != 0)
			femaleAVG = (femaleAVG / femaleCount);
			if(otherCount != 0)
			otherAVG = (otherAVG / otherCount);
			
			avgAmount.add(maleAVG);
			avgAmount.add(femaleAVG);
			avgAmount.add(otherAVG);
			typeAmount.add(maleCount);
			typeAmount.add(femaleCount);
			typeAmount.add(otherCount);
			type = 2;

		} else if (selectedItem.equals("Age Group")) {

			typeAmount = new ArrayList<>();
			avgAmount = new ArrayList<>();
			
			for(int i = 0; i < users.size(); i++) {
				
				if(users.get(i).getAgeGroup().equals("Under 18")) {
					
					under18AVG = under18AVG + users.get(i).getPcm();
					under18++;
					
				}else if(users.get(i).getAgeGroup().equals("18 to 24")) {
					
					to1824AVG = to1824AVG + users.get(i).getPcm();
					to1824++;
					
				}else if(users.get(i).getAgeGroup().equals("25 to 39")) {
					
					to2539AVG = to2539AVG + users.get(i).getPcm();
					to2539++;
					
				}else if(users.get(i).getAgeGroup().equals("40 to 64")) {
					
					to4064AVG = to4064AVG + users.get(i).getPcm();
					to4064++;
					
				}else {
					
					over65AVG = over65AVG + users.get(i).getPcm();
					over65++;
				}
			}
			
			if(under18 != 0)
			under18AVG = (under18AVG / under18);
			if(to1824 != 0)
			to1824AVG = (to1824AVG / to1824);
			if(to2539 != 0)
			to2539AVG = (to2539AVG / to2539);
			if(to4064 != 0)
			to4064AVG = (to4064AVG / to4064);
			if(over65 != 0)
			over65AVG = (over65AVG / over65);
			
			avgAmount.add(under18AVG);
			avgAmount.add(to1824AVG);
			avgAmount.add(to2539AVG);
			avgAmount.add(to4064AVG);
			avgAmount.add(over65AVG);
			typeAmount.add(under18);
			typeAmount.add(to1824);
			typeAmount.add(to2539);
			typeAmount.add(to4064);
			typeAmount.add(over65);
			type = 3;
		}
	}

	public List<AdminChartDTO> filterSelected(List<AdminChartDTO> users) { // This will be for comparing PCM only

		List<AdminChartDTO> tempList = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {

			if (users.get(i).isSelect()) {

				tempList.add(users.get(i));
			}
		}

		return tempList;
	}

	// Getters and Setters-------------------------------------

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getSelectedGender() {
		return selectedGender;
	}

	public void setSelectedGender(String selectedGender) {
		this.selectedGender = selectedGender;
	}

	public String getSelectedAge() {
		return selectedAge;
	}

	public void setSelectedAge(String selectedAge) {
		this.selectedAge = selectedAge;
	}

	public List<String> getDropDownItems() {
		return dropDownItems;
	}

	public void setDropDownItems(List<String> dropDownItems) {
		this.dropDownItems = dropDownItems;
	}

	public List<String> getGenderDrop() {
		return genderDrop;
	}

	public void setGenderDrop(List<String> genderDrop) {
		this.genderDrop = genderDrop;
	}

	public List<String> getAgeDrop() {
		return ageDrop;
	}

	public void setAgeDrop(List<String> ageDrop) {
		this.ageDrop = ageDrop;
	}

	public List<Integer> getTypeAmount() {
		return typeAmount;
	}

	public void setTypeAmount(List<Integer> typeAmount) {
		this.typeAmount = typeAmount;
	}

	public List<Integer> getAvgAmount() {
		return avgAmount;
	}

	public void setAvgAmount(List<Integer> avgAmount) {
		this.avgAmount = avgAmount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
