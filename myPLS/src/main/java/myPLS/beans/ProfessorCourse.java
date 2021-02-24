package myPLS.beans;

public class ProfessorCourse {
    private int professorCourseID;
    private int learnerID;
    private int courseID;

    public ProfessorCourse(){}
    
    public ProfessorCourse(int learnerID, int courseID) {
        this.learnerID = learnerID;
        this.courseID = courseID;
    }
    public int getProfessorCourseID() {
        return this.professorCourseID;
    }

    public void setProfessorCourseID(int professorCourseID) {
        this.professorCourseID = professorCourseID;
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
