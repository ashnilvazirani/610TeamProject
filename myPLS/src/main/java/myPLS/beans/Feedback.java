package myPLS.beans;

import java.util.Date;

/**
 * The Feedback class to declare feedback details like feedback id, dates, comments, rating, course id
 * @author sandeep
 *
 */
public class Feedback {
	private int feedbackId;
	// reciever
	private int feedbackEntityId;
	// who gave the feedback
	private int feedbackGiverId;
	private Date date;
	private String comments;
	private double rating;
	private int courseId;
	private String name;
	private String role;
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public int getFeedbackEntityId() {
		return feedbackEntityId;
	}
	public void setFeedbackEntityId(int feedbackEntityId) {
		this.feedbackEntityId = feedbackEntityId;
	}
	public int getFeedbackGiverId() {
		return feedbackGiverId;
	}
	public void setFeedbackGiverId(int feedbackGiverId) {
		this.feedbackGiverId = feedbackGiverId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getRating() {
		return rating;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
