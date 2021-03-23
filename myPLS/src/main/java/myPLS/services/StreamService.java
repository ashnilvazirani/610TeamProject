package myPLS.services;

import java.util.List;

import myPLS.beans.Stream;

public interface StreamService {
	List<Stream> getStreams();
	Stream getStream(int id);
	boolean updateStream(Stream stream);
	void addStream(Stream stream);
	boolean deleteStream(int id);
}
