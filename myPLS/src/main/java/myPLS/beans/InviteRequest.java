package myPLS.beans;

/**
 * The InviteRequest class to declare group discussion invite details 
 * @author sandeep
 *
 */
public class InviteRequest{
    private int iniviteRequestID;
    private int userID;
    private int discussionPanelID;
    private int flag;
    public InviteRequest(){}
    public InviteRequest(int iniviteRequestID, int userID, int discussionPanelID, int flag) {
        this.iniviteRequestID = iniviteRequestID;
        this.userID = userID;
        this.discussionPanelID = discussionPanelID;
        this.flag = flag;
    }

	public int getIniviteRequestID() {
		return this.iniviteRequestID;
	}

	public void setIniviteRequestID(int iniviteRequestID) {
		this.iniviteRequestID = iniviteRequestID;
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

	public int getFlag() {
		return this.flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
/**
 *  CREATE TABLE iniviteRequest (
    iniviteRequestID int(11) NOT NULL AUTO_INCREMENT,
    userID int(11),
    discussionPanelID int(11),
    flag int(11),
    PRIMARY KEY (iniviteRequestID),
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (discussionPanelID) REFERENCES discussionPanel(discussionPanelID)
);
 */