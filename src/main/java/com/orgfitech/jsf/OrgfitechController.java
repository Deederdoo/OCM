/***************************************************************************f******************u************zz*******y**
 * File: EmployeeController.java
 * Course materials (20W) CST 8277
 * @author (original) Mike Norman
 *
 */
package com.orgfitech.jsf;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("orgfitechController")
@RequestScoped
public class OrgfitechController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected ExternalContext externalContext;

	//1: mainpage
	//2: generaluser_mainpage
	//3: 
	
	public String navigatePages(int pageID) {
		
		if(pageID == 1) {
			
			return "mainpage.xhtml?faces-redirect=true";
		}
		
		if(pageID == 2) {
			
			return "generaluser_mainpage.xhtml?faces-redirect=true";
		}
		
		return null;
	}
}
