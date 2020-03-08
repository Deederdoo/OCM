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

import com.orgfitech.model.AdminChartDTO;
import com.orgfitech.model.FactorResultDTO;

public class AdminChartDaoImpl implements AdminChartDao, Serializable {
	private static final long serialVersionUID = 1L;

	private static final String USER_DS_JNDI = "java:comp/env/jdbc/ocm";

	private static final String READ_ALL_TABLE = "select person.userid, person.firstname, person.lastname, person.department, person_survey.pcm "
			+ "from person, person_survey where surveyid = (?);";

	private static final String READ_ALL_CHART = "select factor_answer.factorpcm, factor.details "
			+ "from factor_answer, factor where factor_answer.userid = (?) and factor.surveyid = (?);";

	@Resource(name = "jdbc/ocm", lookup = USER_DS_JNDI)
	protected DataSource assDS;

	protected Connection conn;

	protected PreparedStatement readAllTablesPstmt;

	protected PreparedStatement readAllChartsPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {

			conn = assDS.getConnection();

			readAllTablesPstmt = conn.prepareStatement(READ_ALL_TABLE);

			readAllChartsPstmt = conn.prepareStatement(READ_ALL_CHART);

		} catch (Exception e) {
			System.out.println("something went wrong getting connection from database: ");
			e.printStackTrace();
		}
	}

	@PreDestroy
	protected void closeConnectionAndStatements() {
		try {

			readAllTablesPstmt.close();

			readAllChartsPstmt.close();

			conn.close();
		} catch (Exception e) {
			System.out.println("something went wrong getting connection from database: ");
		}
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
				ac.setGender("Not Yet Implemented");
				ac.setAgeGroup("Not Yet Implemented");
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
}
