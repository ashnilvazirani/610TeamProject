package myPLS.beans;

/**
 * The course class to declare course details like stream, course name, course duration, course id, professor id
 * @author ashnil
 *
 */
public class Course {
    private int courseId;
    private String courseName;
    private String courseDescription;
    private int courseDuration;
    private int streamID;
    private String streamName;
    private int professorId;
    private int isDeleted;

    public int getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
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
    
    public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
    
    public int getCourseId() {
		return courseId;
	}
    
    public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
    
    public String getStreamName() {
		return streamName;
	}
    
    public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
    
    public int getProfessorId() {
		return professorId;
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
