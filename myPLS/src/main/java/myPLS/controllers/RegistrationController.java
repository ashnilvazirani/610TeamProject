package myPLS.controllers;


import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

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
		 Map<String, Object> map = new HashMap<String, Object>();
		 Template resultTemplate;
		 Map<String, Object> result = registrationService.registerUser(request);
         if(result.isEmpty()) {
        	 try {
            	 resultTemplate = configuration.getTemplate("templates/authorizationMsg.ftl");
                 map.put("result", result);
                 resultTemplate.process(map, writer);
             } catch (Exception e) {
                 Spark.halt(500);
             }
         } else {
        	 try {
            	 resultTemplate = configuration.getTemplate("templates/registration.ftl");
                 resultTemplate.process(result, writer);
             } catch (Exception e) {
                 Spark.halt(500);
             }
         }
        
         return writer;
	}
	
	public StringWriter authoriseUser(Request request) {
		registrationService.updateAuthorization(request);
		StringWriter writer = new StringWriter();  
        try {
            Template formTemplate = configuration.getTemplate("templates/resetPassword.ftl");
            formTemplate.process(null, writer);
        } catch (Exception e) {
            Spark.halt(500);
        }
        return writer;
	}
	
	public StringWriter resetPassword(Request request) {
		StringWriter writer = new StringWriter();  
		if(registrationService.resetPassword(request)) {
			try {
				registrationService.updatePassword(request);
	            Template formTemplate = configuration.getTemplate("templates/studentDashboard.ftl");
	            formTemplate.process(null, writer);
	        } catch (Exception e) {
	            Spark.halt(500);
	        }
		} else {
			// render page to verify email address
			System.out.println("Please verify your email address");
		}
        return writer;
	}
	
	private void setConfiguration() {
        configuration.setClassForTemplateLoading(RegistrationController.class, "/");
	}

	public Object logIn(Request request) {
		StringWriter writer = new StringWriter();
		Template formTemplate;
		if(registrationService.logIn(request)) {
			try {
	            formTemplate = configuration.getTemplate("templates/studentDashboard.ftl");
	            formTemplate.process(null, writer);
	        } catch (Exception e) {
	            Spark.halt(500);
	        }
		} else {
			System.out.println("Invalid credentials");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "error");
			map.put("message", "Invalid Credentials!!");
			try {
	      formTemplate = configuration.getTemplate("templates/login.ftl");
	      formTemplate.process(map, writer);
	    } catch (Exception e) {
	      Spark.halt(500);
	    }
			
		}
        return writer;
	}
	
	public StringWriter getLoginPage() {
		StringWriter writer = new StringWriter();  
        try {
            Template formTemplate = configuration.getTemplate("templates/login.ftl");
            formTemplate.process(null, writer);
        } catch (Exception e) {
            Spark.halt(500);
        }

        return writer;
		
	}
	
}
