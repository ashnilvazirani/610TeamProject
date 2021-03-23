package myPLS.controllers;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.beans.GroupDiscussion;
import myPLS.beans.GroupDiscussionChat;
import myPLS.beans.User;
import myPLS.services.GroupDiscussionService;
import myPLS.services.UserService;
import spark.Request;
import spark.Response;
import spark.Spark;
public class AdminController {
    private final Configuration configuration = new Configuration(new Version(2, 3, 0));
    private static GroupDiscussionService groupDiscussionService;
    private static UserService userService;
	public AdminController() {
		setConfiguration();
        groupDiscussionService = new GroupDiscussionService();
        userService = new UserService();
	}
    private void setConfiguration() {
		configuration.setClassForTemplateLoading(AdminController.class, "/");
	}
    public StringWriter viewAllCourses() {
		StringWriter writer = new StringWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		List<GroupDiscussion> groups = groupDiscussionService.getGroups();
		map.put("groups", groups);
		try {
			Template formTemplate = configuration.getTemplate("templates/viewGroups.ftl");
			formTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
    public StringWriter viewGroupChats(Request request, Response response) {
		StringWriter writer = new StringWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		List<GroupDiscussionChat> chats = groupDiscussionService.getGroupChats(request, response);
        GroupDiscussion groupDiscussion = groupDiscussionService.getGroupDiscussionNameByID(request, response);
        List<User> users = new ArrayList<>();
        for(GroupDiscussionChat chat:chats ){
            User u = userService.getUserByID(chat.getUserID());
            users.add(u);
        }
		map.put("chats", chats);
		map.put("users", users);
		map.put("group", groupDiscussion);
        map.put("userID", request.session().attribute("userID"));
		try {
			Template formTemplate = configuration.getTemplate("templates/viewChats.ftl");
			formTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
    public void createADiscussionGroup(Request request, Response response){
        if(groupDiscussionService.createADiscussionGroup(request, response))
            response.redirect("/courses");
        else
            System.out.println("ERROR TO INSERT!!!");
    }

    public StringWriter inviteMembers(Request request, Response response){
        StringWriter writer = new StringWriter();
        List<User> users = userService.getAllUsers();
        int groupDiscussionID=request.queryParams("groupDiscussionID") != null ? Integer.parseInt(request.queryParams("groupDiscussionID")) : -1;
        Map<String,Object> map = new HashMap<String, Object>();
		map.put("users", users);
        map.put("groupDiscussionID", groupDiscussionID);
        try {
			Template formTemplate = configuration.getTemplate("templates/inviteMembers.ftl");
			formTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
    }

    public StringWriter addMemberToGroup(Request request, Response response){
        if(groupDiscussionService.addMemberToGroup(request, response)){
            System.out.println("ADDED");
        }else{
            System.out.println("ALREADY EXITS");
        }
        response.redirect("/courses");

        return null;
    }
    public StringWriter viewMembersInGroupDiscussion(Request request, Response response){
        StringWriter writer = new StringWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("users", groupDiscussionService.getMembersFromGroupDiscussionID(request, response));
        map.put("groupDiscussion", groupDiscussionService.getGroupDiscussionNameByID(request, response));
		try {
			Template formTemplate = configuration.getTemplate("templates/viewUsersInGroupDiscussion.ftl");
			formTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}

		return writer;
    }
    public StringWriter getGroupDiscussionPage() {
		StringWriter writer = new StringWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userID", 1);
		try {
			Template formTemplate = configuration.getTemplate("templates/addGroupDiscussion.ftl");
			formTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}

		return writer;
	}
}
