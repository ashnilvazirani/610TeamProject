package myPLS.services;

import java.util.List;

import myPLS.DAO.CourseDAO;
import myPLS.DAO.CourseDAOImpl;
import myPLS.beans.Course;
import spark.Request;

public class CourseComponentServiceImpl implements CourseService {
    private CourseDAO courseDao;

    public CourseComponentServiceImpl() {
		this.courseDao = new CourseDAOImpl();
	}

	@Override
	public boolean addCourse(Request request) {
		Course course = new Course();
		course.setCourseName(request.queryParams("courseName") != null ? request.queryParams("courseName") : "unknown");
		course.setCourseDescription(request.queryParams("courseDescription") != null ? request.queryParams("courseDescription") : "unknown");
		course.setCourseDuration(Integer.parseInt(request.queryParams("courseDuration") != null ? request.queryParams("courseDuration") : "unknown"));
		course.setProfessorId(Integer.parseInt(request.queryParams("professorId") != null ? request.queryParams("professorId") : "unknown"));
		int streamId = Integer.parseInt(request.queryParams("streamId") != null ? request.queryParams("streamId") : "unknown");
		course.setStreamId(streamId);
		
		return courseDao.addCourse(course);
	}

	@Override
	public boolean updateCourse(Request request) {
		Course course = new Course();
		course.setCourseName(request.queryParams("courseName") != null ? request.queryParams("courseName") : "unknown");
		course.setCourseDescription(request.queryParams("courseDescription") != null ? request.queryParams("courseDescription") : "unknown");
		course.setCourseDuration(Integer.parseInt(request.queryParams("courseDuration") != null ? request.queryParams("courseDuration") : "unknown"));
		course.setStreamId(Integer.parseInt(request.queryParams("streamId") != null ? request.queryParams("streamId") : "unknown"));
		course.setCourseId(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
		return courseDao.updateCourse(course);
	}

	@Override
	public List<Course> getCourses() {
		return courseDao.getCourses();
	}

	@Override
	public boolean deleteCourse(Request request) {
		return courseDao.deleteCourse(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
	}

	@Override
	public Course getCourseByCourseId(Request request) {
		return courseDao.getCourse(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
	}
   
	@Override
	public List<Course> getCourseByProfessorId(Request request) {
		return courseDao.getCourseById(request.session().attribute("userID"));
	}
}
