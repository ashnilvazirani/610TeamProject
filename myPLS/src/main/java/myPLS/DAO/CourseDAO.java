package myPLS.DAO;

import java.util.List;

import myPLS.beans.Course;

public interface CourseDAO {
	boolean addCourse(Course course);
	boolean updateCourse(Course course);
	List<Course> getCourses();
	boolean deleteCourse(int id);
	Course getCourse(int id);
	List<Course> getPreReqOfCourse(int id);
	List<Course> getCourseById(int id);
}
