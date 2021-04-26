package myPLS.beans;

public class Grade {
    int gradeID;
    int userID;
    int quizID;
    int courseID;
    int lectureID;
    int totalPoints;
    int pointSecured;

    public int getGradeID() {
        return this.gradeID;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getQuizID() {
        return this.quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getCourseID() {
        return this.courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getLectureID() {
        return this.lectureID;
    }

    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
    }

    public int getTotalPoints() {
        return this.totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getPointSecured() {
        return this.pointSecured;
    }

    public void setPointSecured(int pointSecured) {
        this.pointSecured = pointSecured;
    }

    public Grade(int gradeID, int userID, int quizID, int courseID, int lectureID, int totalPoints, int pointSecured) {
        this.gradeID = gradeID;
        this.userID = userID;
        this.quizID = quizID;
        this.courseID = courseID;
        this.lectureID = lectureID;
        this.totalPoints = totalPoints;
        this.pointSecured = pointSecured;
    }
    public Grade(int userID, int quizID, int courseID, int lectureID, int totalPoints, int pointSecured) {
        this.userID = userID;
        this.quizID = quizID;
        this.courseID = courseID;
        this.lectureID = lectureID;
        this.totalPoints = totalPoints;
        this.pointSecured = pointSecured;
    }
    public Grade(){}
}
