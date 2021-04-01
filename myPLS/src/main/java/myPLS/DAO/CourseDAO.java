package myPLS.DAO;

import java.util.List;

import myPLS.beans.Course;
import myPLS.beans.CourseGroup;
import myPLS.beans.CourseGroupChat;
import myPLS.beans.CourseGroupMembers;

public interface CourseDAO {
	boolean addCourse(Course course);
	boolean updateCourse(Course course);
	List<Course> getCourses();
	boolean deleteCourse(int id);
	Course getCourse(int id);
	List<Course> getPreReqOfCourse(int id);
	List<Course> getCourseById(int id);
	boolean createAGroupForCourse(int professorId, int courseId);
	CourseGroup getCourseGroupByCourseId(int courseId);
	List<CourseGroupChat> getCourseGroupChats(int courseGroupID);
	List<CourseGroupMembers> getCourseGroupMembersFromCourse(int courseGroupID);
	boolean postMessageInGroup(CourseGroupChat chat);
	boolean addRemoveMemberCourseGroup(int courseId, int userID, int operation);
	List<CourseGroup> getCourseGroupByUserId(int userId);
	List<CourseGroup> getCourseGroupsForUser(int userId);
	public boolean modifyCourse(Course c, String operation);
}
