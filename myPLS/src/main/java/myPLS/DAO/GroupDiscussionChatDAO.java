package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import myPLS.beans.GroupDiscussion;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import myPLS.beans.GroupDiscussionMembers;
import myPLS.beans.GroupDiscussionChat;
public class GroupDiscussionChatDAO {
    public List<GroupDiscussionChat> getChatsByGroupDiscussionID(int groupDiscussionID){
        final String GET_USERS_IN_GROUP = "SELECT * FROM groupDiscussionChat WHERE groupDiscussionID = "+groupDiscussionID;
		List<GroupDiscussionChat> chats = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_USERS_IN_GROUP)) {
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
                int groupDiscussionChatID = result.getInt("groupDiscussionChatID");
                int userID = result.getInt("userID");
                String messageContent = result.getString("messageContent");
                int flag = result.getInt("flag");
                GroupDiscussionChat groupDiscussionChat = new GroupDiscussionChat(groupDiscussionChatID, groupDiscussionID, userID, messageContent, flag);
				chats.add(groupDiscussionChat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chats;
    }
    public boolean postMessage(GroupDiscussionChat groupDiscussionChat) {
		final String POST_MESSAGE = "INSERT INTO groupDiscussionChat (userID, groupDiscussionID, messageContent, flag) VALUES (?,?,?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(POST_MESSAGE)) {
			preparedStatement.setInt(1, groupDiscussionChat.getUserID());
			preparedStatement.setInt(2, groupDiscussionChat.getGroupDiscussionID());
			preparedStatement.setString(3, groupDiscussionChat.getMessageContent());
			preparedStatement.setInt(4, 0);
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
