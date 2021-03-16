package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import myPLS.beans.GroupDiscussion;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import myPLS.beans.GroupDiscussionMembers;
public class GroupDiscussionDAO {

    public GroupDiscussionDAO(){}
    public boolean addGroupDiscussion(GroupDiscussion groupDiscussion) {
		final String COURSE = "INSERT INTO groupDiscussion (userID, groupName, groupTopic, flag) VALUES (?,?,?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
			    PreparedStatement preparedStatement = conn.prepareStatement(COURSE)) {
			preparedStatement.setInt(1, groupDiscussion.getUserID());
			preparedStatement.setString(2, groupDiscussion.getGroupName());
			preparedStatement.setString(3, groupDiscussion.getGroupTopic());
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
	public boolean addMembersToGroup(GroupDiscussionMembers groupDiscussionMembers) {
		if(checkIfMemberExists(groupDiscussionMembers))
			return false;
		final String COURSE = "INSERT INTO groupDiscussionMembers (userID, groupDiscussionID, flag) VALUES (?,?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(COURSE)) {
			preparedStatement.setInt(1, groupDiscussionMembers.getUserID());
			preparedStatement.setInt(2, groupDiscussionMembers.getGroupDiscussionID());
			preparedStatement.setInt(3, 0);
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static boolean checkIfMemberExists(GroupDiscussionMembers groupDiscussionMembers){
		final String GET_MEMBER = "SELECT * FROM groupDiscussionMembers WHERE userID = "+groupDiscussionMembers.getUserID()+" AND groupDiscussionID = "+groupDiscussionMembers.getGroupDiscussionID();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_MEMBER)) {
					ResultSet result = preparedStatement.executeQuery();
					return result.next();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			return false;
	}
    public List<GroupDiscussion> getGroups() {
        final String GET_GROUPS = "SELECT * FROM groupDiscussion";
		List<GroupDiscussion> groupDiscussions = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_GROUPS)) {
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				String groupName = result.getString("groupName");
				String groupTopic = result.getString("groupTopic");
				int userID = result.getInt("userID");
                int groupDiscussionID = result.getInt("groupDiscussionID");
				GroupDiscussion groupDiscussion = new GroupDiscussion(groupDiscussionID, userID, groupName, groupTopic);
				groupDiscussions.add(groupDiscussion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupDiscussions;
    }
	public GroupDiscussion getGroupDiscussionFromID(int groupDiscussionID){
        final String GET_GROUP_NAME = "SELECT * FROM groupDiscussion WHERE groupDiscussionID = "+groupDiscussionID;
		List<GroupDiscussion> groups = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_GROUP_NAME)) {
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
                int userID = result.getInt("userID");
                String groupName = result.getString("groupName");
                String groupTopic = result.getString("groupTopic");
				GroupDiscussion groupDiscussion = new GroupDiscussion(groupDiscussionID, userID, groupName, groupTopic);
                groups.add(groupDiscussion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groups.get(0);  
    }
}
