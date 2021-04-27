package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import myPLS.beans.Grade;
import myPLS.beans.Question;
import myPLS.beans.Quiz;
import myPLS.beans.QuizQuestion;

/**
 * This QuizDAO class to implement quiz functionality
 * @author ashnil
 *
 */
public class QuizDAO {
	
	/**
	 * This addQuestion method will add questions for the quiz
	 * @param q question to be asked in the quiz
	 * @return true if question is added
	 */
    public boolean addQuestion(Question q){
        final String LEARNER_EROLLMENT = "INSERT INTO question (problem, option1, option2, option3, option4, numberOfCorrectAnswers, correctAnswer, courseID, professorID) VALUES (?,?,?,?,?,?,?,?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(LEARNER_EROLLMENT)) {
			preparedStatement.setString(1, q.getProblem());
            preparedStatement.setString(2, q.getOption1());
            preparedStatement.setString(3, q.getOption2());
            preparedStatement.setString(4, q.getOption3());
            preparedStatement.setString(5, q.getOption4());
            preparedStatement.setInt(6, 1);
            preparedStatement.setInt(7, q.getCorrectOption());
            preparedStatement.setInt(8, q.getCourseID());
            preparedStatement.setInt(9, q.getProfessorID());
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
    
    /**
     * This getAllQuestion method will get list of all questions for the quiz
     * @return list of all questions in a quiz
     */
    public  List<Question> getAllQuestion(){
        final String selectQuery = "SELECT * FROM question";
        try (Connection conn = JDBCConnection.geConnection();
            Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(selectQuery);
            List<Question> questions = new ArrayList<>();
            while(rs.next()){
                Question q =  new Question();
                q.setProblem(rs.getString("problem"));
                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));
                q.setQuestionID(rs.getInt("questionID"));
                q.setCourseID(rs.getInt("courseID"));
                q.setCorrectOption(rs.getInt("correctAnswer"));
                q.setProfessorID(rs.getInt("professorID"));
                questions.add(q);
            }
            return questions;
            }catch (SQLException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }    
    }
    
    /**
     * 
     * This getQuestionsAddedByProfessor method will get list of all questions added by the professor
     * @param professorID ID of professor who adds the quiz question
     * @return list of all questions added by the professor for a given quiz
     */
    public  List<Question> getQuestionsAddedByProfessor(int professorID){
        final String selectQuery = "SELECT * FROM question WHERE professorID = "+professorID;
        try (Connection conn = JDBCConnection.geConnection();
            Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(selectQuery);
            List<Question> questions = new ArrayList<>();
            while(rs.next()){
                Question q =  new Question();
                q.setProblem(rs.getString("problem"));
                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));
                q.setQuestionID(rs.getInt("questionID"));
                q.setCourseID(rs.getInt("courseID"));
                q.setCorrectOption(rs.getInt("correctAnswer"));
                q.setProfessorID(rs.getInt("professorID"));
                questions.add(q);
            }
            return questions;
            }catch (SQLException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }    
    }
    
    /**
     * This getQuizNumberForCourse method will get number of the quiz for the course
     * @param courseID ID of the course for which quiz is generated
     * @return the quiz number
     */
    public int getQuizNumberForCourse(int courseID){
        final String selectQuery = "SELECT COUNT(*) FROM quiz WHERE courseID = "+courseID;
        try (Connection conn = JDBCConnection.geConnection();
            Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(selectQuery);
            int count = 0;
            if(rs.next()){
                count = rs.getInt("COUNT(*)");
            }
            return (++count);
            }catch (SQLException e) {
                e.printStackTrace();
                return -1;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }    
    }
    
    /**
     *  This publishQuiz method will publish the quiz in myPLS
     * @param test quiz details
     * @return true if quiz is published
     */
    public int publishQuiz(Quiz test) {
        final String quiz = "INSERT INTO quiz (quizTime, userID, courseID, numberOfQuestions, quizTopic, lectureId) VALUES (?,?,?,?,?,?)";
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(quiz, PreparedStatement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, test.getQuizTime());
            preparedStatement.setInt(2, test.getUserID());
            preparedStatement.setInt(3, test.getCourseID());
            preparedStatement.setInt(4, test.getNumberOfQuestions());
			preparedStatement.setString(5, test.getQuizTopic());
            preparedStatement.setInt(6, test.getLectureId());
			int row = preparedStatement.executeUpdate();
            if(row>0){
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return Integer.parseInt((generatedKeys.getLong(1)+""));
                }
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return -1;
    }
    
    /**
     *  This addQuizQuestion method will add questions to the quiz in myPLS
     * @param q questions for quiz
     * @return true if question added to the quiz
     */
    public boolean addQuizQuestion(QuizQuestion q) {
        final String quiz = "INSERT INTO quizQuestion (quizID, questionID) VALUES (?,?)";
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(quiz)) {
            preparedStatement.setInt(1, q.getQuizID());
            preparedStatement.setInt(2, q.getQuestionID());
			int row = preparedStatement.executeUpdate();
            System.out.println("INSERTED ID: "+row);
            if(row>0){
                return true;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }
    
    /**
     * This getAllQuizForCourse method will get all the quiz for particular course
     * @param courseId ID of course
     * @return list of quiz for the course
     */
    public List<Quiz> getAllQuizForCourse(int courseId) {
        final String selectQuery = "SELECT * FROM quiz WHERE courseID = "+courseId;
        try (Connection conn = JDBCConnection.geConnection();
            Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(selectQuery);
            List<Quiz> quizzes= new ArrayList<>();
            while(rs.next()){
                Quiz q = new Quiz();
                q.setCourseID(rs.getInt("courseID"));
                q.setNumberOfQuestions(rs.getInt("numberOfQuestions"));
                q.setQuizTopic(rs.getString("quizTopic"));
                q.setQuizTime(rs.getString("quizTime"));
                q.setQuizID(rs.getInt("quizID"));
                q.setUserID(rs.getInt("userID"));
                q.setLectureId(rs.getInt("lectureId"));
                quizzes.add(q);
            }
            return quizzes;
            }catch (SQLException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }   
    }
    
    
    public Quiz getQuizByQuizID(int quizID) {
        final String selectQuery = "SELECT * FROM quiz WHERE quizID = "+quizID;
        try (Connection conn = JDBCConnection.geConnection();
            Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(selectQuery);
            Quiz q = new Quiz();
            if(rs.next()){
                q.setCourseID(rs.getInt("courseID"));
                q.setNumberOfQuestions(rs.getInt("numberOfQuestions"));
                q.setQuizTopic(rs.getString("quizTopic"));
                q.setQuizTime(rs.getString("quizTime"));
                q.setQuizID(rs.getInt("quizID"));
                q.setUserID(rs.getInt("userID"));
                q.setLectureId(rs.getInt("lectureId"));
            }
            return q;
            }catch (SQLException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }   
    }
    
    /**
     * This getQuizForLecture method will get all the quiz for particular lecture
     * @param courseId ID of the course
     * @param lectureId ID of the lecture
     * @return list of all quiz for the particular lecture
     */
    public List<Quiz> getQuizForLecture(int courseId, int lectureId) {
        final String selectQuery = "SELECT * FROM quiz WHERE courseID = "+courseId+" AND lectureId = "+lectureId;
        try (Connection conn = JDBCConnection.geConnection();
            Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(selectQuery);
            List<Quiz> quizzes= new ArrayList<>();
            while(rs.next()){
                Quiz q = new Quiz();
                q.setCourseID(rs.getInt("courseID"));
                q.setNumberOfQuestions(rs.getInt("numberOfQuestions"));
                q.setQuizTopic(rs.getString("quizTopic"));
                q.setQuizTime(rs.getString("quizTime"));
                q.setQuizID(rs.getInt("quizID"));
                q.setUserID(rs.getInt("userID"));
                q.setLectureId(rs.getInt("lectureId"));
                quizzes.add(q);
            }
            return quizzes;
            }catch (SQLException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }   
    }
    
    /**
     * This getAllQuestionsForQuiz method will get all the questions for particular quiz
     * @param quizID ID of the quiz
     * @return list of all questions for a particular quiz
     */
    public List<Question> getAllQuestionsForQuiz(int quizID) {
        final String selectQuery = "SELECT * FROM question WHERE questionID IN (SELECT questionID from quizQuestion WHERE quizID = "+quizID+")";
        try (Connection conn = JDBCConnection.geConnection();
            Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(selectQuery);
            List<Question> questions = new ArrayList<>();
            while(rs.next()){
                Question q = new Question();
                q.setProblem(rs.getString("problem"));
                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));
                q.setCorrectOption(rs.getInt("correctAnswer"));
                q.setQuestionID(rs.getInt("questionID"));
                questions.add(q);
            }
            return questions;
            }catch (SQLException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }   
    }
    
    /**
     * This hasLearnerAttemptedQuiz method will return if learner has attempted the quiz
     * @param g grades for the quiz
     * @return true if quiz has been attempted by learner
     */
    public boolean hasLearnerAttemptedQuiz(Grade g){
        final String selectQuery = "SELECT * FROM grade WHERE quizID = "+g.getQuizID()+" AND userID = "+g.getUserID()+" AND lectureID ="+g.getLectureID();
        try (Connection conn = JDBCConnection.geConnection();
            Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(selectQuery);
            if(rs.next()){
                return true;
            }
            return false;
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } 
    }
    
    /**
     * This submitGradeForStudent method will submit grades for students
     * @param g grades for the student
     * @return the updated grades 
     */
    public int submitGradeForStudent(Grade g) {
        String query;
        if(!hasLearnerAttemptedQuiz(g)){
            query= "INSERT INTO grade(userID, quizID, courseID, lectureID, totalPoints, pointSecured) VALUES(?,?,?,?,?,?)";
        }else{
            query = "UPDATE grade SET userID = ?, quizID = ?, courseID = ?, lectureID = ?, totalPoints = ?, pointSecured = ? WHERE userID = "+g.getUserID()+" AND quizID = "+g.getQuizID();
        }
        try (Connection conn = JDBCConnection.geConnection();
                    PreparedStatement preparedStatement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, g.getUserID());
                preparedStatement.setInt(2, g.getQuizID());
                preparedStatement.setInt(3, g.getCourseID());
                preparedStatement.setInt(4, g.getQuizID());
                preparedStatement.setInt(5, g.getTotalPoints());
                preparedStatement.setInt(6, g.getPointSecured());
                int row = preparedStatement.executeUpdate();
                if(row>0){
                    System.out.println("GRADED!!" +row);
                    return row;
                    // ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    // if (generatedKeys.next()) {
                    //     return Integer.parseInt((generatedKeys.getLong(1)+""));
                    // }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1;
    }
    
    /**
     * This getGradesForStudent method will get grades for students
     * @param userId ID of the user
     * @return list of all grades for a user
     */
    public List<Grade> getGradesForStudent(int userId) {
        final String selectQuery = "SELECT * FROM grade WHERE userId = "+userId;
        try (Connection conn = JDBCConnection.geConnection();
            Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(selectQuery);
            List<Grade> grades = new ArrayList<>();
            while(rs.next()){
                Grade g = new Grade();
                g.setCourseID(rs.getInt("courseID"));
                g.setGradeID(rs.getInt("gradeID"));
                g.setLectureID(rs.getInt("lectureID"));
                g.setPointSecured(rs.getInt("pointSecured"));
                g.setQuizID(rs.getInt("quizID"));
                g.setTotalPoints(rs.getInt("totalPoints"));
                g.setPointSecured(rs.getInt("pointSecured"));
                grades.add(g);
            }
            return grades;
            }catch (SQLException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }  
    }
}
