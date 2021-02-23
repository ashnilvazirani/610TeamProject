package myPLS.beans;

public class DiscussionReply {
    private int discussionReplyID;
    private int userID;
    private int discussionPanelID;
    private String replyContent;
    private int flag; //(0/1)
    public DiscussionReply(){}

    public DiscussionReply(int discussionReplyID, int userID, int discussionPanelID, String replyContent) {
        this.discussionReplyID = discussionReplyID;
        this.userID = userID;
        this.discussionPanelID = discussionPanelID;
        this.replyContent = replyContent;
    }

	public int getDiscussionReplyID() {
		return this.discussionReplyID;
	}

	public void setDiscussionReplyID(int discussionReplyID) {
		this.discussionReplyID = discussionReplyID;
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
    public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
}
/**
 *  CREATE TABLE discussionReply (
    discussionReplyID int(11) NOT NULL AUTO_INCREMENT,
    userID int(11),
    discussionPanelID int(11),
    replyContent varchar(255),
    flag int(11),
    PRIMARY KEY (discussionReplyID),
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (discussionPanelID) REFERENCES discussionPanel(discussionPanelID)
);
 */