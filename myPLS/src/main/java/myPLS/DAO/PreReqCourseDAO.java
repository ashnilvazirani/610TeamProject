package myPLS.DAO;

public interface PreReqCourseDAO {
	boolean addCourse(int courseId, int preReqCourseId);
	boolean deleteCourse(int courseId, int preReqCourseId);
}
