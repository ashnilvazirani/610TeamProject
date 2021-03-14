package myPLS.beans;

public class GroupDiscussionMembers {
    private int groupDiscussionMembersID;
    private int groupDiscussionID;
    private int userID;

    public int getGroupDiscussionMembersID() {
        return this.groupDiscussionMembersID;
    }

    public void setGroupDiscussionMembersID(int groupDiscussionMembersID) {
        this.groupDiscussionMembersID = groupDiscussionMembersID;
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

    public GroupDiscussionMembers(int groupDiscussionMembersID, int groupDiscussionID, int userID) {
        this.groupDiscussionMembersID = groupDiscussionMembersID;
        this.groupDiscussionID = groupDiscussionID;
        this.userID = userID;
    }
    public GroupDiscussionMembers(int groupDiscussionID, int userID) {
        this.groupDiscussionID = groupDiscussionID;
        this.userID = userID;
    }
}
/**
 * CREATE TABLE groupDiscussionMembers (
    groupDiscussionMembersID int(11) NOT NULL AUTO_INCREMENT,
    userID int(11),
    groupDiscussionID int(11),
    flag int(11),
    PRIMARY KEY (groupDiscussionMembersID),
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (groupDiscussionID) REFERENCES groupDiscussion(groupDiscussionID)
);
 */