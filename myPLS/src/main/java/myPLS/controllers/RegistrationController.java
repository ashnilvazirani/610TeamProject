package myPLS.controllers;


import java.io.StringWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import spark.Spark;

public class RegistrationController {
	
	public StringWriter getRegistrationPage() {
		final Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(RegistrationController.class, "/");
	
		StringWriter writer = new StringWriter();  
        try {
            Template formTemplate = configuration.getTemplate("templates/registration.ftl");

            formTemplate.process(null, writer);
        } catch (Exception e) {
            Spark.halt(500);
        }

        return writer;
		
	}
}
