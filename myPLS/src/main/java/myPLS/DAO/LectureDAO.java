package myPLS.DAO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
	
	public List<Lecture> getLectures(int courseId) {
		final String GET_LECTURES = "select * from LECTURE where courseId=?";
		List<Lecture> lectures = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_LECTURES)) {
			preparedStatement.setInt(1, courseId);
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
	
	public List<Lecture> getScheduledLectures(int courseId) {
		final String GET_LECTURES = "select * from LECTURE where courseId=? and sharingDate<=sysdate()";
		List<Lecture> lectures = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_LECTURES)) {
			preparedStatement.setInt(1, courseId);
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

	public boolean updateLecture(Lecture lecture) {
		final String UPDATE_LECTURE = "UPDATE LECTURE set lectureName = ?, lectureDescription = ? where lectureId = ?";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_LECTURE)) {
			preparedStatement.setString(1, lecture.getLectureName());
			preparedStatement.setString(2, lecture.getLectureDescription());
			preparedStatement.setInt(3, lecture.getLectureId());
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public boolean deleteLecture(int lectureId) {
		this.deletePdfLecture(lectureId);
		final String DELETE_LECTURE = "delete from  LECTURE where lectureId = ?";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_LECTURE)) {
			preparedStatement.setInt(1, lectureId);
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public boolean deletePdfLecture(int lectureId) {
		final String DELETE_LECTURE_Content = "delete from  pdfLecture where lectureId = ?";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_LECTURE_Content)) {
			preparedStatement.setInt(1, lectureId);
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public boolean scheduleLectureSharing(LocalDateTime date, int lectureId) {
		final String UPDATE_LECTURE = "UPDATE LECTURE set sharingDate = ? where lectureId = ?";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_LECTURE)) {
			preparedStatement.setTimestamp(1, Timestamp.valueOf(date.minusHours(4)));
			preparedStatement.setInt(2, lectureId);
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
