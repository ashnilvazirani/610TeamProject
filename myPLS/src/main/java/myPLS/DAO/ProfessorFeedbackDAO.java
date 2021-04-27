package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myPLS.beans.Feedback;

/**
 * This ProfessorFeedbackDAO class to get feedback of the professor
 * @author sandeep
 *
 */
public class ProfessorFeedbackDAO {

	/**
	 * This addProfessorFeedback method will add feedback for professor
	 * @param feedback professor feedback
	 * @return true if feedback is added in system
	 */
	public boolean addProfessorFeedback(Feedback feedback) {
		final String INSERT_PROFESSOR_FFEDBACK = "INSERT INTO professorFeedback (learnerId, professorId, rating, comments, courseId) VALUES (?,?,?,?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_PROFESSOR_FFEDBACK)) {
			preparedStatement.setInt(1, feedback.getFeedbackGiverId());
			preparedStatement.setInt(2, feedback.getFeedbackEntityId());
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

	/**
	 * This updateProfessorFeedback method will update feedback for professor
	 * @param feedback professor feedback
	 * @return true if feedback is updated in system
	 */
	public boolean updateProfessorFeedback(Feedback feedback) {
		final String UPDATE_PROFESSOR_FFEDBACK = "UPDATE professorFeedback set rating=?, comments=? where learnerId = ? and professorId=? and courseId=?";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_PROFESSOR_FFEDBACK)) {
			preparedStatement.setInt(3, feedback.getFeedbackGiverId());
			preparedStatement.setInt(4, feedback.getFeedbackEntityId());
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

	/**
	 *  This getProfessorFeedback method will get the feedback for professor from system
	 * @param feedback feedback given by the student
	 * @return feedback of the professor
	 */
	public Feedback getProfessorFeedback(Feedback feedback) {
		final String GET_PROFESSOR_FFEDBACK = "select * from professorFeedback where learnerId =? and professorId=? and courseId=?";
		Feedback feedbackObj = null;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_PROFESSOR_FFEDBACK)) {
			preparedStatement.setInt(1, feedback.getFeedbackGiverId());
			preparedStatement.setInt(2, feedback.getFeedbackEntityId());
			preparedStatement.setInt(3, feedback.getCourseId());
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				feedbackObj = new Feedback();
				feedbackObj.setRating(result.getDouble("rating"));
				feedbackObj.setComments(result.getString("comments"));
				feedbackObj.setFeedbackGiverId(result.getInt("learnerId"));
				feedbackObj.setFeedbackEntityId(result.getInt("professorId"));
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
