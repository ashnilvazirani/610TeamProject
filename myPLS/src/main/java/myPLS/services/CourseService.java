package myPLS.services;

import java.util.List;

import myPLS.beans.Course;
import spark.Request;

public interface CourseService {
	boolean addCourse(Request request);
	boolean updateCourse(Course course);
	List<Course> getCourses();
	boolean deleteCourse(int id);
	Course getCourse(int id);

}
