package myPLS.controllers;

import freemarker.template.Configuration;
import freemarker.template.Version;
import myPLS.services.GroupDiscussionService;
import spark.Request;
import spark.Response;

/**
 * The GroupDiscussionChatController class to implement discussion group chat functionality
 * @author sandeep
 *
 */
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
