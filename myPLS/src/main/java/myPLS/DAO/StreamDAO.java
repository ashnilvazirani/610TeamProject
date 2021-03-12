package myPLS.DAO;

import java.util.List;

import myPLS.beans.Stream;

public interface StreamDAO {
	List<Stream> getStreams();
	Stream getStream(int id);
	boolean updateStream(Stream stream);
	boolean addStream(Stream stream);
	boolean deleteStream(int id);

}
