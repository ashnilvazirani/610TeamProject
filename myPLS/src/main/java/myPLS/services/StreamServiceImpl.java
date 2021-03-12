package myPLS.services;

import java.util.List;

import myPLS.DAO.StreamDAO;
import myPLS.DAO.StreamDAOImpl;
import myPLS.beans.Stream;

public class StreamServiceImpl implements StreamService{
	private static StreamDAO streamDao;

    public StreamServiceImpl() {
		streamDao = new StreamDAOImpl();
	}

	@Override
	public List<Stream> getStreams() {
		return streamDao.getStreams();
	}

	@Override
	public Stream getStream(int id) {
		return streamDao.getStream(id);
	}

	@Override
	public boolean updateStream(Stream stream) {
		return streamDao.updateStream(stream);
	}

	@Override
	public void addStream(Stream stream) {
		streamDao.addStream(stream);
	}

	@Override
	public boolean deleteStream(int id) {
		return streamDao.deleteStream(id);
	}
    
}
