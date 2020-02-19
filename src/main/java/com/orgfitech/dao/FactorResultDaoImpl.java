package com.orgfitech.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.sql.DataSource;

import com.orgfitech.model.FactorDefaultDTO;
import com.orgfitech.model.FactorResultDTO;

public class FactorResultDaoImpl implements FactorResultDao, Serializable {
    private static final long serialVersionUID = 1L;

    private static final String USER_DS_JNDI = "java:comp/env/jdbc/ocm";
    private static final String READ_FACTOR_RESULT_BY_USER_ID = "select * from FACTOR_ANSWER where UserID='";
    private static final String INSERT_FACTOR_RESULT =
            "INSERT INTO FACTOR_RESULT (UserID, SurveyID, FactorRank, FactorPCM, FactorID) "
            + "VALUES (?,?,?,?,?);";
    @Resource(name = "jdbc/ocm", lookup = USER_DS_JNDI)
    protected DataSource assDS;
    protected Connection conn;
    protected PreparedStatement readFactorResultByUserIdPstmt;
    protected PreparedStatement createFactorResultPstmt;
    
    @PostConstruct
    protected void buildConnectionAndStatements() {
        try {
            conn = assDS.getConnection();
            createFactorResultPstmt = conn.prepareStatement(INSERT_FACTOR_RESULT);
        } catch (Exception e) {
            System.out.println("TEST: " + readFactorResultByUserIdPstmt);
            System.out.println("something went wrong getting connection from database: ");
            e.printStackTrace();
        }
    }

    @PreDestroy
    protected void closeConnectionAndStatements() {
        try {
            createFactorResultPstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("something went wrong getting connection from database: ");
        }
    }
    
    @Override
    public List<FactorResultDTO> readFactorResultByUserId(int userId) {
        List<FactorResultDTO> factorResultDto = new ArrayList<>();
        
        try {
            readFactorResultByUserIdPstmt = conn.prepareStatement(READ_FACTOR_RESULT_BY_USER_ID+userId+"'");
            ResultSet rs = readFactorResultByUserIdPstmt.executeQuery();
            while (rs.next()) {
                FactorResultDTO newFactorResult = new FactorResultDTO();
                newFactorResult.setFactorAnswerID(rs.getInt("FactorAnswerID"));
                newFactorResult.setUserID(rs.getInt("UserID"));
                newFactorResult.setSurveyID(rs.getInt("surveyID"));
                newFactorResult.setFactorRank(rs.getInt("factorRank"));
                newFactorResult.setFactorPCM(rs.getInt("factorPCM"));
                newFactorResult.setFactorID(rs.getInt("factorID"));

                factorResultDto.add(newFactorResult);
            }
            readFactorResultByUserIdPstmt.close();
        } catch (Exception e) {
            System.out.println("TEST: " + readFactorResultByUserIdPstmt);
            System.out.println("something went wrong getting connection from database: ");
            e.printStackTrace();
        }

        return factorResultDto;
    }

    public void createFactorResult(FactorResultDTO factorResultDto) {
        
        try {
            createFactorResultPstmt.setInt(1, factorResultDto.getUserID());
            createFactorResultPstmt.setInt(2, factorResultDto.getSurveyID());
            createFactorResultPstmt.setInt(3, factorResultDto.getFactorRank());
            createFactorResultPstmt.setInt(4, factorResultDto.getFactorPCM());
            createFactorResultPstmt.setInt(5, factorResultDto.getFactorID());
            createFactorResultPstmt.execute();
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
