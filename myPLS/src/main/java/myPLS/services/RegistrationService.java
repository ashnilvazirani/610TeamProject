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

	public Map<String, Object> registerUser(Request request) {
		StringBuilder fname = new StringBuilder(request.queryParams("fname") != null ? request.queryParams("fname") : "anonymous");
        StringBuilder lname = new StringBuilder(request.queryParams("lname") != null ? request.queryParams("lname") : "anonymous");
        String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
        Map<String, Object> map = new HashMap<>();
        StringBuilder name = fname.append(" ").append(lname);
        map.put("name", name);
        map.put("email", email);
        map.put("authorized", false);
        Map<String, Object> resultMap = new HashMap<>();
        Optional<User> user = checkRITUser(email);
        if(user.isPresent()) {
        	User userValue = user.get();
        	boolean result = registrationDAO.saveUser(map);
        	if(result) {
        		authMailService.sendAuthMail(name.toString(), email,userValue.getRole());
        	} else {
        		resultMap.put("status", "error");
            	resultMap.put("message", "User already exists.");
        	}
        }else {
        	resultMap.put("status", "error");
        	resultMap.put("message", "Not a RIT user");
        }
        return resultMap;
	}
	
	public void updateAuthorization(Request request) {
        String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
        String role = request.queryParams("role") != null ? request.queryParams("role") : "unknown";
        registrationDAO.updateUser(email,role);
	}
	
	public Map<String,Object> resetPassword(Request request) {
        String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
		User user = registrationDAO.getUser(email);
		Map<String, Object> map = new HashMap<>();
		if(user.getEmail().equalsIgnoreCase(email) && user.isAuthorized()) {
			map.put("updated", true);
			map.put("role", user.getRole());
		}
		else {
			map.put("updated", false);
			map.put("role", user.getRole());
		}
		return map;
			
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
	
	public Map<String,Object> logIn(Request request) {
        String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
        String password = request.queryParams("password") != null ? request.queryParams("password") : "unknown";
		User user = registrationDAO.getUser(email);
		Map<String, Object> map = new HashMap<>();
		if(user.getEmail() != null && user.getPassword()!= null && user.getEmail().equalsIgnoreCase(email) && user.isAuthorized() && user.getPassword().equals(password)) {
			map.put("validUser", true);
			map.put("role", user.getRole());
			map.put("userID", user.getUserID());
		}
		else {
			map.put("validUser", false);
			map.put("role", user.getRole());
			map.put("userID", user.getUserID());

		}
		return map;
	}
	
	public List<User> getUsersByRole(String role){
		return registrationDAO.getUsersByRole(role);
	}
}
