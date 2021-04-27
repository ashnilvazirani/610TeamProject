package myPLS.beans;

/**
 * The CourseGroupMembers class to declare course discussion group member details like course id, course group  id, user id, course group members
 * @author abriti
 *
 */
public class CourseGroupMembers {
    private int courseGroupMembersID;
    private int userID;
    private int courseGroupID;

    public int getCourseGroupMembersID() {
        return this.courseGroupMembersID;
    }

    public void setCourseGroupMembersID(int courseGroupMembersID) {
        this.courseGroupMembersID = courseGroupMembersID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCourseGroupID() {
        return this.courseGroupID;
    }

    public void setCourseGroupID(int courseGroupID) {
        this.courseGroupID = courseGroupID;
    }

    public CourseGroupMembers(int courseGroupMembersID, int userID, int courseGroupID) {
        this.courseGroupMembersID = courseGroupMembersID;
        this.userID = userID;
        this.courseGroupID = courseGroupID;
    }
    public CourseGroupMembers(int userID, int courseGroupID) {
        this.userID = userID;
        this.courseGroupID = courseGroupID;
    }
}

/**
 * CREATE TABLE courseGroupMembers (
    courseGroupMembersID int(11) NOT NULL AUTO_INCREMENT,
    userID int(11),
    courseGroupID int(11),
    PRIMARY KEY (courseGroupMembersID),
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (courseGroupID) REFERENCES courseGroup(courseGroupID)
);
 */