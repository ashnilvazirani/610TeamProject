package myPLS.beans;

public class Course {
    private String courseName;
    private String courseField;
    private int courseYear;
    private String courseDescription;
    private int courseDuration;
    private int streamID;
    public Course(){}
    public Course(String courseName, String courseField, String courseYear, String courseDescription, String courseDuration, int streamID){
      
    }
    public int getStreamID() {
        return this.streamID;
    }

    public void setStreamID(int streamID) {
        this.streamID = streamID;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseField() {
        return this.courseField;
    }

    public void setCourseField(String courseField) {
        this.courseField = courseField;
    }

    public int getCourseYear() {
        return this.courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public String getCourseDescription() {
        return this.courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCourseDuration() {
        return this.courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    public int getStreamId() {
        return this.streamID;
    }

    public void setStreamId(int streamID) {
        this.streamID = streamID;
    }
}
/**
 *  CREATE TABLE course  (
    courseID int NOT NULL AUTO_INCREMENT,
    courseName varchar(255) NOT NULL,
    courseField varchar(255),
    courseYear varchar(255),
    courseDescription varchar(255),
    courseDuration int(11),
    streamID int(11),
    PRIMARY KEY (courseID),
    FOREIGN KEY (streamID) REFERENCES stream(streamID)
);
*/
