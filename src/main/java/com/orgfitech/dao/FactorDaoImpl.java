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

import com.orgfitech.model.FactorDTO;

public class FactorDaoImpl implements FactorDao, Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String USER_DS_JNDI = "java:comp/env/jdbc/ocm";

	private static final String READ_ALL = "select * from factor";
	
	private static final String INSERT_FACTOR =
    		"INSERT INTO FACTOR (SURVEYID, IDFAC, DETAILS, AVGFACTORPCM) "
    		+ "VALUES (?,?,?,?);";

	@Resource(name = "jdbc/ocm", lookup = USER_DS_JNDI)
	protected DataSource assDS;

	protected Connection conn;
	
	protected PreparedStatement readAllPstmt;

	// protected PreparedStatement readByIdPstmt;
	protected PreparedStatement createPstmt;
	// protected PreparedStatement updatePstmt;
	// protected PreparedStatement deleteByIdPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {

			conn = assDS.getConnection();
			
			readAllPstmt = conn.prepareStatement(READ_ALL);

			// TODO - prepare rest of statements for rest of C-R-U-D
			// readByIdPstmt = conn.prepareStatement(READ_EMPLOYEE_BY_ID);
			createPstmt = conn.prepareStatement(INSERT_FACTOR);
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
	
	public List<FactorDTO> readAllFactorsByID(int id){
		
		List<FactorDTO> tempFacs = readAllFactors();
		
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
