package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myPLS.beans.Feedback;

public class AdminFeedbackDAO {
	
	public List<Feedback> getFeedbacks() {
		final String FEEDBACK = "(SELECT t1.name,t2.feedback,t1.role\n"
				+ "FROM  user t1\n"
				+ "JOIN(SELECT learnerId,AVG(rating) AS  feedback          \n"
				+ "FROM learnerFeedback           \n"
				+ "GROUP BY learnerId) AS t2          \n"
				+ "ON t1.userID=t2.learnerId)\n"
				+ "union\n"
				+ "(SELECT t1.name,t2.feedback,t1.role \n"
				+ "FROM  user t1\n"
				+ "JOIN(SELECT professorId,AVG(rating) AS  feedback          \n"
				+ "FROM professorFeedback           \n"
				+ "GROUP BY professorId) AS t2          \n"
				+ "ON t1.userID=t2.professorId);";
		List<Feedback> feedbacks = new ArrayList<>();
		
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(FEEDBACK)) {
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Feedback feedbackObj = new Feedback();
				feedbackObj.setRating(result.getDouble("feedback"));
				feedbackObj.setName(result.getString("name"));
				feedbackObj.setRole(result.getString("role"));
				feedbacks.add(feedbackObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedbacks;
	}

}
