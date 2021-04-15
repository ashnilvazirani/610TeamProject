package myPLS.beans;

public class QuizQuestion {
    int quizQuestionID;
    int quizID;
    int questionID;

    public int getQuizQuestionID() {
        return this.quizQuestionID;
    }

    public void setQuizQuestionID(int quizQuestionID) {
        this.quizQuestionID = quizQuestionID;
    }

    public int getQuizID() {
        return this.quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getQuestionID() {
        return this.questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public QuizQuestion(int quizQuestionID, int quizID, int questionID) {
        this.quizQuestionID = quizQuestionID;
        this.quizID = quizID;
        this.questionID = questionID;
    }
    public QuizQuestion(int quizID, int questionID) {
        this.quizID = quizID;
        this.questionID = questionID;
    }
}
