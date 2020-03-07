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

public class AdminChartDaoImpl implements AdminChartDao, Serializable{
	private static final long serialVersionUID = 1L;

	private static final String USER_DS_JNDI = "java:comp/env/jdbc/ocm";

	private static final String READ_ALL = "select * from survey where orgid = "; //------------------TODO

	@Resource(name = "jdbc/ocm", lookup = USER_DS_JNDI)
	protected DataSource assDS;

	protected Connection conn;

	protected PreparedStatement readAllPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {

			conn = assDS.getConnection();

			readAllPstmt = conn.prepareStatement(READ_ALL);

		} catch (Exception e) {
			System.out.println("something went wrong getting connection from database: ");
			e.printStackTrace();
		}
	}

	@PreDestroy
	protected void closeConnectionAndStatements() {
		try {

			readAllPstmt.close();

			conn.close();
		} catch (Exception e) {
			System.out.println("something went wrong getting connection from database: ");
		}
	}
	
	@Override
	public List<AdminChartDTO> readAllCharts() {
		
		List<AdminChartDTO> data = new ArrayList<>();
		
		try {
			ResultSet rs = readAllPstmt.executeQuery();

			while (rs.next()) {
				AdminChartDTO ac = new AdminChartDTO();
				ac.setId(rs.getInt("userid"));
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
