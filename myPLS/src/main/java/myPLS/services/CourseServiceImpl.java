package myPLS.services;

import java.util.ArrayList;
import java.util.List;

import myPLS.DAO.CourseDAO;
import myPLS.DAO.CourseDAOImpl;
import myPLS.DAO.PreReqCourseDAO;
import myPLS.DAO.PreReqCourseDAOImpl;
import myPLS.beans.Course;
import spark.Request;

public class CourseServiceImpl implements CourseService{
	
	private CourseDAO courseDao;
	private List<Course> preReqs;
	private PreReqCourseDAO preReqCourseDao;

    public CourseServiceImpl() {
		this.courseDao = new CourseDAOImpl();
		this.preReqs = new ArrayList<Course>();
		this.preReqCourseDao = new PreReqCourseDAOImpl();
	}

    /**
     * @param request
     * This method adds preReq of a course
     */
	@Override
	public boolean addCourse(Request request) {
		// TODO get all the preReq courses check if their is no any cycle detection only then add course preReq
		
		int courseId = Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown");
		int preReqCourseId = Integer.parseInt(request.queryParams("preReqId") != null ? request.queryParams("preReqId") : "unknown");
		return preReqCourseDao.addCourse(courseId, preReqCourseId);
	}

	
	/**
     * This method returns preReq List
     */
	@Override
	public List<Course> getCourses() {
		return preReqs;
	}

	/**
     * @param courseId
     * This method delete preReq of a Course
     */
	@Override
	public boolean deleteCourse(Request request) {
		int courseId = Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown");
		int preReqCourseId = Integer.parseInt(request.queryParams("preReqId") != null ? request.queryParams("preReqId") : "unknown");
		return preReqCourseDao.deleteCourse(courseId, preReqCourseId);
	}

	/**
     * @param courseId
     * This method get the courseDeatils and set the preReq of a Course
     */
	@Override
	public Course getCourseByCourseId(Request request) {
		int id = Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown");
		preReqs.addAll(courseDao.getPreReqOfCourse(id));
		return courseDao.getCourse(id);
	}

	@Override
	public List<Course> getCourseByProfessorId(Request request) {
		return null;
	}

}
