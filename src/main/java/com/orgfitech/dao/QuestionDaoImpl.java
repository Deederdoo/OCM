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

import com.orgfitech.model.QuestionDTO;

public class QuestionDaoImpl implements QuestionDao, Serializable {
	private static final long serialVersionUID = 1L;

	private static final String USER_DS_JNDI = "java:comp/env/jdbc/ocm";

	private static final String READ_ALL = "select * from question";
	
	private static final String INSERT_QUESTION =
    		"INSERT INTO QUESTION (SURVEYID, IDFAC, DETAILS) "
    		+ "VALUES (?,?,?);";

	@Resource(name = "jdbc/ocm", lookup = USER_DS_JNDI)
	protected DataSource qDS;

	protected Connection conn;

	protected PreparedStatement readAllPstmt;

	// protected PreparedStatement readByIdPstmt;
	protected PreparedStatement createPstmt;
	// protected PreparedStatement updatePstmt;
	// protected PreparedStatement deleteByIdPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {

			conn = qDS.getConnection();
			readAllPstmt = conn.prepareStatement(READ_ALL);

			// TODO - prepare rest of statements for rest of C-R-U-D
			// readByIdPstmt = conn.prepareStatement(READ_EMPLOYEE_BY_ID);
			createPstmt = conn.prepareStatement(INSERT_QUESTION);
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

	public List<QuestionDTO> readAllQuestions() {

		List<QuestionDTO> question = new ArrayList<>();

		try {
			ResultSet rs = readAllPstmt.executeQuery();

			while (rs.next()) {
				QuestionDTO newQ = new QuestionDTO();
				newQ.setQuestionID(rs.getInt("questionid"));
				newQ.setFactorID(rs.getInt("idfac"));
				newQ.setDetails(rs.getString("details"));

				question.add(newQ);
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

		return question;
	}

	public void createQuestion(int quesId, QuestionDTO question) {

		try {
			createPstmt.setInt(1, quesId);
			createPstmt.setInt(2, question.getFactorID());
			createPstmt.setString(3, question.getDetails());
			createPstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
