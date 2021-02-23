package myPLS.services;

import java.util.Map;

import myPLS.DAO.LearnerDAO;
import myPLS.beans.Learner;
import spark.Request;

public class LearnerService {
    private LearnerDAO learnerDAO;

    public LearnerService() {
		this.learnerDAO = new LearnerDAO();
	}
    public Map<Integer, Learner> getUserDetails(){
        return this.learnerDAO.getAllLearners();
    }
	public boolean addLearnerDetails(Request request) {
		//Connect with UI
        return true;
	}
}
