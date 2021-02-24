package myPLS.beans;

import java.util.ArrayList;
import java.util.Map;

public class Learner {
    private int learnerID;
    private String learnerName;
    private String learnerUsername;
    private Map<String, String> addressInformation; // addressLane, city, zip, state,
    private String contactInformation;
    private String universityEmail;
    private String personalEmail;
    // private ArrayList<String> socialNetworks;
    private Map<String, String> socialNetworks;
    private Map<String, String> additionalComments; // key: tag, value: comment
    private int streamID;
    private int userID;

    public Learner(){}
    
    public Learner(String learnerName, String learnerUsername, Map<String,String> addressInformation, String contactInformation, String universityEmail, String personalEmail, Map<String,String> socialNetworks, Map<String,String> additionalComments, int streamID, int userID) {
        this.learnerName = learnerName;
        this.learnerUsername = learnerUsername;
        this.addressInformation = addressInformation;
        this.contactInformation = contactInformation;
        this.universityEmail = universityEmail;
        this.personalEmail = personalEmail;
        this.socialNetworks = socialNetworks;
        this.additionalComments = additionalComments;
        this.streamID = streamID;
        this.userID = userID;
    }
    
    public int getLearnerID() {
        return this.learnerID;
    }

    public void setLearnerID(int learnerID) {
        this.learnerID = learnerID;
    }

    public String getLearnerName() {
        return this.learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public String getLearnerUsername() {
        return this.learnerUsername;
    }

    public void setLearnerUsername(String learnerUsername) {
        this.learnerUsername = learnerUsername;
    }

    public String getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getUniversityEmail() {
        return this.universityEmail;
    }

    public void setUniversityEmail(String universityEmail) {
        this.universityEmail = universityEmail;
    }

    public String getPersonalEmail() {
        return this.personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
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
