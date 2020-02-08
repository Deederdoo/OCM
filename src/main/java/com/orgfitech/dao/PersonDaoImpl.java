/***************************************************************************f******************u************zz*******y**
 * File: EmployeeDaoImpl.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 *
 */
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

import com.orgfitech.model.PersonDTO;

public class PersonDaoImpl implements PersonDao, Serializable {
    private static final long serialVersionUID = 1L;

    private static final String USER_DS_JNDI =  "java:comp/env/jdbc/ocm";

    private static final String READ_ALL =  "select * from person";

    @Resource(name = "jdbc/ocm", lookup = USER_DS_JNDI)
    protected DataSource userDS;

    protected Connection conn;


    protected PreparedStatement readAllPstmt;

    //protected PreparedStatement readByIdPstmt;
    // protected PreparedStatement createPstmt;
    // protected PreparedStatement updatePstmt;
    // protected PreparedStatement deleteByIdPstmt;


    @PostConstruct
    protected void buildConnectionAndStatements() {
        try {

            conn = userDS.getConnection();
            readAllPstmt = conn.prepareStatement(READ_ALL);

            //TODO - prepare rest of statements for rest of C-R-U-D
            //readByIdPstmt = conn.prepareStatement(READ_EMPLOYEE_BY_ID);
            //createPstmt = conn.prepareStatement(INSERT_EMPLOYEE);
            // updatePstmt = conn.prepareStatement(UPDATE_EMPLOYEE_ALL_FIELDS);
            //deleteByIdPstmt = conn.prepareStatement(DELETE_EMPLOYEE_BY_ID);

        }
        catch (Exception e) {
        	System.out.println("TEST: " + readAllPstmt);
            System.out.println("something went wrong getting connection from database: ");
        	e.printStackTrace();
        }
    }


    @PreDestroy
    protected void closeConnectionAndStatements() {
        try {

            readAllPstmt.close();
            //readByIdPstmt.close();
            // createPstmt.close();
            // updatePstmt.close();
            // deleteByIdPstmt.close();

            conn.close();
        }
        catch (Exception e) {
            System.out.println("something went wrong getting connection from database: ");
        }
    }


    public List<PersonDTO> readAllPerson(){
        
        List<PersonDTO> persons = new ArrayList<>();
        try {
            ResultSet rs = readAllPstmt.executeQuery();
            
            while (rs.next()) {
                PersonDTO newPerson = new PersonDTO();
                newPerson.setId(rs.getInt("userid"));
                newPerson.setFirstName(rs.getString("firstname"));
                newPerson.setLastName(rs.getString("lastname"));
                newPerson.setEmail(rs.getString("email"));
                newPerson.setPassword(rs.getString("pass"));
                newPerson.setDepartment(rs.getString("department"));
                newPerson.setAccesslevel(rs.getInt("Access_Level"));
                
                persons.add(newPerson);
            }
            try {
                rs.close();
            }
            catch (Exception e) {
                System.out.println("something went wrong getting ...: ");
            }
        }
        catch (SQLException e) {
            System.out.println("something went wrong getting .......: ");
        }
        
        return persons;
    }
}