package myPLS.services;

import java.util.HashMap;
import java.util.Map;

import myPLS.DAO.StreamDAO;
import myPLS.beans.Stream;
import spark.Request;

public class StreamService {
	private static StreamDAO streamDao;

    public StreamService() {
		streamDao = new StreamDAO();
	}
    public Map<Integer, Stream> getUserDetails(){
        return streamDao.getAllStreams();
    }
	public boolean addStreamDetails(Request request) {
		//Connect with UI
        return true;
	}
}
