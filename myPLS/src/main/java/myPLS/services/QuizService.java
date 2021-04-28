package myPLS.services;

import java.util.List;
import java.util.Map;

import myPLS.DAO.QuizDAO;
import myPLS.beans.Grade;
import myPLS.beans.Question;
import myPLS.beans.Quiz;
import myPLS.beans.QuizQuestion;

public class QuizService{
    QuizDAO quizDAO;
    public QuizService(){
        this.quizDAO = new QuizDAO(); 
    }
    public boolean addQuestion(Question q){
        return this.quizDAO.addQuestion(q);
    }
    public List<Question> getQuestionsAddedByProfessor(int professorID){
        return this.quizDAO.getQuestionsAddedByProfessor(professorID);
    }
    public int getQuizNumberForCourse(int courseID) {
        return this.quizDAO.getQuizNumberForCourse(courseID);
    }
    public int publishQuiz(Quiz test) {
        return this.quizDAO.publishQuiz(test);
    }
    public void addQuizQuestion(QuizQuestion q) {
        this.quizDAO.addQuizQuestion(q);
    }
    public List<Quiz> getAllQuizForCourse(int courseId) {
        return this.quizDAO.getAllQuizForCourse(courseId);
    }
    public List<Quiz> getQuizForLecture(int courseId, int lectureId) {
        return this.quizDAO.getQuizForLecture(courseId, lectureId);
    }
    public List<Question> getQuestionsForQuiz(int quizID){
        return this.quizDAO.getAllQuestionsForQuiz(quizID);
    }
    public int gradeQuiz(Map<Integer, Integer> userAnswers, int quizID, int lectureId, int userID) {
        List<Question> getQuestions = this.quizDAO.getAllQuestionsForQuiz(quizID);
        int totalPoints = getQuestions.size()*10;
        Quiz quiz = this.quizDAO.getQuizByQuizID(quizID);
        int pointSecured = 0;
        for(int i=0;i<getQuestions.size();i++){
            int choice = userAnswers.get(getQuestions.get(i).getQuestionID());
            int answer = getQuestions.get(i).getCorrectOption();
            if(choice==answer){
                pointSecured+=10;
            }
        }
        System.out.println("Points Secured: "+pointSecured);
        Grade g = new Grade(userID, quizID, quiz.getCourseID(), lectureId, totalPoints, pointSecured);
        System.out.println("GRADE CREATED");
        return this.quizDAO.submitGradeForStudent(g);
    }
    public List<Grade> getGradesForStudent(int userId) {
        return this.quizDAO.getGradesForStudent(userId);
    }
    public List<Quiz> getAllQuizForLecture(int lectureID) {
        return this.quizDAO.getAllQuizForLecture(lectureID);
    }
}