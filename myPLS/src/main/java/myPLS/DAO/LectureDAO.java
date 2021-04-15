package myPLS.DAO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myPLS.beans.Lecture;
import myPLS.beans.PDFLecture;

public class LectureDAO {
	
	public boolean addLecture(Lecture lecture) {
		final String INSERT_LECTURE = "INSERT INTO LECTURE (courseId, professorId, lectureName,lectureDescription) VALUES (?,?,?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_LECTURE)) {
			preparedStatement.setInt(1, lecture.getCourseId());
			preparedStatement.setInt(2, lecture.getProfessorId());
			preparedStatement.setString(3, lecture.getLectureName());
			preparedStatement.setString(4, lecture.getLectureDescription());
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public boolean uploadPDF(PDFLecture pdf) {
		final String PDF = "INSERT INTO pdfLecture (lectureId,content,fileName) VALUES (?,?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(PDF)) {
			preparedStatement.setInt(1, pdf.getLectureId());
			preparedStatement.setBlob(2, pdf.getContent());
			preparedStatement.setString(3, pdf.getFileName());
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Lecture> getLecture(int lectureId) {
		final String GET_LECTURE = "select * from LECTURE where lectureId=?";
		List<Lecture> lectures = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_LECTURE)) {
			preparedStatement.setInt(1, lectureId);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Lecture lecture = new Lecture();
				lecture.setLectureId(result.getInt("lectureId"));
				lecture.setCourseId(result.getInt("courseId"));
				lecture.setLectureName(result.getString("lectureName"));
				lecture.setProfessorId(result.getInt("professorId"));
				lecture.setLectureDescription(result.getString("lectureDescription"));
				lectures.add(lecture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lectures;
	}
	
	public List<Lecture> getLectures() {
		final String GET_LECTURES = "select * from LECTURE";
		List<Lecture> lectures = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_LECTURES)) {
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Lecture lecture = new Lecture();
				lecture.setLectureId(result.getInt("lectureId"));
				lecture.setCourseId(result.getInt("courseId"));
				lecture.setLectureName(result.getString("lectureName"));
				lecture.setProfessorId(result.getInt("professorId"));
				lecture.setLectureDescription(result.getString("lectureDescription"));
				lectures.add(lecture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lectures;
	}
	
	public OutputStream getPdf(int lectureId,String lectureName) {
		final String GET_LECTURE = "select * from pdfLecture where lectureId=? and fileName=?";
		OutputStream fos = null;
		List<PDFLecture> lectures = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_LECTURE)) {
			preparedStatement.setInt(1, lectureId);
			preparedStatement.setString(2, lectureName);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				PDFLecture lecture = new PDFLecture();
				lecture.setLectureId(result.getInt("lectureId"));
				InputStream in = result.getBlob("content").getBinaryStream();
	            try {
	            	fos = new FileOutputStream(result.getString("fileName"));
	            	int b = 0;
		            while ((b = in.read()) != -1)
		            {
		                fos.write(b); 
		            }
	            } catch(IOException e) {
	            	e.printStackTrace();
	            }
				lecture.setPdfId(result.getInt("pdfID"));
				lectures.add(lecture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fos;
	}
	
	public List<PDFLecture> getPdfNames(int lectureId) {
		final String GET_LECTURE = "select * from pdfLecture where lectureId=?";
		List<PDFLecture> lectures = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_LECTURE)) {
			preparedStatement.setInt(1, lectureId);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				PDFLecture lecture = new PDFLecture();
				lecture.setLectureId(result.getInt("lectureId"));
				lecture.setPdfId(result.getInt("pdfID"));
				lecture.setFileName(result.getString("fileName"));
				lectures.add(lecture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lectures;
	}


}
