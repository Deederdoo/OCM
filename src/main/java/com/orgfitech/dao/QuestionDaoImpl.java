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

import com.orgfitech.jsf.CompareController;
import com.orgfitech.jsf.ConnectionManager;
import com.orgfitech.jsf.FactorController;
import com.orgfitech.model.QuestionDTO;

public class QuestionDaoImpl implements QuestionDao, Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String INSERT_QUESTION_PERSON_ANSWER = "INSERT INTO PERSON_SURVEY (SurveyID, UserID, PCM, GENDER, AGE_GROUP) "
			+ "VALUE (?,?,?,?,?);";
	
	private static final String INSERT_QUESTION_ANSWER = "INSERT INTO QUESTION_ANSWER (UserID, SurveyID, Score, OrgID) "
			+ "VALUE (?,?,?,?);";
	
	private static final String READ_BY_ASS_ID = "select * from question where surveyid = (?)";

	private static final String READ_ALL = "select * from question";
	
	private static final String INSERT_QUESTION =
    		"INSERT INTO QUESTION (SURVEYID, IDFAC, DETAILS) "
    		+ "VALUES (?,?,?);";

	protected Connection conn;

	protected PreparedStatement readAllassIDPstmt;
	
	protected PreparedStatement readAllPstmt;
	
	protected PreparedStatement createQuestionPersonPstmt;

	protected PreparedStatement createAnswerPstmt;
	
	protected PreparedStatement createPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {

			conn = ConnectionManager.INSTANCE.getConnection();
			
			readAllassIDPstmt = conn.prepareStatement(READ_BY_ASS_ID);
			
			readAllPstmt = conn.prepareStatement(READ_ALL);
			
			createQuestionPersonPstmt = conn.prepareStatement(INSERT_QUESTION_PERSON_ANSWER);

			createAnswerPstmt = conn.prepareStatement(INSERT_QUESTION_ANSWER);
			
			createPstmt = conn.prepareStatement(INSERT_QUESTION);

		} catch (Exception e) {
			System.out.println("TEST: " + readAllPstmt);
			System.out.println("something went wrong getting connection from database: ");
			e.printStackTrace();
		}
	}

	@PreDestroy
	protected void closeConnectionAndStatements() {
		try {

			System.out.println("CLOSED 3");
			
			readAllassIDPstmt.close();
			
			readAllPstmt.close();
			
			createQuestionPersonPstmt.close();
			
			createAnswerPstmt.close();
			
			createPstmt.close();

			conn.close();
		} catch (Exception e) {
			System.out.println("something went wrong getting connection from database: ");
		}
	}
	
	public List<QuestionDTO> readAllQuestionsByAssID(int assID) {
		
		List<QuestionDTO> question = new ArrayList<>();
		
		System.out.println("ASSID: " + assID);
		
		try {
			readAllassIDPstmt.setInt(1, assID);
			ResultSet rs = readAllassIDPstmt.executeQuery();

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
	
	public void createQuestionAnswer(double score) {
		
		try {
			createAnswerPstmt.setInt(1, UserDaoImpl.usersOrgIDMap.get("userID"));
			createAnswerPstmt.setInt(2, FactorController.genUserAssID.get("guID"));
			createAnswerPstmt.setDouble(3, score);
			createAnswerPstmt.setInt(4, UserDaoImpl.usersOrgIDMap.get("userOrgID"));
			createAnswerPstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createQuestionPersonAnswer(int score) {
		
		try {
			createQuestionPersonPstmt.setInt(1, FactorController.genUserAssID.get("guID"));
			createQuestionPersonPstmt.setInt(2, UserDaoImpl.usersOrgIDMap.get("userID"));
			createQuestionPersonPstmt.setInt(3, score);
			createQuestionPersonPstmt.setString(4, CompareController.dropDownMap.get("gender"));
			createQuestionPersonPstmt.setString(5, CompareController.dropDownMap.get("ageGroup"));
			createQuestionPersonPstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
