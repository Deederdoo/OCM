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

import com.orgfitech.model.AssessmentDTO;

public class AssessmentDaoImpl implements AssessmentDao, Serializable {
	private static final long serialVersionUID = 1L;

	private static final String USER_DS_JNDI = "java:comp/env/jdbc/ocm";

	private static final String READ_ALL = "select * from survey";

	private static final String INSERT_ASSESSMENT = "INSERT INTO SURVEY (SURVEYNAME, DATECREATED, ISLEGACY, AVGPCM) "
			+ "VALUES (?,?,?,?);";

	private static final String GET_ID_BY_NAME = "SELECT SURVEYID FROM SURVEY " + "WHERE SURVEYNAME = (?);";

	@Resource(name = "jdbc/ocm", lookup = USER_DS_JNDI)
	protected DataSource assDS;

	protected Connection conn;

	protected PreparedStatement readByNamePstmt;

	protected PreparedStatement readAllPstmt;

	// protected PreparedStatement readByIdPstmt;
	protected PreparedStatement createPstmt;
	// protected PreparedStatement updatePstmt;
	// protected PreparedStatement deleteByIdPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {

			conn = assDS.getConnection();

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

			readByNamePstmt.close();

			readAllPstmt.close();
			// readByIdPstmt.close();
			createPstmt.close();
			// updatePstmt.close();
			// deleteByIdPstmt.close();

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

	public List<AssessmentDTO> readAllAsessments() {

		List<AssessmentDTO> assessments = new ArrayList<>();
		try {
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
			createPstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
