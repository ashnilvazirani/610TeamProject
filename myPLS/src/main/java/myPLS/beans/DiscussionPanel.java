package myPLS.beans;

/**
 * The DiscussionPanel class to declare discussion panel details like course id, user id, discussion panel id, message content
 * @author sandeep
 *
 */
public class DiscussionPanel {
    private int discussionPanelID;
    private int userID;
    private int courseID;
    private String messageContent;
    private int flag; //(0/1)

    public DiscussionPanel(){}

    public DiscussionPanel(int discussionPanelID, int userID, int courseID, String messageContent, int flag) {
        this.discussionPanelID = discussionPanelID;
        this.userID = userID;
        this.courseID = courseID;
        this.messageContent = messageContent;
        this.flag = flag;
    }

	public int getDiscussionPanelID() {
		return this.discussionPanelID;
	}

	public void setDiscussionPanelID(int discussionPanelID) {
		this.discussionPanelID = discussionPanelID;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getCourseID() {
		return this.courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
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
}
/**
 *  CREATE TABLE discussionPanel (
    discussionPanelID int(11) NOT NULL AUTO_INCREMENT,
    userID int(11),
    courseID int(11),
    messageContent varchar(255),
    flag int(11),
    PRIMARY KEY (discussionPanelID),
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (courseID) REFERENCES course(courseID)
);
 */