package com.orgfitech.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("adminChartDTO")
@SessionScoped
public class AdminChartDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int userID;
	private String firstName;
	private String lastName;
	private String department;
	private String gender;
	private String ageGroup;
	private int pcm;
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public int getPcm() {
		return pcm;
	}
	public void setPcm(int pcm) {
		this.pcm = pcm;
	}
}
