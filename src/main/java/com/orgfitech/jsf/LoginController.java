package com.orgfitech.jsf;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.UserDao;
import com.orgfitech.model.UserDTO;

@Named("loginController")
@RequestScoped
public class LoginController implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
    protected ExternalContext externalContext;
    
    protected UserDao userDao;
    protected UserDTO userDTO;
    private String CurrentLoginUser;
    
    @Inject
    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }    
    
    
    public String validateLogin(String user, String password){
    	
    	System.out.println("Invalidated...");
    	externalContext.invalidateSession();
    	
        boolean adminUser=userDao.validateLogin1(user, password);
        boolean commonUser=userDao.validateLogin2(user, password);
        //adminUser = false;
       // commonUser = true;
        setCurrentLoginUser(user);
        if(adminUser) {
        	
            return "mainpage.xhtml?faces-redirect=true";
        }
        if(commonUser) {
        	
            return "generaluser_mainpage.xhtml?faces-redirect=true";
        }
        else {
            return "login";
        }
        
        
    }

    public String getCurrentLoginUser() {
        return CurrentLoginUser;
    }


    public void setCurrentLoginUser(String currentLoginUser) {
        CurrentLoginUser = currentLoginUser;
    }
    
    



}