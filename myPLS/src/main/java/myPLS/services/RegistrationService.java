package myPLS.services;

import java.util.HashMap;
import java.util.Map;

import myPLS.DAO.RegistrationDAO;
import spark.Request;

public class RegistrationService {
	
	private static RegistrationDAO registrationDAO;
	
	public RegistrationService() {
		registrationDAO = new RegistrationDAO();
	}

	public boolean registerUser(Request request) {
		StringBuilder fname = new StringBuilder(request.queryParams("fname") != null ? request.queryParams("fname") : "anonymous");
        StringBuilder lname = new StringBuilder(request.queryParams("lname") != null ? request.queryParams("lname") : "anonymous");
        String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
        Map<String, Object> map = new HashMap<>();
        map.put("name", fname.append(" ").append(lname));
        map.put("email", email);
        map.put("authorized", false);
        return registrationDAO.saveUser(map);
	}
}
