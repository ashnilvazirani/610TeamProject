package myPLS.DAO;

import java.util.List;

import myPLS.beans.Stream;

/**
 * The StreamDAO interface 
 * @author sandeep
 *
 */
public interface StreamDAO {
	List<Stream> getStreams();
	Stream getStream(int id);
	boolean updateStream(Stream stream);
	boolean addStream(Stream stream);
	boolean deleteStream(int id);

}
