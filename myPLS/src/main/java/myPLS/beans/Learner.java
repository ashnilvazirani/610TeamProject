package myPLS.beans;

public class Learner {
    private String learnerName;
    private int streamID;
    private int userID;
    private int courseID;
    
    public Learner(){}
    
    public String getLearnerName() {
        return this.learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }
    public int getStreamID() {
        return this.streamID;
    }

    public void setStreamID(int streamID) {
        this.streamID = streamID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
    
    public int getCourseID() {
		return courseID;
	}
}
/**
 *  CREATE TABLE learner (
    learnerID int(11) NOT NULL AUTO_INCREMENT,
    userID int(11),
    streamID int(11),
    learnerName varchar(255),
    learnerUsername varchar(255),
    addressInformation varchar(255),
    contactInformation varchar(255),
    universityEmail varchar(255),
    personalEmail varchar(255),
    socialNetworks varchar(255),
    additionalComments varchar(255),
    PRIMARY KEY (learnerID),
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (streamID) REFERENCES stream(streamID)
);
 */
