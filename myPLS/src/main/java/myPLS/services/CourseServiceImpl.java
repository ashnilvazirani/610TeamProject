package myPLS.services;

import java.util.List;

import myPLS.DAO.CourseDAO;
import myPLS.DAO.CourseDAOImpl;
import myPLS.beans.Course;
import spark.Request;

public class CourseServiceImpl implements CourseService {
    private CourseDAO courseDao;

    public CourseServiceImpl() {
		this.courseDao = new CourseDAOImpl();
	}

	@Override
	public boolean addCourse(Request request) {
		Course course = new Course();
		course.setCourseName(request.queryParams("courseName") != null ? request.queryParams("courseName") : "unknown");
		course.setCourseDescription(request.queryParams("courseDescription") != null ? request.queryParams("courseDescription") : "unknown");
		course.setCourseDuration(Integer.parseInt(request.queryParams("courseDuration") != null ? request.queryParams("courseDuration") : "unknown"));
		int streamId = Integer.parseInt(request.queryParams("streamId") != null ? request.queryParams("streamId") : "unknown");
		course.setStreamId(streamId);
		
		return courseDao.addCourse(course);
	}

	@Override
	public boolean updateCourse(Course course) {
		return courseDao.updateCourse(course);
	}

	@Override
	public List<Course> getCourses() {
		return courseDao.getCourses();
	}

	@Override
	public boolean deleteCourse(int id) {
		return courseDao.deleteCourse(id);
	}

	@Override
	public Course getCourse(int id) {
		return courseDao.getCourse(id);
	}
   
}
