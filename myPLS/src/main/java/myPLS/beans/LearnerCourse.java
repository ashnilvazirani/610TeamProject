package myPLS.beans;

public class LearnerCourse {
    private int learnerCourseID;
    private int learnerID;
    private int courseID;
    LearnerCourse(){}
    LearnerCourse(int learnerID, int courseID){
        this.learnerID=learnerID;
        this.courseID=courseID;
    }
    public int getLearnerCourseID() {
        return this.learnerCourseID;
    }

    public void setLearnerCourseID(int learnerCourseID) {
        this.learnerCourseID = learnerCourseID;
    }

    public int getLearnerID() {
        return this.learnerID;
    }

    public void setLearnerID(int learnerID) {
        this.learnerID = learnerID;
    }

    public int getCourseID() {
        return this.courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

}
/**
 *  CREATE TABLE learner_course  (
    learnerCourseID int NOT NULL AUTO_INCREMENT,
    learnerID int(11),
    courseID int(11),
    PRIMARY KEY (learnerCourseID),
    FOREIGN KEY (learnerID) REFERENCES learner(learnerID),
    FOREIGN KEY (courseID) REFERENCES course(courseID)
);
 */
