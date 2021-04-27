package myPLS.DAO;

import java.util.List;
import java.util.Map;

import myPLS.beans.Course;

/**
 * The PreReqCourseDAO interface 
 * @author sandeep
 *
 */
public interface PreReqCourseDAO {

	boolean addCourse(int courseId, int preReqCourseId);
	boolean deleteCourse(int courseId, int preReqCourseId);
	public Map<Integer,List<Integer>> getCourses();
	public List<Course> getPreReqCoursesOfCourse(int courseId);
}
