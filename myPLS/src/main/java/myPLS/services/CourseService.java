package myPLS.services;

import java.util.Map;

import myPLS.DAO.CourseDAO;
import myPLS.beans.Course;
import spark.Request;

public class CourseService {
    private CourseDAO courseDao;

    public CourseService() {
		this.courseDao = new CourseDAO();
	}
    public Map<Integer, Course> getUserDetails(){
        return this.courseDao.getAllCourses();
    }
	public boolean addCourseDetails(Request request) {
		//Connect with UI
        return true;
	}
}
