package com.orgfitech.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.sql.DataSource;

import com.orgfitech.jsf.ConnectionManager;
import com.orgfitech.model.AdminChartDTO;
import com.orgfitech.model.FactorResultDTO;
import com.orgfitech.model.UserDTO;

public class AdminChartDaoImpl implements AdminChartDao, Serializable {
	private static final long serialVersionUID = 1L;

	private static final String READ_AVG_FAC = "select score, userid "
			+ "from question_answer "
			+ "where surveyid = (?);";
	
	private static final String READ_ALL_TABLE = "select * "
			+ "from person_survey "
			+ "join person on person_survey.userid = person.userid "
			+ "where surveyid = (?);";

	private static final String READ_ALL_CHART = "select factor_answer.factorpcm, factor.details "
			+ "from factor_answer, factor where factor_answer.userid = (?) and factor.surveyid = (?);";
	
	private static final String READ_FACTOR_LABELS = "select details from factor where surveyid = (?)";

	protected Connection conn;
	
	protected PreparedStatement readAvgFacPstmt;

	protected PreparedStatement readAllTablesPstmt;

	protected PreparedStatement readAllChartsPstmt;
	
	protected PreparedStatement readFactorLabelsPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {

			conn = ConnectionManager.INSTANCE.getConnection();

			readAvgFacPstmt = conn.prepareStatement(READ_AVG_FAC);
			
			readAllTablesPstmt = conn.prepareStatement(READ_ALL_TABLE);

			readAllChartsPstmt = conn.prepareStatement(READ_ALL_CHART);
			
			readFactorLabelsPstmt = conn.prepareStatement(READ_FACTOR_LABELS);

		} catch (Exception e) {
			System.out.println("something went wrong getting connection from database: ");
			e.printStackTrace();
		}
	}

	@PreDestroy
	protected void closeConnectionAndStatements() {
		try {

			System.out.println("CLOSED 8");
			
			readAvgFacPstmt.close();
			
			readAllTablesPstmt.close();

			readAllChartsPstmt.close();
			
			readFactorLabelsPstmt.close();

			conn.close();
		} catch (Exception e) {
			System.out.println("something went wrong getting connection from database: ");
		}
	}
	
	public List<UserDTO> readAvgFac(int assID){
		
		List<UserDTO> tempList = new ArrayList<>();
		
		try {
			
			readAvgFacPstmt.setInt(1, assID);
			ResultSet rs = readAvgFacPstmt.executeQuery();

			while (rs.next()) {
				UserDTO tempDTO = new UserDTO();
				tempDTO.setId(rs.getInt("userid"));
				tempDTO.setScore(rs.getDouble("score"));

				tempList.add(tempDTO);
			}
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("something went wrong getting ...: ");
			}
		} catch (SQLException e) {
			System.out.println("something went wrong getting .......: ");
		}
		
		return tempList;
	}

	public List<FactorResultDTO> readAllCharts(int userID, int assID) {

		List<FactorResultDTO> data = new ArrayList<>();
		
		try {

			readAllChartsPstmt.setInt(1, userID);
			readAllChartsPstmt.setInt(2, assID);
			ResultSet rs = readAllChartsPstmt.executeQuery();

			while (rs.next()) {
				FactorResultDTO fr = new FactorResultDTO();
				fr.setFactorPCM(rs.getInt("factorpcm"));
				fr.setDetails(rs.getString("details"));

				data.add(fr);
			}
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("something went wrong getting ...: ");
			}
		} catch (SQLException e) {
			System.out.println("something went wrong getting .......: ");
		}

		return data;
	}

	public List<AdminChartDTO> readAllTables(int assID) {

		List<AdminChartDTO> data = new ArrayList<>();

		try {

			readAllTablesPstmt.setInt(1, assID);
			ResultSet rs = readAllTablesPstmt.executeQuery();

			while (rs.next()) {
				AdminChartDTO ac = new AdminChartDTO();
				ac.setUserID(rs.getInt("userid"));
				ac.setFirstName(rs.getString("firstname"));
				ac.setLastName(rs.getString("lastname"));
				ac.setDepartment(rs.getString("department"));
				ac.setGender(rs.getString("gender"));
				ac.setAgeGroup(rs.getString("age_group"));
				ac.setPcm(rs.getInt("pcm"));

				data.add(ac);
			}
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("something went wrong getting ...: ");
			}
		} catch (SQLException e) {
			System.out.println("something went wrong getting .......: ");
		}

		return data;
	}

	@Override
	public List<String> readFactorLabels(int assID) {
		
		List<String> factorLabels = new ArrayList<>();
		
		try {

			readFactorLabelsPstmt.setInt(1, assID);
			ResultSet rs = readFactorLabelsPstmt.executeQuery();

			while (rs.next()) {
				
				factorLabels.add(rs.getString("details"));
			}
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("something went wrong getting ...: ");
			}
		} catch (SQLException e) {
			System.out.println("something went wrong getting .......: ");
		}
		
		return factorLabels;
	}
}
