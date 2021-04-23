package myPLS.beans;

import java.io.InputStream;

public class PDFLecture {
	private int pdfId;
	private int lectureId;
	private InputStream content;
	private String fileName;
	public int getPdfId() {
		return pdfId;
	}
	public void setPdfId(int pdfId) {
		this.pdfId = pdfId;
	}
	public int getLectureId() {
		return lectureId;
	}
	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}
	public InputStream getContent() {
		return content;
	}
	public void setContent(InputStream content) {
		this.content = content;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
