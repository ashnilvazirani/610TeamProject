package myPLS.controllers;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

/**
 *  The ProfessorController class to implement professor functionality
 * @author ashnil
 *
 */
public class ProfessorController {
	private final Configuration configuration = new Configuration(new Version(2, 3, 0));
	private CourseService courseService;
	private CourseService preReqService;
	private QuizService quizService;
	public ArrayList<Integer> questionList;
	private LectureFactory lectureFactory;
	private LectureDAO lectureDao;
	private LearnerController learnerCotroller;

	public ProfessorController() {
		this.setConfiguration();
		this.courseService = new CourseComponentServiceImpl();
		this.preReqService = new CourseServiceImpl();
		this.quizService = new QuizService();
		this.questionList = new ArrayList<>();
		lectureFactory = new LectureFactory();
		lectureDao = new LectureDAO();
		learnerCotroller = new LearnerController();
	}

	private void setConfiguration() {
		configuration.setClassForTemplateLoading(ProfessorController.class, "/");
	}

	public boolean createACourseGroup(Request request, Response response) {
		return this.courseService.createACourseGroup(request);
	}

	//m method to add question by professor
	public boolean addQuestion(Request request, Response response) {
		String problem = request.queryParams("problem") != null ? request.queryParams("problem") : "null";
		String option1 = request.queryParams("option1") != null ? request.queryParams("option1") : "null";
		String option2 = request.queryParams("option2") != null ? request.queryParams("option2") : "null";
		String option3 = request.queryParams("option3") != null ? request.queryParams("option3") : "null";
		String option4 = request.queryParams("option4") != null ? request.queryParams("option4") : "null";
		int correctAnswer = request.queryParams("correctAnswer") != null
				? Integer.parseInt(request.queryParams("correctAnswer"))
				: 1;
		int courseID = 0;
		int professorID = request.queryParams("professorID") != null
				? Integer.parseInt(request.queryParams("professorID"))
				: 0;
		Question q = new Question(problem, option1, option2, option3, option4, correctAnswer, courseID, professorID);
		if (this.quizService.addQuestion(q)) {
			response.redirect("/question");
			return true;
		} else {
			System.out.println("ERROR");
			return false;
		}
	}

