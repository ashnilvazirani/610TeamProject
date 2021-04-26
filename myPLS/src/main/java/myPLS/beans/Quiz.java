package myPLS.beans;

public class Quiz {
    int quizID;
    String quizTime;
    String quizTopic;

    public String getQuizTopic() {
        return this.quizTopic;
    }

    public void setQuizTopic(String quizTopic) {
        this.quizTopic = quizTopic;
    }

    int userID;
    int courseID;
    int numberOfQuestions;
    int lectureId;

    public int getLectureId() {
        return this.lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public int getQuizID() {
        return this.quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getQuizTime() {
        return this.quizTime;
    }

    public void setQuizTime(String quizTime) {
        this.quizTime = quizTime;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCourseID() {
        return this.courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getNumberOfQuestions() {
        return this.numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public Quiz(int quizID, String quizTime,String quizTopic, int userID, int courseID, int numberOfQuestions, int lectureId) {
        this.quizTopic = quizTopic;
        this.quizID = quizID;
        this.quizTime = quizTime;
        this.userID = userID;
        this.courseID = courseID;
        this.numberOfQuestions = numberOfQuestions;
        this.lectureId = lectureId;
    }
    public Quiz(String quizTime, String quizTopic, int userID, int courseID, int numberOfQuestions, int lectureId) {
        this.quizTime = quizTime;
        this.userID = userID;
        this.quizTopic = quizTopic;
        this.courseID = courseID;
        this.numberOfQuestions = numberOfQuestions;
        this.lectureId = lectureId;
    }
    public Quiz(){}
}
