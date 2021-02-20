package myPLS.services;

import java.util.HashMap;
import java.util.Map;

import myPLS.DAO.RegistrationDAO;
import myPLS.beans.User;
import spark.Request;

public class RegistrationService {
	
	private static RegistrationDAO registrationDAO;
	
	public RegistrationService() {
		registrationDAO = new RegistrationDAO();
	}
    public Map<Integer, User> getUserDetails(){
        return registrationDAO.getRegisteredUserDetails();
    }
	public boolean registerUser(Request request) {
		StringBuilder fname = new StringBuilder(request.queryParams("fname") != null ? request.queryParams("fname") : "anonymous");
        StringBuilder lname = new StringBuilder(request.queryParams("lname") != null ? request.queryParams("lname") : "anonymous");
        String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
        Map<String, Object> map = new HashMap<>();
        map.put("name", fname.append(" ").append(lname));
        map.put("email", email);
        map.put("authorized", false);
        // Map<String, User> userData = new HashMap<>();
        // User u =new User(fname.append(" ").append(lname).toString(), email, false);
        // userData.put("newUser", u);
        return registrationDAO.saveUser(map);
	}
}
