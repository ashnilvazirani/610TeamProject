package myPLS.services;

import java.util.ArrayList;
import java.util.List;

import myPLS.DAO.GroupDiscussionChatDAO;
import myPLS.DAO.GroupDiscussionDAO;
import myPLS.DAO.GroupDiscussionMembersDAO;
import myPLS.beans.GroupDiscussion;
import myPLS.beans.GroupDiscussionChat;
import myPLS.beans.GroupDiscussionMembers;
import myPLS.beans.User;
import spark.Request;
import spark.Response;
public class GroupDiscussionService {
    private GroupDiscussionDAO groupDiscussionDAO;
    private GroupDiscussionMembersDAO groupDiscussionMembersDAO;
    private GroupDiscussionChatDAO groupDiscussionChatDAO;
    public static ArrayList<GroupDiscussionChat> chat = new ArrayList<GroupDiscussionChat>();
    
    public GroupDiscussionService(){
        groupDiscussionDAO = new GroupDiscussionDAO();
        groupDiscussionMembersDAO = new GroupDiscussionMembersDAO();
        groupDiscussionChatDAO = new GroupDiscussionChatDAO();
    }
    public List<GroupDiscussion> getGroups() {
		return groupDiscussionDAO.getGroups();
	}
    public List<User> getMembersFromGroupDiscussionID(Request request, Response response){
        int groupDiscussionID = request.queryParams("groupDiscussionID") != null ? Integer.parseInt(request.queryParams("groupDiscussionID")) : -1;
        return groupDiscussionMembersDAO.getAllMembersInAGroup(groupDiscussionID);
    }
    public GroupDiscussion getGroupDiscussionNameByID(Request request, Response response){
        // int groupDiscussionID = request.queryParams("groupDiscussionID") != null ? Integer.parseInt(request.queryParams("groupDiscussionID")) : -1;
        int groupDiscussionID=request.params(":groupDiscussionID") != null ? Integer.parseInt(request.params(":groupDiscussionID")) : Integer.parseInt(request.queryParams("groupDiscussionID"));
        return groupDiscussionDAO.getGroupDiscussionFromID(groupDiscussionID);
    }
    public boolean createADiscussionGroup(Request request, Response response){
        String groupName=request.queryParams("groupName") != null ? (request.queryParams("groupName")) : "unknown";
        int userID = request.queryParams("userID") != null ? Integer.parseInt(request.queryParams("userID")) : -1;
        String groupTopic = request.queryParams("groupTopic") != null ? request.queryParams("groupTopic") : "unknown";
        GroupDiscussion groupDiscussion =  new GroupDiscussion(userID, groupName, groupTopic);
        if(groupDiscussionDAO.addGroupDiscussion(groupDiscussion)){
            return true;
        }else{
            return false;
        }
    }
    public boolean addMemberToGroup(Request request, Response response){
        int groupDiscussionID=request.queryParams("groupDiscussionID") != null ? Integer.parseInt(request.queryParams("groupDiscussionID")) : Integer.parseInt(request.params(":groupDiscussionID"));
        int userID=request.queryParams("userID") != null ? Integer.parseInt(request.queryParams("userID")) : Integer.parseInt(request.params(":userID"));
        GroupDiscussionMembers groupDiscussionMembers = new GroupDiscussionMembers(groupDiscussionID, userID);
        return groupDiscussionDAO.addMembersToGroup(groupDiscussionMembers);
    }
    public List<GroupDiscussionChat> getGroupChats(Request request, Response response){
        // int groupDiscussionID=request.queryParams("groupDiscussionID") != null ? Integer.parseInt(request.queryParams("groupDiscussionID")) : -1;
        int groupDiscussionID=request.params(":groupDiscussionID") != null ? Integer.parseInt(request.params(":groupDiscussionID")) : -1;
        return groupDiscussionChatDAO.getChatsByGroupDiscussionID(groupDiscussionID);
    }
    public void postAMessage(Request request, Response response){
        int groupDiscussionID=request.queryParams("groupDiscussionID") != null ? Integer.parseInt(request.queryParams("groupDiscussionID")) : -1;
        int userID=request.queryParams("userID") != null ? Integer.parseInt(request.queryParams("userID")) : -1;
        String messageContent=request.queryParams("messageContent") != null ? request.queryParams("messageContent") : "NONE";
        GroupDiscussionChat groupDiscussionChat = new GroupDiscussionChat(groupDiscussionID, userID, messageContent);
        if(groupDiscussionChatDAO.postMessage(groupDiscussionChat)){
            response.redirect("/viewGroupChats/"+groupDiscussionID);
        }
    }
    
   
}


