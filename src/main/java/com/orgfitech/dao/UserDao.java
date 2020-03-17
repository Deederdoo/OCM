/***************************************************************************f******************u************zz*******y**
 * File: EmployeeDao.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 *
 */
package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.UserDTO;

/**
 * Description: API for the database C-R-U-D operations
 */
public interface UserDao {


    public boolean validateLogin1(String user, String password);

    public boolean validateLogin2(String user, String password);


    public List<UserDTO> readAllPerson();

    /**
     * createEmployee method
     * 
     * @param employee
     */
    public UserDTO createUser(String firstname, String lastname, String email, String department, String password, int accessLevel);

    /**
     * readEmployee method
     * 
     * @param employeeId
     */
    // public PersonDTO readUserById(int userId);

    /**
     * updateEmployee method
     * 
     * @param employee
     */
    public void updateUser(UserDTO user);

    /**
     * deleteEmployee method
     * 
     * @param employeeId
     */
    public void deleteUserById(int userId);



}