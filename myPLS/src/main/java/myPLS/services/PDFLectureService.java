package myPLS.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import myPLS.DAO.LectureDAO;
import myPLS.beans.Lecture;
import myPLS.beans.PDFLecture;
import spark.Request;

public class PDFLectureService implements LectureService {
	private LectureDAO lectureDAO;

	public PDFLectureService() {
		lectureDAO = new LectureDAO();
	}

	@Override
	public Map<String, String> addLecture(Request request) {
		Lecture lecture = new Lecture();
		Map<String, String> map = new HashMap<>();
		lecture.setCourseId(Integer
				.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
		lecture.setProfessorId(request.session().attribute("userID"));
		lecture.setLectureName(
				request.queryParams("lectureName") != null ? request.queryParams("lectureName") : "unknown");
		lecture.setLectureDescription(
				request.queryParams("lectureDesc") != null ? request.queryParams("lectureDesc") : "unknown");
		request.session().attribute("lectureId", map);
		if (lectureDAO.addLecture(lecture)) {
			map.put("message", "Course enrolled successfully");
		}
		return map;

	}

	@Override
	public void upload(Request request) {
		PDFLecture pdf = new PDFLecture();
		// Get the uploaded file
		Part uploadedFile = null;
		try {
			request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
			uploadedFile = request.raw().getPart("file");
			String fileUrl =  uploadedFile.getSubmittedFileName();
			pdf.setFileName(fileUrl);
			InputStream fileInputStream = uploadedFile.getInputStream();
			pdf.setContent(fileInputStream);
			pdf.setLectureId(request.session().attribute("lectureId"));
			lectureDAO.uploadPDF(pdf);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Lecture> getLectures(Request request) {
		return lectureDAO.getLectures();
	}

	@Override
	public List<Lecture> getLecture(Request request) {
		int lectureId = Integer
				.parseInt(request.queryParams("lectureId") != null ? request.queryParams("lectureId") : "unknown");
		return lectureDAO.getLecture(lectureId);
	}

}
