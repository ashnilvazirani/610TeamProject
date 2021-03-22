package myPLS.services;

import java.util.List;

import myPLS.beans.Course;
import spark.Request;

public interface CourseService {
	boolean addCourse(Request request);
	default boolean updateCourse(Request course) {return false;}
	List<Course> getCourses();
	boolean deleteCourse(Request request);
	Course getCourseByCourseId(Request request);
	List<Course> getCourseByProfessorId(Request request);
}
