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

import com.orgfitech.model.QuestionDefaultDTO;

public class QuestionDefaultDaoImpl implements QuestionDefaultDao, Serializable{
	private static final long serialVersionUID = 1L;

	private static final String USER_DS_JNDI = "java:comp/env/jdbc/ocm";

	private static final String READ_ALL = "select * from question_default";

	@Resource(name = "jdbc/ocm", lookup = USER_DS_JNDI)
	protected DataSource assDS;

	protected Connection conn;

	protected PreparedStatement readAllPstmt;

	// protected PreparedStatement readByIdPstmt;
	// protected PreparedStatement createPstmt;
	// protected PreparedStatement updatePstmt;
	// protected PreparedStatement deleteByIdPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {

			conn = assDS.getConnection();
			readAllPstmt = conn.prepareStatement(READ_ALL);

			// TODO - prepare rest of statements for rest of C-R-U-D
			// readByIdPstmt = conn.prepareStatement(READ_EMPLOYEE_BY_ID);
			// createPstmt = conn.prepareStatement(INSERT_EMPLOYEE);
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
			
			System.out.println("CLOSED 2");
			
			readAllPstmt.close();
			// readByIdPstmt.close();
			// createPstmt.close();
			// updatePstmt.close();
			// deleteByIdPstmt.close();

			conn.close();
		} catch (Exception e) {
			System.out.println("something went wrong getting connection from database: ");
		}
	}
	
	public List<QuestionDefaultDTO> readAllDefaultQuestions() {
		
		List<QuestionDefaultDTO> questionD = new ArrayList<>();
		
		try {
			ResultSet rs = readAllPstmt.executeQuery();

			while (rs.next()) {
				QuestionDefaultDTO newQD = new QuestionDefaultDTO();
				newQD.setQuestionID(rs.getInt("questiondefaultid"));
				newQD.setFactorID(rs.getInt("factordefaultid"));
				newQD.setDetails(rs.getString("details"));

				questionD.add(newQD);
			}
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("something went wrong getting ...: ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("something went wrong getting .......: ");
		}

		return questionD;
	}

}
