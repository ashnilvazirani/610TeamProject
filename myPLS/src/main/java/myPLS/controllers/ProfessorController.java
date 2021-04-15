package myPLS.controllers;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.DAO.LectureDAO;
import myPLS.beans.Course;
import myPLS.beans.CourseGroup;
import myPLS.beans.Lecture;
import myPLS.beans.PDFLecture;
import myPLS.beans.Question;
import myPLS.beans.Quiz;
import myPLS.beans.QuizQuestion;
import myPLS.services.CourseComponentServiceImpl;
import myPLS.services.CourseService;
import myPLS.services.CourseServiceImpl;
import myPLS.services.LectureFactory;
import myPLS.services.LectureService;
import myPLS.services.QuizService;
import spark.Request;
import spark.Response;
import spark.Spark;

public class ProfessorController {
    private final Configuration configuration = new Configuration(new Version(2, 3, 0));
    private CourseService courseService;
    private CourseService preReqService;
	private QuizService quizService;
	public ArrayList<Integer> questionList;
	private LectureFactory lectureFactory;
    private LectureDAO lectureDao;
    public ProfessorController(){
        this.setConfiguration();
        this.courseService = new CourseComponentServiceImpl();
        this.preReqService = new CourseServiceImpl();
		this.quizService = new QuizService();
		this.questionList = new ArrayList<>();
		lectureFactory = new LectureFactory();
        lectureDao = new LectureDAO();
    }
    private void setConfiguration() {
        configuration.setClassForTemplateLoading(ProfessorController.class, "/");
	}
    public boolean createACourseGroup(Request request, Response response){
		return this.courseService.createACourseGroup(request);
	}
	public boolean addQuestion(Request request, Response response){
        String problem = request.queryParams("problem") != null ? request.queryParams("problem") : "null";
		String option1 = request.queryParams("option1") != null ? request.queryParams("option1") : "null";
		String option2 = request.queryParams("option2") != null ? request.queryParams("option2") : "null";
		String option3 = request.queryParams("option3") != null ? request.queryParams("option3") : "null";
		String option4 = request.queryParams("option4") != null ? request.queryParams("option4") : "null";
		int correctAnswer = request.queryParams("correctAnswer") != null ? Integer.parseInt(request.queryParams("correctAnswer")) : 1;
		int courseID = 0;
		int professorID = request.queryParams("professorID") != null ? Integer.parseInt(request.queryParams("professorID")) : 0;
		Question q = new Question(problem, option1, option2, option3, option4, correctAnswer, courseID, professorID);
		if(this.quizService.addQuestion(q)){
			response.redirect("/question");
			return true;
		}else{
			System.out.println("ERROR");
			return false;
		}
	}

