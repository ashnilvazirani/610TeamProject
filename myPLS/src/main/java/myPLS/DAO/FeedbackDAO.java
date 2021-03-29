package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myPLS.beans.Feedback;

public class FeedbackDAO {

	public boolean addStudentFeedback(Feedback feedback) {
		final String STUDENT_FFEDBACK = "INSERT INTO learnerFeedback (learnerId, professorId, rating, comments, courseId) VALUES (?,?,?,?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(STUDENT_FFEDBACK)) {
			preparedStatement.setInt(1, feedback.getFeedbackEntityId());
			preparedStatement.setInt(2, feedback.getFeedbackGiverId());
			preparedStatement.setDouble(3, feedback.getRating());
			preparedStatement.setString(4, feedback.getComments());
			preparedStatement.setInt(5, feedback.getCourseId());
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateStudentFeedback(Feedback feedback) {
		final String STUDENT_FFEDBACK = "UPDATE learnerFeedback set rating=?, comments=? where learnerId = ? and professorId=? and courseId=?";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(STUDENT_FFEDBACK)) {
			preparedStatement.setInt(3, feedback.getFeedbackEntityId());
			preparedStatement.setInt(4, feedback.getFeedbackGiverId());
			preparedStatement.setDouble(1, feedback.getRating());
			preparedStatement.setString(2, feedback.getComments());
			preparedStatement.setInt(5, feedback.getCourseId());
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Feedback getStudentFeedback(Feedback feedback) {
		final String ENROLLED_STUDENTS = "select * from learnerFeedback where learnerId =? and professorId=? and courseId=?";
		Feedback feedbackObj = null;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(ENROLLED_STUDENTS)) {
			preparedStatement.setInt(1, feedback.getFeedbackEntityId());
			preparedStatement.setInt(2, feedback.getFeedbackGiverId());
			preparedStatement.setInt(3, feedback.getCourseId());
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				feedbackObj = new Feedback();
				feedbackObj.setRating(result.getDouble("rating"));
				feedbackObj.setComments(result.getString("comments"));
				feedbackObj.setFeedbackGiverId(result.getInt("professorId"));
				feedbackObj.setFeedbackEntityId(result.getInt("learnerId"));
				feedbackObj.setCourseId(result.getInt("courseId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedbackObj;
	}

}
