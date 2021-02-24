package myPLS.beans;

public class MembersInDiscussion{
    private int membersInDiscussionID;
    private int userID;
    private int discussionPanelID;
    private int flag;
    public MembersInDiscussion(){}
    public MembersInDiscussion(int membersInDiscussionID, int userID, int discussionPanelID, int flag) {
        this.membersInDiscussionID = membersInDiscussionID;
        this.userID = userID;
        this.discussionPanelID = discussionPanelID;
        this.flag = flag;
    }

	public int getFlag() {
		return this.flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getMembersInDiscussionID() {
		return this.membersInDiscussionID;
	}

	public void setMembersInDiscussionID(int membersInDiscussionID) {
		this.membersInDiscussionID = membersInDiscussionID;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getDiscussionPanelID() {
		return this.discussionPanelID;
	}

	public void setDiscussionPanelID(int discussionPanelID) {
		this.discussionPanelID = discussionPanelID;
	}

}
/**
 *  CREATE TABLE membersInDiscussion (
    membersInDiscussionID int(11) NOT NULL AUTO_INCREMENT,
    userID int(11),
    discussionPanelID int(11),
    flag int(11),
    PRIMARY KEY (membersInDiscussionID),
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (discussionPanelID) REFERENCES discussionPanel(discussionPanelID)
);
 */