    public StringWriter getProfessorDashboard(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> courses =  courseService.getCoursesById(request);
		ArrayList<CourseGroup> courseGroups =new ArrayList<>();
		Map<Integer, List<Course>> prereqCourses = new HashMap<Integer, List<Course>>();
		List<Integer> quizNumbersInLine = new ArrayList<>();
		for(Course c:courses){
			CourseGroup gc = courseService.getCourseGroupByCourseId(c.getCourseId());
			if(gc.getCourseName()!=null)
				courseGroups.add(gc);
			prereqCourses.put(c.getCourseId(), new ArrayList<Course>());
			request.attribute("courseId", c.getCourseId());
			quizNumbersInLine.add(this.quizService.getQuizNumberForCourse(c.getCourseId()));
			List<Course> preReqs = preReqService.getCoursesById(request);
			Iterator<Course> itr = preReqs.iterator();
			while(itr.hasNext()) {
				prereqCourses.get(c.getCourseId()).add(itr.next());
			}
		}
		map.put("courses", courses);
		map.put("courseGroups", courseGroups);
		map.put("userId", request.session().attribute("userID"));
		map.put("preReqs", prereqCourses);
		map.put("quizNumbers", quizNumbersInLine);
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/professorDashboard.ftl");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
	public boolean publishQuiz(Request request, Response response){
		System.out.println(request.queryParams("quizTopic"));
		String quizTime = request.queryParams("quizTime");
		String quizTopic = request.queryParams("quizTopic");
		int userId = request.session().attribute("userID");
		int courseID = Integer.parseInt(request.queryParams("courseID"));
		int numberOfQuestions = this.questionList.size();
		Quiz test = new Quiz(quizTime, quizTopic, userId, courseID, numberOfQuestions);
		int quizID = this.quizService.publishQuiz(test);
		System.out.println("QUIZ PUBLISHED: "+test.getQuizTopic());
		if(quizID>0){
			for(int questionID : this.questionList){
				QuizQuestion q = new QuizQuestion(quizID, questionID);
				this.quizService.addQuizQuestion(q);
				System.out.println("ADDED PROBLEM: "+questionID+" to "+quizID);
			}
		}
		response.redirect("/professorDashboard");
		return true;
	}
	public StringWriter getQuestionPage(Request request, Response response) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		int professorID = request.session().attribute("userID");
		List<Question> questions =  this.quizService.getQuestionsAddedByProfessor(professorID);
		map.put("questions", questions);
		if(request.params("courseId") != null){
			map.put("courseID", Integer.parseInt(request.params("courseId")));
			map.put("courseName", this.courseService.getCourseGroupByCourseId(Integer.parseInt(request.params("courseId"))).getCourseName());
		}
		map.put("quizNumber", -11);
		map.put("userId", request.session().attribute("userID"));
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/questions.ftl");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
	public boolean addQuestionToList(Request request, Response response){
		int courseID = Integer.parseInt(request.queryParams("courseID"));
		int questionID = Integer.parseInt(request.queryParams("questionID"));
		if(!this.questionList.contains(questionID))
			this.questionList.add(questionID);
		System.out.println(this.questionList.toString());
		response.redirect("/createQuiz/"+courseID);
		return true;
	}
	public StringWriter addQuestionPage(Request request, Response response) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", request.session().attribute("userID"));
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/addQuestion.ftl");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
	
    public StringWriter getQuizListPage(Request request, Response response) {
        StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Quiz> quizzes = null;
		if(request.params("courseId") != null){
			map.put("courseId", Integer.parseInt(request.params("courseId")));
			map.put("courseName", this.courseService.getCourseByCourseId(Integer.parseInt(request.params("courseId"))).getCourseName());
			quizzes = this.quizService.getAllQuizForCourse(Integer.parseInt(request.params("courseId")));
		}
		map.put("quizzes", quizzes);
		map.put("userId", request.session().attribute("userID"));
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/viewQuizList.ftl");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
    }
	public StringWriter getAnswerKey(Request request, Response response) {
        StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Question> questions = null;
		System.out.println(request.params());
		int quizID = Integer.parseInt(request.params("quizid"));
		if(request.params("quizid") != null){
			map.put("quizID", quizID);
			map.put("courseName", this.courseService.getCourseByCourseId(quizID).getCourseName());
			questions = this.quizService.getQuestionsForQuiz(quizID);
		}
		for(Question q:questions){
			System.out.println(q.getProblem());
		}
		map.put("questions", questions);
		map.put("userId", request.session().attribute("userID"));
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/viewQuizAnswerKey.ftl");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
    }
	
	 public StringWriter addLecture(Request request, Response response){
	    	String type = request.queryParams("type") != null ? request.queryParams("type") : "unknown";
	    	LectureService lectureService =  lectureFactory.createLecture(type);
	    	lectureService.addLecture(request);
			response.redirect("/getLectures");
			return null;
		}
	    
		public Object getAddLecturePage(Request request) {
			StringWriter writer = new StringWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			Template resultTemplate;
			try {
				resultTemplate = configuration.getTemplate("templates/addLecture.ftl");
				map.put("courseId", Integer.parseInt(request.queryParams("courseId")));
				String msg = request.queryParams("errorMsg");
				map.put("errorMsg", msg);
				resultTemplate.process(map, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		}
		
		 public StringWriter getLectures(Request request) {
				StringWriter writer = new StringWriter();
				Map<String, Object> map = new HashMap<String, Object>();
		    	String type = request.queryParams("type") != null ? request.queryParams("type") : "unknown";
				LectureService lectureService =  lectureFactory.createLecture(type);
		    	List<Lecture> lectures = lectureService.getLectures(request);
				map.put("lectures", lectures);
				Template resultTemplate;
				try {
					resultTemplate = configuration.getTemplate("templates/lectures.ftl");
					resultTemplate.process(map, writer);
				} catch (Exception e) {
					Spark.halt(500);
				}
				return writer;
		}
		 
		public Object getUploadPage(Request request) {
			StringWriter writer = new StringWriter();
			Template resultTemplate;
			try {
				resultTemplate = configuration.getTemplate("templates/uploadPdf.ftl");
				request.session().attribute("lectureId",Integer.parseInt(request.queryParams("lectureId")));
				resultTemplate.process(null, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		}
		
		public Object uploadPdf(Request request, Response response) {
			LectureService lectureService =  lectureFactory.createLecture("PDF");
	    	lectureService.upload(request);
	    	response.redirect("/getLectures");
			return null;
		}
		
		public Object downloadPdfLecture(Request request, Response response) {
			int lectureId = Integer.parseInt(request.queryParams("lectureId"));
			String lectureName = request.queryParams("lectureName");
			lectureDao.getPdf(lectureId,lectureName);
			response.redirect("/getLectures");
			return null;
		}
		
		public Object getPdfLectures(Request request, Response response) {
			StringWriter writer = new StringWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			int lectureId = Integer.parseInt(request.queryParams("lectureId"));
			List<PDFLecture> lectures =  lectureDao.getPdfNames(lectureId);
			map.put("lectures", lectures);
			Template resultTemplate;
			try {
				resultTemplate = configuration.getTemplate("templates/pdfLectures.ftl");
				resultTemplate.process(map, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		}
		public Object getEditLecturePage(Request request, Response response) {
			StringWriter writer = new StringWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			int lectureId = Integer.parseInt(request.queryParams("lectureId"));
			List<Lecture> lectures =  lectureDao.getLecture(lectureId);
			map.put("lecture", lectures.get(0));
			Template resultTemplate;
			try {
				resultTemplate = configuration.getTemplate("templates/editLecture.ftl");
				resultTemplate.process(map, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		}
		public Object updateLecture(Request request, Response response) {
	    	LectureService lectureService =  lectureFactory.createLecture("PDF");
	    	lectureService.updateLecture(request);
			response.redirect("/getLectures");
			return null;
		}
		public Object deleteLecture(Request request, Response response) {
			int lectureId = Integer.parseInt(request.queryParams("lectureId"));
	    	lectureDao.deleteLecture(lectureId);
			response.redirect("/getLectures");
			return null;
		}
		
		
		
}
