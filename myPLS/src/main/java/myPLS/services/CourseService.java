package myPLS.services;

import java.util.List;

import myPLS.beans.Course;
import myPLS.beans.CourseGroup;
import myPLS.beans.CourseGroupChat;
import spark.Request;

public interface CourseService {
	boolean addCourse(Request request);
	default boolean updateCourse(Request course) {return false;}
	List<Course> getCourses();
	boolean deleteCourse(Request request);
	Course getCourseByCourseId(Request request);
	List<Course> getCourseByProfessorId(Request request);
	Course getCourseByCourseId(int courseId);
	boolean createACourseGroup(Request request);
	CourseGroup getCourseGroupByCourseId(int courseId);
	List<CourseGroupChat> getChatsForCourseGroup(int courseGroupID);
	int addMessageToCourseGroup(Request request);
}
