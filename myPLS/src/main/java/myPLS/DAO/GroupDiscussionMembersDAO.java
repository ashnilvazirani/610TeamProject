package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import myPLS.beans.GroupDiscussion;
import myPLS.beans.User;
import myPLS.services.UserService;

public class GroupDiscussionMembersDAO {
    private static UserService userService;
    public GroupDiscussionMembersDAO(){
        userService = new UserService();
    }
    public List<User> getAllMembersInAGroup(int groupDiscussionID){
        final String GET_USERS_IN_GROUP = "SELECT * FROM groupDiscussionMembers WHERE groupDiscussionID = "+groupDiscussionID;
		List<User> usersInGroup = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_USERS_IN_GROUP)) {
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
                User u = userService.getUserByID(result.getInt("userID"));
                System.out.println(u.getUserID());
				usersInGroup.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersInGroup;
    }
}
