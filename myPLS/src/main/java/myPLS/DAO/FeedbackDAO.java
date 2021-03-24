package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
