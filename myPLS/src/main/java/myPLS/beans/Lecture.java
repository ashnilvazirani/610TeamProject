package myPLS.beans;

import java.io.InputStream;

/**
 * The Lecture class to declare lecture details like Lecture id, name and description, course id, group id, professor id
 * @author sandeep
 *
 */
public class Lecture {
	private int lectureId;
	private int courseId;
	private int professorId;
	private String lectureName;
	private String lectureDescription;
	public int getLectureId() {
		return lectureId;
	}
	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getProfessorId() {
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public String getLectureDescription() {
		return lectureDescription;
	}
	public void setLectureDescription(String lectureDescription) {
		this.lectureDescription = lectureDescription;
	}
}
