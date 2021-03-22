package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreReqCourseDAOImpl implements PreReqCourseDAO{
	
	@Override
	public boolean addCourse(int courseId, int preReqCourseId) {
		
		final String COURSE = "INSERT INTO PreRequisiteCourse (courseId,preReqCourse) VALUES (?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(COURSE)) {
			preparedStatement.setInt(1, courseId);
			preparedStatement.setInt(2, preReqCourseId);
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteCourse(int courseId, int preReqCourseId) {
		final String DELETE_COURSE = "DELETE from PreRequisiteCourse where courseId = ? and preReqCourse= ?";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_COURSE)) {
			preparedStatement.setInt(1, courseId);
			preparedStatement.setInt(2, preReqCourseId);
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
