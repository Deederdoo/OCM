package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.AssessmentDao;
import com.orgfitech.dao.UserDaoImpl;
import com.orgfitech.model.AssessmentDTO;
import com.orgfitech.model.UserDTO;

@Named("assessmentController")
@SessionScoped
public class AssessmentController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String assName;
	
	protected String assType;
	
	protected boolean finished;

	public static HashMap<Integer, String> assNameMap;

	public static Map<String, Integer> assIdMap;

	@Inject
	protected ExternalContext externalContext;

	protected AssessmentDao assessmentDao;

	protected List<AssessmentDTO> assessments;

	@Inject
	protected AssessmentDTO assessment;

	@Inject
	public AssessmentController(AssessmentDao assesmentDao) {
		this.assessmentDao = assesmentDao;
	}

	public String getAssType() {
		return assType;
	}

	public void setAssType(String assType) {
		this.assType = assType;
	}

	public void setAssessments(List<AssessmentDTO> assessments) {
		this.assessments = assessments;
	}

	public List<AssessmentDTO> getAssessments() {
		return assessments;
	}

	public AssessmentDTO getAssessment() {
		return assessment;
	}

	public void setAssessment(AssessmentDTO assessment) {
		this.assessment = assessment;
	}

	public String getAssName() {

		return assName;
	}

	public void setAssName(String assName) {

		this.assName = assName;
	}

	public void loadAssessments() {
		setAssessments(assessmentDao.readAllAsessments());
		
		if(assNameMap != null) {
			
			assNameMap.clear();
		}
		
		if(assessments != null && !assessments.isEmpty() && assessments.size() > 0) {
			
			assNameMap = new HashMap<>();
		
			// Loads the assessment name to later compare in validator
			for (int i = 0; i < assessments.size(); i++) {
				
				assNameMap.put(i, assessments.get(i).getAssessmentName());
			}
		}
	}

	public String displayAssessments() {
		loadAssessments();
		return "main_assessments";
	}
	
	public void genUserLoadAssessments() {
		setAssessments(assessmentDao.readAllAsessments());
	}
	
	public String displayGenAssessments() {
		genUserLoadAssessments();
		
		return "generaluser_assessments";
	}
	
	public boolean notTaken(int assID) {
		
		List<UserDTO> tempList = assessmentDao.readFinished();
		
		for(int i = 0; i < tempList.size(); i++) {
			
			if(tempList.get(i).getAccessstate() == assID && tempList.get(i).getId() == UserDaoImpl.usersOrgIDMap.get("userID")) {
				
				finished(true);
				
				return false;
			}
		}
		
		finished(false);
		
		return true;
	}
	
	public boolean getFinished() {
		
		return finished;
	}
	
	public void finished(boolean isFinished) {
		
		this.finished = isFinished;
	}

	public int getAssIdByName(String name) {

		int id = assessmentDao.getIdByName(name);

		return id;
	}

	public void createAssessment() {

		assessment = new AssessmentDTO();

		assessment.setType(assType);
		assessment.setAssessmentName(assName);
		assessment.setDate(new Date());
		assessment.setLegacy(false);
		assessment.setAvgPCM(0);
		assessment.setOrgID(UserDaoImpl.usersOrgIDMap.get("userOrgID"));
		System.out.println("ORGIDASS: " + UserDaoImpl.usersOrgIDMap.get("userOrgID"));
		assessmentDao.createAssessment(assessment);

		assIdMap = new HashMap<>();
		assIdMap.clear();
		assIdMap.put("assId", getAssIdByName(assName));
	}
	
	public String deleteAssessmentById(int id) {
		
		assessmentDao.deleteAssessmentById(id);
		
		return "main_assessments.xhtml?faces-redirect=true";
	}
}


