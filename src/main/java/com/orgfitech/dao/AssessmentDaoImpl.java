package com.orgfitech.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.sql.DataSource;

import com.orgfitech.jsf.ConnectionManager;
import com.orgfitech.model.AssessmentDTO;
import com.orgfitech.model.UserDTO;

public class AssessmentDaoImpl implements AssessmentDao, Serializable {
	private static final long serialVersionUID = 1L;

	private static final String READ_ALL = "select * from survey where orgid = (?)";

	private static final String INSERT_ASSESSMENT = "INSERT INTO SURVEY (SURVEYNAME, DATECREATED, ISLEGACY, AVGPCM, ORGID) "
			+ "VALUES (?,?,?,?,?);";

	private static final String GET_ID_BY_NAME = "SELECT SURVEYID FROM SURVEY " + "WHERE SURVEYNAME = (?);";
	
	private static final String GET_FINISHED_ASS = "SELECT SURVEYID, USERID FROM PERSON_SURVEY;";

	protected Connection conn;
	
	protected PreparedStatement readFinishedPstmt;

	protected PreparedStatement readByNamePstmt;

	protected PreparedStatement readAllPstmt;

	// protected PreparedStatement readByIdPstmt;
	protected PreparedStatement createPstmt;
	// protected PreparedStatement updatePstmt;
	// protected PreparedStatement deleteByIdPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {
			
			conn = ConnectionManager.INSTANCE.getConnection();
			
			readFinishedPstmt = conn.prepareStatement(GET_FINISHED_ASS);
			
			readByNamePstmt = conn.prepareStatement(GET_ID_BY_NAME);

			readAllPstmt = conn.prepareStatement(READ_ALL);

			// TODO - prepare rest of statements for rest of C-R-U-D
			// readByIdPstmt = conn.prepareStatement(READ_EMPLOYEE_BY_ID);
			createPstmt = conn.prepareStatement(INSERT_ASSESSMENT);
			// updatePstmt = conn.prepareStatement(UPDATE_EMPLOYEE_ALL_FIELDS);
			// deleteByIdPstmt = conn.prepareStatement(DELETE_EMPLOYEE_BY_ID);

		} catch (Exception e) {
			System.out.println("TEST: " + readAllPstmt);
			System.out.println("something went wrong getting connection from database: ");
			e.printStackTrace();
		}
	}

	@PreDestroy
	protected void closeConnectionAndStatements() {
		try {

			System.out.println("CLOSED 7");
			
			readFinishedPstmt.close();
			
			readByNamePstmt.close();

			readAllPstmt.close();
			
			createPstmt.close();

			conn.close();
		} catch (Exception e) {
			System.out.println("something went wrong getting connection from database: ");
		}
	}

	public int getIdByName(String name) {

		System.out.println("GETNAMEID NAME: " + name);

		int temp = -1;

		try {
			readByNamePstmt.setString(1, name);

			ResultSet rs = readByNamePstmt.executeQuery();
			while (rs.next()) {
				temp = rs.getInt("surveyid");
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get id by name broke");
		}

		return temp;
	}

	public List<UserDTO> readFinished(){
		
		List<UserDTO> finished = new ArrayList<>();
		
		try {
			ResultSet rs = readFinishedPstmt.executeQuery();

			while (rs.next()) {
				UserDTO temp = new UserDTO();
				temp.setAccessstate(rs.getInt("surveyid"));
				temp.setId(rs.getInt("userid"));
				
				finished.add(temp);
			}
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("something went wrong getting ...: ");
			}
		} catch (SQLException e) {
			System.out.println("something went wrong getting .......: ");
		}
		
		return finished;
	}
	
	public List<AssessmentDTO> readAllAsessments() {

		List<AssessmentDTO> assessments = new ArrayList<>();
		try {
			readAllPstmt.setInt(1, UserDaoImpl.usersOrgIDMap.get("userOrgID"));
			ResultSet rs = readAllPstmt.executeQuery();

			while (rs.next()) {
				AssessmentDTO newAss = new AssessmentDTO();
				newAss.setAssessmentID(rs.getInt("surveyid"));
				newAss.setAssessmentName(rs.getString("surveyname"));
				newAss.setDate(rs.getDate("datecreated"));
				newAss.setLegacy(rs.getBoolean("islegacy"));
				newAss.setAvgPCM(rs.getDouble("avgpcm"));

				assessments.add(newAss);
			}
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("something went wrong getting ...: ");
			}
		} catch (SQLException e) {
			System.out.println("something went wrong getting .......: ");
		}

		return assessments;
	}

	public void createAssessment(AssessmentDTO assessment) {

		java.sql.Date sqlDate = new java.sql.Date(assessment.getDate().getTime());

		try {
			createPstmt.setString(1, assessment.getAssessmentName());
			createPstmt.setDate(2, sqlDate);
			createPstmt.setBoolean(3, assessment.isLegacy());
			createPstmt.setDouble(4, assessment.getAvgPCM());
			createPstmt.setInt(5, assessment.getOrgID());
			createPstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
