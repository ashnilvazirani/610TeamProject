package myPLS.beans;

public class Question {
    int questionID;
    String problem;
    String option1;
    String option2;
    String option3;
    String option4;
    int correctOption;
    int courseID;

    public int getCourseID() {
        return this.courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCorrectOption() {
        return this.correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public int getQuestionID() {
        return this.questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getProblem() {
        return this.problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getOption1() {
        return this.option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return this.option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return this.option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return this.option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public Question(int questionID, String problem, String option1, String option2, String option3, String option4, int correctOption, int courseID) {
        this.questionID = questionID;
        this.problem = problem;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.courseID = courseID;
    }
    public Question(String problem, String option1, String option2, String option3, String option4, int correctOption, int courseID) {
        this.problem = problem;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.courseID = courseID;
    }
    public Question(String problem, String option1, String option2, String option3, String option4, int correctOption) {
        this.problem = problem;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.courseID=-1;
    }
    @Override
    public String toString(){
        return this.getProblem()+"\n"+
            "1) "+this.getOption1()+"\n"+
            "2) "+this.getOption2()+"\n"+
            "3) "+this.getOption3()+"\n"+
            "4) "+this.getOption4()+"\n"+
            "ans: "+this.getCorrectOption()+"\n";
    }
}
