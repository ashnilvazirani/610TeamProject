package myPLS.controllers;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.DAO.LectureDAO;
import myPLS.beans.Course;
import myPLS.beans.CourseGroup;
import myPLS.beans.CourseGroupMembers;
import myPLS.beans.Grade;
import myPLS.beans.Lecture;
import myPLS.beans.Question;
import myPLS.beans.User;

import myPLS.services.CourseComponentServiceImpl;
import myPLS.services.CourseService;

import myPLS.services.LearnerService;
import myPLS.services.QuizService;
import spark.Request;
import spark.Response;
import spark.Spark;
import myPLS.beans.Quiz;

/**
 * The LearnerController class to implement learner functionality
 * @author abriti
 *
 */
public class LearnerController {
    private final Configuration configuration = new Configuration(new Version(2, 3, 0));
    private LearnerService learnerService;
	private CourseService courseService;
	private LectureDAO lectureDao;
	private QuizService quizService;
    public LearnerController(){
        this.setConfiguration();
        this.learnerService = new LearnerService(); 
		this.courseService = new CourseComponentServiceImpl();
		this.lectureDao = new LectureDAO();
		this.quizService = new QuizService();
    }
    private void setConfiguration() {
        configuration.setClassForTemplateLoading(LearnerController.class, "/");
	}
    
 // method to call studentDashboard.ftl file based on student role
    public StringWriter getLearnerDashboard(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> courses = learnerService.getEnrolledCourses(request);
		List<CourseGroup> myCourseGroups = this.courseService.getCourseGroupForUserByUserId(request.session().attribute("userID"));
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/studentDashboard.ftl");
			map.put("courses", courses);
			map.put("userID", request.session().attribute("userID"));
			map.put("myCourseGroups", myCourseGroups);
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
    
    // method to call enrollCourses.ftl file 
    public StringWriter getAllCourses(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		Template resultTemplate;
		List<Course> courses = learnerService.getUnEnrolledCourses(request);
		try {
			resultTemplate = configuration.getTemplate("templates/enrollCourses.ftl");
			map.put("courses", courses);
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
    
    public StringWriter enrollCourse(Request request, Response response) {
    	StringWriter writer = new StringWriter();
		Template resultTemplate;
		Map<String,String> map = learnerService.enrollCourse(request);
		response.redirect("/studentDashboard");
//		try {
//			resultTemplate = configuration.getTemplate("templates/studentDashboard.ftl");
//			resultTemplate.process(map, writer);
//		} catch (Exception e) {
//			Spark.halt(500);
//		}
		return writer;
    }
    
    // method to call enrolledLearners.ftl file 
    public StringWriter getLearnersEnrolledList(Request request) {
    	StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		Template resultTemplate;
    	List<User> users= learnerService.getLearnersEnrolledList(request);
		int courseId= Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "-1");
		CourseGroup group =  this.courseService.getCourseGroupByCourseId(courseId);
		List<CourseGroupMembers> groupMembers = this.courseService.getCourseGroupMemberFromCourseGroupId(group.getCourseGroupID());
		try {
			resultTemplate = configuration.getTemplate("templates/enrolledLearners.ftl");
			map.put("users", users);
			map.put("courseId", courseId);
			map.put("groupMembers", groupMembers);
			resultTemplate.process(map, writer);

		}catch(Exception e){
			e.printStackTrace();
		}
		return writer;
	}
	public StringWriter enrollLearnerForCourse(Request request, Response response) {
		if(learnerService.addLearnerForCourse(request)){
			response.redirect("/enrollForCourses");
		}else{
			System.out.println("ERROR");
		}
		return null;
	}
	
    // method to call viewCoursesForStudentToEnroll.ftl file 
	public StringWriter getCourseListForLearners(Request request, Response response) {
		StringWriter writer = new StringWriter();
		List<Course> courses = (List<Course>)courseService.getCourses();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courses", courses);
		map.put("userId", request.session().attribute("userID").toString());
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/viewCoursesForStudentToEnroll.ftl");

			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;

    }
    public StringWriter getLearnerLectureDetails(Request request) {
        StringWriter writer = new StringWriter();
		int courseId;
		if(request.queryParams("courseId")!=null){
			courseId = Integer.parseInt(request.queryParams("courseId"));
		}else{
			courseId = Integer.parseInt(request.params("courseId"));
		}
		List<Lecture> lectures =  this.lectureDao.getScheduledLectures(courseId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lectures", lectures);
		map.put("userId", request.session().attribute("userID").toString());
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/studentGetLectures.ftl");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
    }
    
    // method to call viewStudentQuiz.ftl file 
	public StringWriter getLearnerQuizPage(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Quiz> quizzes = null;
		if(request.params("courseId") != null){
			map.put("courseId", Integer.parseInt(request.params("courseId")));
			map.put("lectureId", Integer.parseInt(request.params("lectureId")));
			map.put("courseName", this.courseService.getCourseByCourseId(Integer.parseInt(request.params("courseId"))).getCourseName());
			quizzes = this.quizService.getQuizForLecture(Integer.parseInt(request.params("courseId")), Integer.parseInt(request.params("lectureId")));
		}
		int userId = request.session().attribute("userID");
		List<Grade> grades = this.quizService.getGradesForStudent(userId);
		map.put("quizzes", quizzes);
		map.put("grades", grades);
		map.put("userId", userId);
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/viewStudentQuiz.ftl");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
	   // method to call takeQuiz.ftl file 
	public StringWriter takeQuiz(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Question> questions = null;
		int quizID = Integer.parseInt(request.queryParams("quizID"));
		if(request.queryParams("quizID") != null){
			map.put("quizID", quizID);
			map.put("courseName", this.courseService.getCourseByCourseId(quizID).getCourseName());
			questions = this.quizService.getQuestionsForQuiz(quizID);
		}
		map.put("questions", questions);
		int timeInMinutes = questions.size();
		map.put("userId", request.session().attribute("userID"));
		map.put("lectureId", Integer.parseInt(request.queryParamsSafe("lectureId")));
		map.put("quizTime", timeInMinutes);
		Template resultTemplate;
		try {
			
			resultTemplate = configuration.getTemplate("templates/takeQuiz.ftl");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
	public Object submitQuizToGetGrades(Request request, Response response) {	
	try {
		Map<Integer, Integer> userAnswers = new HashMap<>();
		int numberOfQuestions = Integer.parseInt(request.queryParams("totalPoints").split(":")[1].trim())/10;
		for(int i=1;i<=numberOfQuestions;i++){
			int q = Integer.parseInt(request.queryParams("question:"+i));
			int a =request.queryParams("answer:"+i)!=null ? Integer.parseInt(request.queryParams("answer:"+i)):0;
			userAnswers.put(q, a);
		}
		int quizID = Integer.parseInt(request.queryParams("quizId"));
		int lectureId = Integer.parseInt(request.queryParams("lectureId"));
		int userID = request.session().attribute("userID");
		if(this.quizService.gradeQuiz(userAnswers, quizID, lectureId, userID)>0){
			// http://localhost:4567/learnerQuiz/:courseId/:lectureId
			response.redirect("/studentDashboard");
		}else{
			System.out.println("ERRIR");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}