package myPLS.controllers;


import java.io.StringWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.services.RegistrationService;
import spark.Request;
import spark.Spark;

public class RegistrationController {
	private final Configuration configuration = new Configuration(new Version(2, 3, 0));
	private static RegistrationService registrationService;
	
	public RegistrationController() {
		setConfiguration();
		registrationService = new RegistrationService();
	}

	public StringWriter getRegistrationPage() {
		StringWriter writer = new StringWriter();  
        try {
            Template formTemplate = configuration.getTemplate("templates/registration.ftl");
            formTemplate.process(null, writer);
        } catch (Exception e) {
            Spark.halt(500);
        }

        return writer;
		
	}
	
	public StringWriter registerUser(Request request) {
		 StringWriter writer = new StringWriter();
         try {
             Template resultTemplate = configuration.getTemplate("templates/authorizationMsg.ftl");
             boolean result =  registrationService.registerUser(request);
             resultTemplate.process(result, writer);
         } catch (Exception e) {
             Spark.halt(500);
         }
         return writer;
	}
	
	private void setConfiguration() {
        configuration.setClassForTemplateLoading(RegistrationController.class, "/");

	}
}
