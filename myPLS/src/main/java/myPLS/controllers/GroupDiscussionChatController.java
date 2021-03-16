package myPLS.controllers;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import spark.Request;
import spark.Response;
import myPLS.services.GroupDiscussionService;
public class GroupDiscussionChatController {
    private final Configuration configuration = new Configuration(new Version(2, 3, 0));
    private static GroupDiscussionService groupDiscussionService;

    public GroupDiscussionChatController() {
		setConfiguration();
        groupDiscussionService = new GroupDiscussionService();
	}
    private void setConfiguration() {
		configuration.setClassForTemplateLoading(GroupDiscussionChatController.class, "/");
        
	}
    public boolean postMessage(Request request, Response response){
        groupDiscussionService.postAMessage(request, response);
        return true;
    }
}
