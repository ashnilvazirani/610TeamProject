package myPLS.services;

import myPLS.beans.Feedback;
import spark.Request;

public abstract class FeedbackService {
	abstract public Object getFeedback(Request request);
	public boolean addFeedback(Request request){return false;}

}
