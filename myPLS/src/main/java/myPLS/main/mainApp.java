package myPLS.main;

import static spark.Spark.get;

import myPLS.controllers.RegistrationController;

public class mainApp {
	
	private final static RegistrationController registraionController = new RegistrationController();

	public static void main(String[] args) {
		get("/", (request, response) -> {
            return registraionController.getRegistrationPage();
        });
	}

}
