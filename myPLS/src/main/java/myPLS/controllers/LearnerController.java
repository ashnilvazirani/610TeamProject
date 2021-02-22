package myPLS.controllers;
import freemarker.template.Configuration;
import freemarker.template.Version;
import myPLS.services.LearnerService;

public class LearnerController {
    private final Configuration configuration = new Configuration(new Version(2, 3, 0));
    private LearnerService learnerService;
    LearnerController(){
        this.setConfiguration();
        this.learnerService = new LearnerService(); 
    }
    private void setConfiguration() {
        configuration.setClassForTemplateLoading(LearnerController.class, "/");
	}
}
