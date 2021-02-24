package myPLS.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import myPLS.DAO.RITUserDAO;
import myPLS.DAO.RegistrationDAO;
import myPLS.beans.User;
import spark.Request;

public class RegistrationService {
	
	private static RegistrationDAO registrationDAO;
	private static RITUserDAO ritUserDAO;
	private static AuthenticationMailService authMailService;
	
	public RegistrationService() {
		registrationDAO = new RegistrationDAO();
		ritUserDAO = new RITUserDAO();
		authMailService = new AuthenticationMailService();
	}

	public boolean registerUser(Request request) {
		StringBuilder fname = new StringBuilder(request.queryParams("fname") != null ? request.queryParams("fname") : "anonymous");
        StringBuilder lname = new StringBuilder(request.queryParams("lname") != null ? request.queryParams("lname") : "anonymous");
        String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
        Map<String, Object> map = new HashMap<>();
        StringBuilder name = fname.append(" ").append(lname);
        map.put("name", name);
        map.put("email", email);
        map.put("authorized", false);
        Optional<User> user = checkRITUser(email);
        if(user.isPresent()) {
        	User userValue = user.get();
        	boolean result = registrationDAO.saveUser(map);
        	authMailService.sendAuthMail(name.toString(), email,userValue.getRole());
        	return result;

        }else {
        	return false;
        }
	}
	
	public void updateAuthorization(Request request) {
        String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
        String role = request.queryParams("role") != null ? request.queryParams("role") : "unknown";
        registrationDAO.updateUser(email,role);
	}
	
	public boolean resetPassword(Request request) {
        String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
		User user = registrationDAO.getUser(email);
		if(user.getEmail().equalsIgnoreCase(email) && user.isAuthorized()) 
			return true;
		else
			return false;
	}
	
	public boolean updatePassword(Request request) {
        String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
        String password = request.queryParams("password") != null ? request.queryParams("password") : "unknown";
        return registrationDAO.updatePassword(email, password);
	}
	
	private Optional<User> checkRITUser(String email) {
		List<User> users = ritUserDAO.getRITUsers();
		return users.stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
	}
	
	public boolean logIn(Request request) {
        String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
        String password = request.queryParams("password") != null ? request.queryParams("password") : "unknown";
		User user = registrationDAO.getUser(email);
		if(user.getEmail().equalsIgnoreCase(email) && user.isAuthorized() && user.getPassword().equals(password)) 
			return true;
		else
			return false;
	}
}
