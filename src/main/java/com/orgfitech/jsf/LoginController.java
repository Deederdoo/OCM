package com.orgfitech.jsf;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.UserDao;
import com.orgfitech.model.UserDTO;

@Named("loginController")
@ApplicationScoped
public class LoginController {

    private static final long serialVersionUID = 1L;

    @Inject
    protected ExternalContext externalContext;

    protected UserDao userDao;
    protected UserDTO userDTO;
    
    @Inject
    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }    
    
    
    public String validateLogin(String user, String password){
       
        boolean adminUser=userDao.validateLogin1(user, password);
        boolean commonUser=userDao.validateLogin2(user, password);
        //adminUser = false;
       // commonUser = true;
        
        if(adminUser) {
            return "mainpage";
        }
        if(commonUser) {
            return "generaluser_mainpage";
        }
        else {
            return "login";
        }
        
        
    }



}