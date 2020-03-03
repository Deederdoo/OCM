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
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.orgfitech.model.FactorDefaultDTO;
import com.orgfitech.model.FactorResultDTO;
import com.orgfitech.model.UserDTO;

public class FactorResultDaoImpl implements FactorResultDao, Serializable {
    private static final long serialVersionUID = 1L;

    protected ServletContext sc;

    private static final String USER_DS_JNDI = "java:comp/env/jdbc/ocm";
    private static final String READ_FACTOR_RESULT_BY_USER_ID = "select * from FACTOR_ANSWER where UserID=?";
    private static final String FIND_USER_ID_BY_EMAIL = "select * from PERSON where Email=?";
    private static final String INSERT_FACTOR_RESULT =
            "INSERT INTO FACTOR_RESULT (UserID, SurveyID, FactorRank, FactorPCM, FactorID) "
            + "VALUES (?,?,?,?,?);";
    @Resource(name = "jdbc/ocm", lookup = USER_DS_JNDI)
    protected DataSource assDS;
    protected Connection conn;
    protected PreparedStatement readFactorResultByUserIdPstmt;
    protected PreparedStatement findUserIdByEmailPstmt;
    protected PreparedStatement createFactorResultPstmt;
    
    @Inject
    public FactorResultDaoImpl(ServletContext sc) {
        super();
        this.sc = sc;
    }
    
    private void myLog(String log) {
        sc.log(getClass().getSimpleName()+":"+log);
    }
    
    @PostConstruct
    protected void buildConnectionAndStatements() {
        try {
            conn = assDS.getConnection();
            findUserIdByEmailPstmt = conn.prepareStatement(FIND_USER_ID_BY_EMAIL);
            readFactorResultByUserIdPstmt = conn.prepareStatement(READ_FACTOR_RESULT_BY_USER_ID);
            createFactorResultPstmt = conn.prepareStatement(INSERT_FACTOR_RESULT);
        } catch (Exception e) {
            myLog("something went wrong getting connection from database: ");
            e.printStackTrace();
        }
    }

    @PreDestroy
    protected void closeConnectionAndStatements() {
        try {
            createFactorResultPstmt.close();
            findUserIdByEmailPstmt.close();
            readFactorResultByUserIdPstmt.close();
            conn.close();
        } catch (Exception e) {
            myLog("something went wrong getting connection from database: ");
        }
    }
    
    @Override
    public List<FactorResultDTO> readFactorResultByLoginEmail(String email) {
        List<FactorResultDTO> factorResultDto = new ArrayList<>();
        
        try {
            int userId;
            findUserIdByEmailPstmt.setString(1, email);
            ResultSet rsFindUser = findUserIdByEmailPstmt.executeQuery();
            if (rsFindUser.next()) {
                userId = rsFindUser.getInt("UserID");
                myLog("rsFindUser: "+userId);
            } else {
                myLog("Cannot find user via email "+email);
                return null;
            }
            
            readFactorResultByUserIdPstmt.setInt(1, userId);
            ResultSet rsFactorResult = readFactorResultByUserIdPstmt.executeQuery();
            while (rsFactorResult.next()) {
               
                FactorResultDTO newFactorResult = new FactorResultDTO();
                newFactorResult.setFactorAnswerID(rsFactorResult.getInt("FactorAnswerID"));
                newFactorResult.setUserID(rsFactorResult.getInt("UserID"));
                newFactorResult.setSurveyID(rsFactorResult.getInt("surveyID"));
                newFactorResult.setFactorRank(rsFactorResult.getInt("factorRank"));
                newFactorResult.setFactorPCM(rsFactorResult.getInt("factorPCM"));
                newFactorResult.setFactorID(rsFactorResult.getInt("factorID"));

                factorResultDto.add(newFactorResult);
            }
            myLog("rsFactorResult: "+factorResultDto.toString());
        } catch (Exception e) {
            myLog("something went wrong getting connection from database: ");
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
