package myPLS.services;

import java.util.List;

import myPLS.DAO.QuizDAO;
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
    public List<Question> getQuestionsForQuiz(int quizID){
        return this.quizDAO.getAllQuestionsForQuiz(quizID);
    }
}