//.̴̢̢̨̧̡̡̧̛̛̟̰̟̖͖͇̻̦̱͕̭̹͎̫͔̼͙̩̣͚͙̭̬͔̺̟̹̘̠̙͚̰̟̹̬̭͙̼̜̟͚̞̭̟͔͚̣̰̟͇͖̞̱͔͔̞͇̜͈̗̘͔̭̱̳̜̻͈̮̠̯͓̝̭̣̘͈̲͇̳͓̦̤̪̼̺͆͛̽͌͆͐͂̂̏̔͌̉͌͛͑̉̈́̋̑́̆́̆̏̂̈̓̐̐̈̄̿͂́̌͂͂͆̄̒̐̊̀̅͛̃̽̂͌̾̍̒̈́̈́̽̎̽̇́͑̈͒̌͛̂̍͋̎̄̓̄̆̃̉̆̐͗͊̓͗͒͆̌͐͗̃̾̚̕͘͘͘͘͜͜͝͝͝͝͝ͅͅͅͅͅ.̶̡̢̡̧̡̢̢̢̛͍̬̬̞̖̝͕̩̻̪̱̳̯̤̖̘͎̹͔͉̝̞͉̜̤̼͔̹̥̠͚̗͙͚̗͍̯͎̜̙̼̯̤͎̮̗̺̙͈̼̘͈̯̪͍̞͖͙̜̗̱̜͈͔̯̯̹̪̗͎̤͍̫̟̲͔͚̯͔̯͖̹̪̝̗̪̯̏̈́̇̔̇̊́̋͛͛̆̏̄̈́͆̉̓͂̑́̋̍̄̿̈́̀͆͋̐̈́̍̃̔̆̉͛̾̓̂̃͌̊̍̽̒̎̾̄̽͗͊̅̓͊̂͌̎̆̌̉̅́̽̋̉̿͛̆͌͒̂͛͂͆͋̂̀͆̔͛̂͛͌̀̅̆̂͌̾͘͘̕̕͘̚͜͜͜͝͝͝͠ͅͅ.̴̢̢̨̢̡̧͔͎͖͔͕̞͔̠̮̠͖̻̝̱͕̰̙̬̫̣̻͈͓̪̺̜͇̰͈̫̖͖̫̯̪͕̪̖͉͚̭̫̰̻̖̳̪̹̪̞̺͙̦̩͖͉̳̟̳̬̜͇̹̲͇̹͕̳̖̝̮̦̺̺̭̯̲̟̜͙̗̤̝̯͕̜̀͒͆̇͑̑͆̌̏͗̂̈̆͒̎̃̌̈͆̔̓̿́̍̊̏̃̊̽̎̅̈́̈́̐̆͂̂̽̅͛̃́͋͑̈́́̐̿́͗̀̎̈́̐͋́̇̇̍͛̾̽͋̏̀̾̑̂̒͑̋̓͒͌͌͊͛͆̊́͋́͆̌̚̚͘̚̚̕͘̕͘͘̚͜͜͝͝͝͠ͅͅͅͅͅͅ.̸̧̨̧̧̨̨̢̛̛̛̛̤̭̺͉͉͖̜̯͍̱͇̯̣̟͍͙͎̬͔̦͓͕̪̥̫͍̰̫͇̫̤̟̪̗̩̙̥̩̺̙̟̰͉͍͉͓͍͉̮̭̝͔̗̖͔͍̱͉̩͓̲̥͙̰̫͈̝͖̮̙͙̩̘̰̻̫̪͉̝͚̼̩̱͖̲̱̤̣̦̹͓̋̂͊̽́̇̉͑̎̀͗̌̈́̈́́̊͌̋͑͆͂̈́͆͆̀̄̓̌̏̽͊̐͆͂̽̄̊̽̒̐̄̑̾͛͌̒̋̾̍͗̓̀̐̆̈́͌̋̋̂̽̀̾́͂͂̆̍̈́̅͆͐̓̈́͑̀́͋̅̋̄̍̏̚͘̕̚̕͘͝͠͝ 