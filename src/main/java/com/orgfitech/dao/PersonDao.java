/***************************************************************************f******************u************zz*******y**
 * File: EmployeeDao.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 *
 */
package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.PersonDTO;

/**
 * Description: API for the database C-R-U-D operations
 */
public interface PersonDao {

  public List<PersonDTO> readAllPerson();
    
    
    // C
    //public EmployeeDTO createEmployee(EmployeeDTO employee);
    // R
    //public EmployeeDTO readEmployeeById(int employeeId);
   
    // U
   // public void updateEmployee(EmployeeDTO employee);
    // D
   // public void deleteEmployeeById(int employeeId);

}