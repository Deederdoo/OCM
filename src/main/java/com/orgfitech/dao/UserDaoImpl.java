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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.sql.DataSource;

import com.orgfitech.jsf.ConnectionManager;
import com.orgfitech.model.UserDTO;

public class UserDaoImpl implements UserDao, Serializable {
    private static final long serialVersionUID = 1L;
    
    public static Map<String, Integer> usersOrgIDMap;

    private static final String READ_ALL =  "select * from person";
    private static final String READ_USER_BY_ID = "select * from person where id = ?";
    private static final String DELETE_USER_BY_ID = "delete from person where userid = ?";
    private static final String INSERT_USER = "insert into person (firstname, lastname, email, department, pass, access_level, assess_status, pass_flag, orgid) "
    		+ "values (?,?,?,?,?,?,0,1,?);";
    private static final String UPDATE_USER_ALL_FIELDS = 
            "update person set FirstName=?, LastName=?, Email=?, Department=?, pass=?, access_level=? where UserID = ?";

    private static final String SEARCH_LOGIN_LEVEL1 = "select * from person where email=? and pass=? and access_level=1";
    private static final String SEARCH_LOGIN_LEVEL2 = "select * from person where email=? and pass=? and access_level=2";

    protected Connection conn;
    protected PreparedStatement readAllPstmt;
    protected PreparedStatement readByIdPstmt;
    protected PreparedStatement createPstmt;
    protected PreparedStatement updatePstmt;
    protected PreparedStatement deleteByIdPstmt;

    protected PreparedStatement searchLogin1stmt;
    protected PreparedStatement searchLogin2stmt;


    @PostConstruct
    protected void buildConnectionAndStatements() {
        try {
        	
            conn = ConnectionManager.INSTANCE.getConnection();
            readAllPstmt = conn.prepareStatement(READ_ALL);
            readByIdPstmt = conn.prepareStatement(READ_USER_BY_ID);
            createPstmt = conn.prepareStatement(INSERT_USER);
            updatePstmt = conn.prepareStatement(UPDATE_USER_ALL_FIELDS);
            deleteByIdPstmt = conn.prepareStatement(DELETE_USER_BY_ID);

            searchLogin1stmt= conn.prepareStatement( SEARCH_LOGIN_LEVEL1);
            searchLogin2stmt= conn.prepareStatement( SEARCH_LOGIN_LEVEL2);

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

        	System.out.println("CLOSED 1");
            readAllPstmt.close();
            readByIdPstmt.close();
            createPstmt.close();
            updatePstmt.close();
            deleteByIdPstmt.close();

            searchLogin1stmt.close();
            searchLogin2stmt.close();

            conn.close();

        }
        catch (Exception e) {
            System.out.println("something went wrong getting connection from database: ");
        }
    }


    public boolean validateLogin1(String user, String password) {

        try {
            searchLogin1stmt.setString(1, user);
            searchLogin1stmt.setString(2, password); 
            ResultSet rs = searchLogin1stmt.executeQuery();

            if(rs.next()) {
            	usersOrgIDMap = new HashMap<>();
            	usersOrgIDMap.put("userOrgID", rs.getInt("orgid"));
            	usersOrgIDMap.put("userID", rs.getInt("userid"));
                return true;
            }else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean validateLogin2(String user, String password) {

        ResultSet rs;  
        try {
            searchLogin2stmt.setString(1, user);
            searchLogin2stmt.setString(2, password); 
            rs = searchLogin2stmt.executeQuery();
            
            if(rs.next()) {
            	usersOrgIDMap = new HashMap<>();
            	usersOrgIDMap.put("userOrgID", rs.getInt("orgid"));
            	usersOrgIDMap.put("userID", rs.getInt("userid"));
                return true;
            }else {   
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public List<UserDTO> readAllPerson(){

        List<UserDTO> users = new ArrayList<>();
        try {
            ResultSet rs = readAllPstmt.executeQuery();

            while (rs.next()) {
                UserDTO newUser = new UserDTO();
                newUser.setId(rs.getInt("userid"));
                newUser.setFirstName(rs.getString("firstname"));
                newUser.setLastName(rs.getString("lastname"));
                newUser.setEmail(rs.getString("email"));
                newUser.setPassword(rs.getString("pass"));
                newUser.setDepartment(rs.getString("department"));
                newUser.setAccesslevel(rs.getInt("Access_Level"));

                users.add(newUser);
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

        return users;
    }

    /**
     * deleteEmployee method
     * @param: employeeId
     */
    public void deleteUserById(int userId){
        try {
            deleteByIdPstmt = conn.prepareStatement(DELETE_USER_BY_ID);
            deleteByIdPstmt.setString(1, String.valueOf(userId));

            deleteByIdPstmt.executeUpdate();

        }
        catch (SQLException e) {

        }

    }
    
    public UserDTO createUser(String firstname, String lastname, String email, String department, String password, int accessLevel) {

        try {

            createPstmt.setString(1, firstname);
            createPstmt.setString(2, lastname); 
            createPstmt.setString(3, email);
            createPstmt.setString(4, department);
            createPstmt.setString(5, password);
            createPstmt.setInt(6, accessLevel);
            createPstmt.setInt(7, usersOrgIDMap.get("userOrgID"));

            createPstmt.executeUpdate();

        }
        catch(SQLException e) {
            System.out.println("something went wrong accessing database:");
            e.printStackTrace();
        }

        return null;
    }

    /**
     *  readEmployee method
     *  @param employeeId
     */
    // public PersonDTO readUserById(int userId) {
    //     return new PersonDTO();
    //  }

    /**
     *  updateEmployee method
     *  @param employee
     */
    public void updateUser(UserDTO user) {

        try {

            updatePstmt.setString(1, user.getFirstName());
            updatePstmt.setString(2, user.getLastName()); 
            updatePstmt.setString(3, user.getEmail());
            updatePstmt.setString(4, user.getDepartment());
            updatePstmt.setString(5, user.getPassword());
            updatePstmt.setInt(6, user.getAccesslevel());
            updatePstmt.setInt(7, user.getId());
            updatePstmt.executeUpdate();

        }
        catch(SQLException e) {
            System.out.println("something went wrong accessing database:");
        }

    }















}