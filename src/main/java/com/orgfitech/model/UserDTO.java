/**
 *
 *
 */


package com.orgfitech.model;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("userDTO")
@RequestScoped
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String department;
    protected String password;
    protected int accesslevel;
    protected int orgID;
    protected int pcm;
    
    protected boolean editable;
    //protected int accessstate;
    //protected int accessflag;
    
    
        

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

   public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
   
    public String getLastName() {
        return lastName;
    }
   
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

   
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }

    
    public String getPassword() {
       return password;
    }
  
    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccesslevel() {
        return accesslevel;
    }
    
    public void setAccesslevel(int accesslevel) {
        this.accesslevel = accesslevel;
    }
    
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    public int getOrgID() {
		return orgID;
	}

	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}
    
    public int getPcm() {
		return pcm;
	}

	public void setPcm(int pcm) {
		this.pcm = pcm;
	}

	public void resetPerson() {
        this.firstName = "";
        this.lastName = "";
        this.email ="";
        this.department="";
        this.password="";
        this.accesslevel=0;
    }
    
   // public int getAccessstate() {
      //  return accessstate;
   // }
    
    //public void setAccessstate(int accessstate) {
      //  this.accessstate = accessstate;
    //} 
    
    //public int getAccessflag() {
        //return accessflag;
    //}
    
    //public void setAccessflag(int accessflag) {
        //this.accessflag = accessflag;
    //} 

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserDTO)) {
            return false;
        }
        UserDTO other = (UserDTO) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Person [id=")
            .append(id)
            .append(", ");
        if (firstName != null) {
            builder
                .append("firstName=")
                .append(firstName)
                .append(", ");
        }
        if (lastName != null) {
            builder
                .append("lastName=")
                .append(lastName)
                .append(", ");
        }
        if (email != null) {
            builder
                .append("email=")
                .append(email)
                .append(", ");
        }
        if (department != null) {
            builder
                .append("department=")
                .append(department)
                .append(", ");
        }
        
        if (accesslevel != 0) {
            builder
                .append("accesslevel=")
                .append(accesslevel)
                .append(", ");
        }
       builder.append("]");
        return builder.toString();
    }

}