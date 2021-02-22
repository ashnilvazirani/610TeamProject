package myPLS.controllers;
import freemarker.template.Configuration;
import myPLS.services.StreamService;
import freemarker.template.Version;

public class StreamController {
    private final Configuration configuration = new Configuration(new Version(2, 3, 0));
	private static StreamService streamService;
    StreamController(){
        this.setConfiguration();
        this.streamService = new StreamService();
    }
    private void setConfiguration() {
        configuration.setClassForTemplateLoading(StreamController.class, "/");
	}
}