	 // method to call professorDashboard.ftl file based on professor role
	public StringWriter getProfessorDashboard(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> courses = courseService.getCoursesById(request);
		ArrayList<CourseGroup> courseGroups = new ArrayList<>();
		Map<Integer, List<Course>> prereqCourses = new HashMap<Integer, List<Course>>();
		List<Integer> quizNumbersInLine = new ArrayList<>();
		for (Course c : courses) {
			CourseGroup gc = courseService.getCourseGroupByCourseId(c.getCourseId());
			if (gc.getCourseName() != null)
				courseGroups.add(gc);
			prereqCourses.put(c.getCourseId(), new ArrayList<Course>());
			request.attribute("courseId", c.getCourseId());
			quizNumbersInLine.add(this.quizService.getQuizNumberForCourse(c.getCourseId()));
			List<Course> preReqs = preReqService.getCoursesById(request);
			Iterator<Course> itr = preReqs.iterator();
			while (itr.hasNext()) {
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

	// method to implement publish quiz functionality
	public boolean publishQuiz(Request request, Response response) {
		System.out.println(request.queryParams("quizTopic"));
		String quizTime = request.queryParams("quizTime");
		String quizTopic = request.queryParams("quizTopic");
		int userId = request.session().attribute("userID");
		int courseID = Integer.parseInt(request.queryParams("courseID"));
		int numberOfQuestions = this.questionList.size();
		int lectureId = Integer.parseInt(request.queryParams("lectureId"));
		Quiz test = new Quiz(quizTime, quizTopic, userId, courseID, numberOfQuestions, lectureId);
		int quizID = this.quizService.publishQuiz(test);
		if (quizID > 0) {
			for (int questionID : this.questionList) {
				QuizQuestion q = new QuizQuestion(quizID, questionID);
				this.quizService.addQuizQuestion(q);
				System.out.println("ADDED PROBLEM: " + questionID + " to " + quizID);
			}
		}
		response.redirect("/professorDashboard");
		return true;
	}

	// method to get question page
	public StringWriter getQuestionPage(Request request, Response response) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		int professorID = request.session().attribute("userID");
		List<Question> questions = this.quizService.getQuestionsAddedByProfessor(professorID);
		map.put("questions", questions);
		if (request.params("courseId") != null) {
			map.put("courseID", Integer.parseInt(request.params("courseId")));
			map.put("courseName", this.courseService
					.getCourseGroupByCourseId(Integer.parseInt(request.params("courseId"))).getCourseName());
			map.put("lectureId", Integer.parseInt(request.params("lectureId")));
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

	public boolean addQuestionToList(Request request, Response response) {
		int courseID = Integer.parseInt(request.queryParams("courseID"));
		int questionID = Integer.parseInt(request.queryParams("questionID"));
		int lectureId = Integer.parseInt(request.queryParams("lectureId"));
		if (!this.questionList.contains(questionID))
			this.questionList.add(questionID);
		System.out.println(this.questionList.toString());
		response.redirect("/createQuiz/" + courseID + "/" + lectureId);
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
		if (request.params("courseId") != null) {
			map.put("courseId", Integer.parseInt(request.params("courseId")));
			map.put("courseName", this.courseService.getCourseByCourseId(Integer.parseInt(request.params("courseId")))
					.getCourseName());
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

	// method to get answer key for quiz
	public StringWriter getAnswerKey(Request request, Response response) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Question> questions = null;
		System.out.println(request.params());
		int quizID = Integer.parseInt(request.params("quizid"));
		if (request.params("quizid") != null) {
			map.put("quizID", quizID);
			map.put("courseName", this.courseService.getCourseByCourseId(quizID).getCourseName());
			questions = this.quizService.getQuestionsForQuiz(quizID);
		}
		for (Question q : questions) {
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
		// method to implement add lecture
	public StringWriter addLecture(Request request, Response response) {
		String type = request.queryParams("type") != null ? request.queryParams("type") : "unknown";
		LectureService lectureService = lectureFactory.createLecture(type);
		lectureService.addLecture(request);
		return this.getLectures(request);
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

	// method to get lecture
	public StringWriter getLectures(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		String type = request.queryParams("type") != null ? request.queryParams("type") : "unknown";
		LectureService lectureService = lectureFactory.createLecture(type);
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
			request.session().attribute("lectureId", Integer.parseInt(request.queryParams("lectureId")));
			Map<String, Object> map = new HashMap<String, Object>();
			int courseId = Integer.parseInt(request.queryParams("courseId"));
			map.put("courseId", courseId);
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}

	// method to call upload pdf
	public Object uploadPdf(Request request, Response response) {
		LectureService lectureService = lectureFactory.createLecture("PDF");
		lectureService.upload(request);
		return this.getLectures(request);
	}

	public Object downloadPdfLecture(Request request, Response response) {
		int lectureId = Integer.parseInt(request.queryParams("lectureId"));
		String lectureName = request.queryParams("lectureName");
		lectureDao.getPdf(lectureId, lectureName);
		if (request.session().attribute("role").equals("student")) {
			return learnerCotroller.getLearnerLectureDetails(request);
		}
		return this.getLectures(request);
	}

	public Object getPdfLectures(Request request, Response response) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		int lectureId = Integer.parseInt(request.queryParams("lectureId"));
		List<PDFLecture> lectures = lectureDao.getPdfNames(lectureId);
		map.put("lectures", lectures);
		int courseId = Integer.parseInt(request.queryParams("courseId"));
		map.put("courseId", courseId);
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
		List<Lecture> lectures = lectureDao.getLecture(lectureId);
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
		LectureService lectureService = lectureFactory.createLecture("PDF");
		lectureService.updateLecture(request);
		return this.getLectures(request);
	}

	public Object deleteLecture(Request request, Response response) {
		int lectureId = Integer.parseInt(request.queryParams("lectureId"));
		lectureDao.deleteLecture(lectureId);
		return this.getLectures(request);
	}

	public Object getLearnerLectureDetails(Request request) {
		return null;
	}

	// method to implement schedule sharing of lecture
	public Object scheduleLectureSharing(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/scehduleLectureSharing.ftl");
			String lectureName = request.queryParams("lectureName");
			int courseId = Integer.parseInt(request.queryParams("courseId"));
			int lectureId = Integer.parseInt(request.queryParams("lectureId"));
			map.put("lectureId", lectureId);
			map.put("lectureName", lectureName);
			map.put("courseId", courseId);
			resultTemplate.process(map, writer);

		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}

	public Object getCss(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/jquery.datetimepicker.min.css");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}

	public Object getJs(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/jquery.js");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}

	public Object getJsFull(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/jquery.datetimepicker.full.js");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}

	public Object scheduleLecture(Request request, Response response) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		LocalDateTime date = LocalDateTime.parse(request.queryParams("datetime"), formatter);
		int lectureId = Integer.parseInt(request.queryParams("lectureId"));
		LectureService lectureService = lectureFactory.createLecture("PDF");
		lectureService.scheduleLectureSharing(date, lectureId);
		return this.getLectures(request);
	}

}
