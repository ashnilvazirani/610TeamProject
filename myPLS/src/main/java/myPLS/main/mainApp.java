package myPLS.main;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import myPLS.controllers.RegistrationController;

public class mainApp {
	
	private final static RegistrationController registraionController = new RegistrationController();

	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		
		get("/", (request, response) -> {
            return registraionController.getRegistrationPage();
        });
		
		post("/registerUser", (request, response) -> {
           return registraionController.registerUser(request);
        });
		
		post("/mailVerified",(request,response) -> {
			return registraionController.authoriseUser(request);
		});
		
		post("/resetPassword",(request,response) -> {
			return registraionController.resetPassword(request);
		});
		
		post("/loginPage", (request, response) -> {
            return registraionController.getLoginPage();
        });
		
		post("/logIn",(request,response) -> {
			return registraionController.logIn(request);
		});
		
	}
	
	

}
