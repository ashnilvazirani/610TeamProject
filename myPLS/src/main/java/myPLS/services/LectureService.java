package myPLS.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import myPLS.beans.Lecture;
import spark.Request;

public interface LectureService {
	Map<String, String> addLecture(Request request);
	void upload(Request request);
	List<Lecture> getLectures(Request request);
	List<Lecture> getLecture(Request request);
	Map<String, String> updateLecture(Request request);
	List<Lecture> getScheduledLectures(Request request);
	Map<String, String> scheduleLectureSharing(LocalDateTime date,int lectureId);
}
