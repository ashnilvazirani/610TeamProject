package myPLS.main;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;

import javax.swing.JOptionPane;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import myPLS.controllers.AdminController;
import myPLS.controllers.CourseController;
import myPLS.controllers.GroupDiscussionChatController;
import myPLS.controllers.LearnerController;
import myPLS.controllers.ProfessorController;
import myPLS.controllers.RegistrationController;

public class mainApp {
	
	private final static RegistrationController registraionController = new RegistrationController();
	private final static CourseController courseController = new CourseController();
	private final static LearnerController learnerController = new LearnerController();
	private final static AdminController adminController = new AdminController();
	private final static GroupDiscussionChatController groupDiscussionChatController = new GroupDiscussionChatController();
	private static final ProfessorController professorController = new ProfessorController();
	public static int fileCount=0;
	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException, Exception {
		// get("/*", (request, response) -> {
		// 	if(request.session().attribute("userID")==null){
		// 		request.session().attribute("userID", -1);
        //     	return registraionController.getLoginPage();
		// 	}
		// 	return null;
        // });
		
		get("/registerUser", (request, response) -> {
            return registraionController.getRegistrationPage();
        });
		
		post("/registerUser", (request, response) -> {
           return registraionController.registerUser(request);
        });
		
		post("/mailVerified",(request,response) -> {
			return registraionController.authoriseUser(request);
		});
		
		post("/resetPassword",(request,response) -> {
			registraionController.resetPassword(request, response);
			return 0;
		});
		
		get("/loginPage", (request, response) -> {
            return registraionController.getLoginPage();
        });
        get("/index", (request, response) -> {
			response.redirect("/loginPage");
			return 0;
        });
        get("/", (request, response) -> {
			response.redirect("/loginPage");
			return 0;
        });
        post("/logIn",(request,response) -> {
            return registraionController.logIn(request, response);
        });
		post("/inviteMembers",(request,response) -> {
            return adminController.inviteMembers(request, response);
        });
		post("/postChat",(request,response) -> {
            return groupDiscussionChatController.postMessage(request, response);
        });
		post("/addMemberToGroup",(request,response) -> {
            return adminController.addMemberToGroup(request, response);
        });

		get("/viewGroupChats/:groupDiscussionID",(request,response) -> {
            return adminController.viewGroupChats(request, response);
        });
        get("/addCourse", (request, response) -> {
        	return courseController.getAddCoursePage();
        });
        get("/createGroup", (request, response) -> {
        	return adminController.getGroupDiscussionPage();
        });
		get("/viewGroups",(request,response) -> {
        	return adminController.viewAllCourses();
        });
		post("/viewMembersInGroup",(request,response) -> {
        	return adminController.viewMembersInGroupDiscussion(request, response);
        });
        post("/addGroupDiscussion", (request, response) -> {
			adminController.createADiscussionGroup(request,response);
			response.redirect("/courses");
			return 0;
        });
        get("/courses",(request,response) -> {
        	return courseController.getCourses();
        });
        
        get("/studentDashboard",(request,response) -> {
        	return learnerController.getLearnerDashboard();
        });
		get("/enrollForCourses",(request,response) -> {
        	return learnerController.getCourseListForLearners(request, response);
        });
		get("/enroll/:userId/:courseId",(request,response) -> {
        	return learnerController.enrollLearnerForCourse(request, response);
        });
        
        get("/professorDashboard",(request,response) -> {
        	return professorController.getProfessorDashboard(request);
        });
        
        post("/addCourse", (request, response) -> {
			courseController.addCourse(request,response);
			response.redirect("/courses");
			return 0;
        });	
		post("/courseGroupChat", (request, response) -> {
			return courseController.getCourseChat(request,response);
        });
		get("/courseGroupChat/:courseGroupID", (request, response) -> {
			return courseController.getCourseChat(request,response);
        });
		post("/postMessageForCourseGroup", (request, response) -> {
			courseController.postGroupMessage(request,response);
			return null;
        });
        post("/createACourseGroup", (request, response) -> {
			if(professorController.createACourseGroup(request,response))
				response.redirect("/professorDashboard");
			return 0;
        });
	}
}
