package myPLS.beans;

public class CourseGroup {
    private int courseGroupID;

    public int getCourseGroupID() {
        return this.courseGroupID;
    }

    public void setCourseGroupID(int courseGroupID) {
        this.courseGroupID = courseGroupID;
    }
    
    private int courseID;

    public int getCourseID() {
        return this.courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    private int userID; //this should be the userID where role = 'professor';
    private String courseName;
    private String courseStream;
    private int flag;

    

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseStream() {
        return this.courseStream;
    }

    public void setCourseStream(String courseStream) {
        this.courseStream = courseStream;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public CourseGroup(int courseGroupID, int userID, String courseName, String courseStream) {
        this.courseGroupID = courseGroupID;
        this.userID = userID;
        this.courseName = courseName;
        this.courseStream = courseStream;
        this.flag=0;
    }
    public CourseGroup(int userID, String courseName, String courseStream) {
        this.userID = userID;
        this.courseName = courseName;
        this.courseStream = courseStream;
        this.flag=0;
    }
    public CourseGroup(){}
}

/**
 * CREATE TABLE courseGroup (
    courseGroupID int(11) NOT NULL AUTO_INCREMENT,
    userID int(11),
    courseID int(11),
    courseName varchar(255),
    courseStream varchar(255),
    flag int(11),
    PRIMARY KEY (courseGroupID),
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (courseID) REFERENCES course(courseID)
);
 */
