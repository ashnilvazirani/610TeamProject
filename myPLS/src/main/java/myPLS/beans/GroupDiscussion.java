package myPLS.beans;

public class GroupDiscussion {
    private int groupDiscussionID;
    private int userID; // admin ID
    private String groupName;
    private String groupTopic;
    private int flag;

    public int getGroupDiscussionID() {
        return this.groupDiscussionID;
    }

    public void setGroupDiscussionID(int groupDiscussionID) {
        this.groupDiscussionID = groupDiscussionID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupTopic() {
        return this.groupTopic;
    }

    public void setGroupTopic(String groupTopic) {
        this.groupTopic = groupTopic;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public GroupDiscussion(int groupDiscussionID, int userID, String groupName, String groupTopic) {
        this.groupDiscussionID = groupDiscussionID;
        this.userID = userID;
        this.groupName = groupName;
        this.groupTopic = groupTopic;
        this.flag=0;
    }
    public GroupDiscussion(int userID, String groupName, String groupTopic) {
        this.userID = userID;
        this.groupName = groupName;
        this.groupTopic = groupTopic;
        this.flag=0;
    }
}
