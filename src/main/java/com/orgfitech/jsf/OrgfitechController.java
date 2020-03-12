/***************************************************************************f******************u************zz*******y**
 * File: EmployeeController.java
 * Course materials (20W) CST 8277
 * @author (original) Mike Norman
 *
 */
package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.UserDao;
import com.orgfitech.model.UserDTO;

@Named("orgfitechController")
@SessionScoped
public class OrgfitechController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected ExternalContext externalContext;

	protected UserDao userDao;

	protected List<UserDTO> persons;

	@Inject
	protected UserDTO person;

	@Inject
	public OrgfitechController(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<UserDTO> getPersons() {
		return persons;
	}

	public void setPersons(List<UserDTO> persons) {
		this.persons = persons;
	}

	public UserDTO getPerson() {
		return person;
	}

	public void setPerson(UserDTO person) {
		this.person = person;
	}

	public void loadUsers() {
		setPersons(userDao.readAllPerson());
	}
//
//	public String displayAllUsers() {
//		loadPersons();
//		return "users";
//	}

	public String validateLogin() {

		if (person.getFirstName() != null && person.getPassword() != null) {

			if (person.getFirstName().equals("admin") && (person.getPassword().equals("password"))) {
				
				return "mainpage";
			} else {

				loadUsers();

				for (int i = 0; i < persons.size(); i++) {
					
					if (persons.get(i).getFirstName().toLowerCase().equals(person.getFirstName().toLowerCase())
							&& persons.get(i).getPassword().equals(person.getPassword())) {
						
						return "mainpage";
					}
				}
			}
		}
		return "login";
	}
}
