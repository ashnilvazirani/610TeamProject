package myPLS.services;

public class LectureFactory {
	public LectureService createLecture(String contentType) {
		if(contentType.equalsIgnoreCase("PDF")) {
			return new PDFLectureService();
		}
		return  new PDFLectureService();
	}

}
