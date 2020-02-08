/***************************************************************************f******************u************zz*******y**
 * File: EmployeeController.java
 * Course materials (20W) CST 8277
 * @author (original) Mike Norman
 *
 */
package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.PersonDao;
import com.orgfitech.model.PersonDTO;

@Named("orgfitechController")
@ApplicationScoped
public class OrgfitechController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected ExternalContext externalContext;

	protected PersonDao personDao;

	protected List<PersonDTO> persons;

	@Inject
	protected PersonDTO person;

	@Inject
	public OrgfitechController(PersonDao personDao) {
		this.personDao = personDao;
	}

	public List<PersonDTO> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonDTO> persons) {
		this.persons = persons;
	}

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}

	public void loadPersons() {
		setPersons(personDao.readAllPerson());
	}

	public String displayAllUsers() {
		loadPersons();
		return "users";
	}

	public String validateLogin() {

		if (person.getFirstName() != null && person.getPassword() != null) {

			if (person.getFirstName().equals("admin") && (person.getPassword().equals("password"))) {
				
				return "mainpage";
			} else {

				persons = personDao.readAllPerson();

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
