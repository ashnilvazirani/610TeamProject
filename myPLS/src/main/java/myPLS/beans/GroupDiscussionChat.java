package myPLS.beans;

public class GroupDiscussionChat {
    private int groupDiscussionChatID;
    private int groupDiscussionID;
    private int userID;
    private String messageContent;
    private int flag;

    public int getGroupDiscussionChatID() {
        return this.groupDiscussionChatID;
    }

    public void setGroupDiscussionChatID(int groupDiscussionChatID) {
        this.groupDiscussionChatID = groupDiscussionChatID;
    }

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

    public String getMessageContent() {
        return this.messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public GroupDiscussionChat(int groupDiscussionChatID, int groupDiscussionID, int userID, String messageContent, int flag) {
        this.groupDiscussionChatID = groupDiscussionChatID;
        this.groupDiscussionID = groupDiscussionID;
        this.userID = userID;
        this.messageContent = messageContent;
        this.flag=flag;
    }
    public GroupDiscussionChat(int groupDiscussionID, int userID, String messageContent){
        this.groupDiscussionID = groupDiscussionID;
        this.userID = userID;
        this.messageContent = messageContent;
        this.flag=0;
    }
}
