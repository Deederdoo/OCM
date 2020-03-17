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
import com.orgfitech.model.FactorDTO;

public class FactorDaoImpl implements FactorDao, Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String USER_DS_JNDI = "java:comp/env/jdbc/ocm";

	private static final String READ_BY_ID = "select * from factor where surveyid = (?);";
	
	private static final String READ_ALL = "select * from factor";
	
	private static final String INSERT_FACTOR =
    		"INSERT INTO FACTOR (SURVEYID, IDFAC, DETAILS, AVGFACTORPCM) "
    		+ "VALUES (?,?,?,?);";

	protected Connection conn;
	
	protected PreparedStatement readByIDPstmt;
	
	protected PreparedStatement readAllPstmt;

	protected PreparedStatement createPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {

			conn = ConnectionManager.INSTANCE.getConnection();
			
			readByIDPstmt = conn.prepareStatement(READ_BY_ID);
			
			readAllPstmt = conn.prepareStatement(READ_ALL);
			
			createPstmt = conn.prepareStatement(INSERT_FACTOR);

		} catch (Exception e) {
			System.out.println("TEST: " + readAllPstmt);
			System.out.println("something went wrong getting connection from database: ");
			e.printStackTrace();
		}
	}

	@PreDestroy
	protected void closeConnectionAndStatements() {
		try {

			System.out.println("CLOSED 6");
			
			readByIDPstmt.close();
			
			readAllPstmt.close();
			
			createPstmt.close();

			conn.close();
		} catch (Exception e) {
			System.out.println("something went wrong getting connection from database: ");
		}
	}
	
	public List<FactorDTO> readAllFactorsByID(int id){
		
		List<FactorDTO> tempFacs = new ArrayList<>();
		
		try {
			readByIDPstmt.setInt(1, id);
			ResultSet rs = readByIDPstmt.executeQuery();

			while (rs.next()) {
				FactorDTO newFac = new FactorDTO();
				newFac.setFactorID(rs.getInt("idfac"));
				newFac.setAssessmentID(rs.getInt("surveyid"));
				newFac.setDetails(rs.getString("details"));
				newFac.setAvgFactorPCM(rs.getInt("avgfactorpcm"));

				tempFacs.add(newFac);
			}
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("something went wrong getting ...: ");
			}
		} catch (SQLException e) {
			System.out.println("something went wrong getting .......: ");
		}
		
		return tempFacs;
	}

	public List<FactorDTO> readAllFactors() {

		List<FactorDTO> facs = new ArrayList<>();
		try {
			ResultSet rs = readAllPstmt.executeQuery();

			while (rs.next()) {
				FactorDTO newFac = new FactorDTO();
				newFac.setFactorID(rs.getInt("idfac"));
				newFac.setAssessmentID(rs.getInt("surveyid"));
				newFac.setDetails(rs.getString("details"));
				newFac.setAvgFactorPCM(rs.getInt("avgfactorpcm"));

				facs.add(newFac);
			}
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("something went wrong getting ...: ");
			}
		} catch (SQLException e) {
			System.out.println("something went wrong getting .......: ");
		}

		return facs;
	}

	public void createFactor(FactorDTO factor) {
		
		try {
			createPstmt.setInt(1, factor.getAssessmentID());
			createPstmt.setInt(2, factor.getFactorID());
			createPstmt.setString(3, factor.getDetails());
			createPstmt.setInt(4, factor.getAvgFactorPCM());
			createPstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
