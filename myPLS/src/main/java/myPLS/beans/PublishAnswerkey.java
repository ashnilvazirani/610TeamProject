package myPLS.beans;

public class PublishAnswerkey {
    int publishAnswerKeyID;
    int quizID;
    int flag;

    public int getPublishAnswerKeyID() {
        return this.publishAnswerKeyID;
    }

    public void setPublishAnswerKeyID(int publishAnswerKeyID) {
        this.publishAnswerKeyID = publishAnswerKeyID;
    }

    public int getQuizID() {
        return this.quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public PublishAnswerkey(int publishAnswerKeyID, int quizID, int flag) {
        this.publishAnswerKeyID = publishAnswerKeyID;
        this.quizID = quizID;
        this.flag = flag;
    }

    public PublishAnswerkey(int quizID, int flag) {
        this.quizID = quizID;
        this.flag = flag;
    }
    public PublishAnswerkey(){}
}
