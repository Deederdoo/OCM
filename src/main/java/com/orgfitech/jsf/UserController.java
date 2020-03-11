package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.SessionMap;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;


import com.orgfitech.dao.UserDao;
import com.orgfitech.model.UserDTO;

@Named("userController")
@ApplicationScoped
public class UserController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    protected ExternalContext externalContext;

    protected UserDao userDao;

    protected List<UserDTO> users;

    protected boolean addPageVisiable = false;

    @Inject
    protected UserDTO newUser;

    @Inject    @SessionMap
    protected Map<String, Object> sessionMap;
    
    @Inject
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    
    private void logMsg(String msg) {
        ((ServletContext)externalContext.getContext()).log(msg);
    }

    
    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public UserDTO getNewUser() {
        return newUser;
    }

    public void setNewUser(UserDTO newUser) {
        this.newUser = newUser;
    }

    public void loadPersons() {
             
    	setUsers(userDao.readAllPerson());
    }

    public String displayAllUsers() {
        loadPersons();
        return "users";
    }
    public boolean isAddPageVisiable() {
        return addPageVisiable;
    }

    public void getUserPageVisiable(boolean addPageVisiable) {
        this.addPageVisiable = addPageVisiable;
    }


    public void showAddPage() {
       addPageVisiable = true;
    }

    public void hideAddPage() {
        addPageVisiable = false;
     
    }
   
    
    public String homePage() {
        return "mainpage";
    }
    
    public String logOut() {
        return "login";
    }
    
    
    /**
     *  deleteEmployee method
     *  @param empId
     *  @return String list-employees
     */
    public String deleteUser(int userId) {  
        userDao.deleteUserById(userId);
        return "users?faces-redirect=true";
    }


    public String editUser(UserDTO person) {
        
        person.setEditable(true);
        
        return "users";
    }
    
 public String updateUser(UserDTO person) {
        
        userDao.updateUser(person);
        
        return "users?faces-redirect=true";
    }
    
    
    
    
 public String cancelUpdateUser(UserDTO person) {
        
        person.setEditable(false);
        
        return "users";
    }
 
 public String addNewUser(String firstname, String lastname, String email, String department, String password) {
       
     userDao.createUser(firstname, lastname, email, department, password);
     newUser.resetPerson();
     return "users?faces-redirect=true";
 }
}