package myPLS.beans;

/**
 * The CourseGroupChat class to declare course discussion group chats details like set course group id, get message content
 * @author ashnil
 *
 */
public class CourseGroupChat{
    private int courseGroupChatID;
    private int courseGroupID;
    private int userID;
    private String userName;
    private String messageContent;
    
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCourseGroupChatID() {
        return this.courseGroupChatID;
    }

    public void setCourseGroupChatID(int courseGroupChatID) {
        this.courseGroupChatID = courseGroupChatID;
    }

    public int getCourseGroupID() {
        return this.courseGroupID;
    }

    public void setCourseGroupID(int courseGroupID) {
        this.courseGroupID = courseGroupID;
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

    public CourseGroupChat(int courseGroupChatID, int courseGroupID, int userID,String userName, String messageContent) {
        this.courseGroupChatID = courseGroupChatID;
        this.courseGroupID = courseGroupID;
        this.userID = userID;
        this.userName = userName;
        this.messageContent = messageContent;
    }
    public CourseGroupChat(int courseGroupID, int userID,String userName, String messageContent) {
        this.courseGroupID = courseGroupID;
        this.userID = userID;
        this.userName = userName;
        this.messageContent = messageContent;
    }
    public CourseGroupChat(){}
}
/** 
 *   CREATE TABLE courseGroupChat (
    courseGroupChatID int(11) NOT NULL AUTO_INCREMENT,
    userID int(11),
    courseGroupID int(11),
    messageContent varchar(255),
    userName varchar(255),
    flag int(11),
    PRIMARY KEY (courseGroupChatID),
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (courseGroupID) REFERENCES courseGroup(courseGroupID)
);
*/