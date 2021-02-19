package myPLS.main;

import static spark.Spark.get;
import static spark.Spark.post;

import myPLS.controllers.RegistrationController;

public class mainApp {
	
	private final static RegistrationController registraionController = new RegistrationController();

	public static void main(String[] args) {
		get("/", (request, response) -> {
            return registraionController.getRegistrationPage();
        });
		
		post("/registerUser", (request, response) -> {
           return registraionController.registerUser(request);
        });
	}

}
