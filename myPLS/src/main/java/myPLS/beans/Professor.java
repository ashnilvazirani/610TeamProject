package myPLS.beans;
import java.util.ArrayList;
import java.util.Map;

public class Professor {
    private int professorID;
    private String professorName;
    private String professorUsername;
    private Map<String, String> addressInformation; // addressLane, city, zip, state,
    private String contactInformation;
    private String universityEmail;
    private String personalEmail;
    // private ArrayList<String> socialNetworks;
    private Map<String, String> socialNetworks;
    private Map<String, String> additionalComments; // key: tag, value: comment
    private int streamID;
    private int userID;
    public Professor(){}
    public Professor(int professorID, String professorName, String professorUsername, Map<String,String> addressInformation, String contactInformation, String universityEmail, String personalEmail, Map<String,String> socialNetworks, Map<String,String> additionalComments, int streamID, int userID) {
        this.professorID = professorID;
        this.professorName = professorName;
        this.professorUsername = professorUsername;
        this.addressInformation = addressInformation;
        this.contactInformation = contactInformation;
        this.universityEmail = universityEmail;
        this.personalEmail = personalEmail;
        this.socialNetworks = socialNetworks;
        this.additionalComments = additionalComments;
        this.streamID = streamID;
        this.userID = userID;
    }

    public int getProfessorID() {
        return this.professorID;
    }

    public void setProfessorID(int professorID) {
        this.professorID = professorID;
    }

    public String getProfessorName() {
        return this.professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorUsername() {
        return this.professorUsername;
    }

    public void setProfessorUsername(String professorUsername) {
        this.professorUsername = professorUsername;
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
 *  CREATE TABLE professor (
    professorID int(11) NOT NULL AUTO_INCREMENT,
    userID int(11),
    streamID int(11),
    professorName varchar(255),
    professorUsername varchar(255),
    addressInformation varchar(255),
    contactInformation varchar(255),
    universityEmail varchar(255),
    personalEmail varchar(255),
    socialNetworks varchar(255),
    additionalComments varchar(255),
    PRIMARY KEY (professorID),
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (streamID) REFERENCES stream(streamID)
);
 */