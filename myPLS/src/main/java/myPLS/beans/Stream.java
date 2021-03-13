package myPLS.beans;

public class Stream {
    private int streamID;
    private String streamName;
    private int streamDuration;
    private String streamDescription;
   
    public String getStreamDescription() {
		return this.streamDescription;
	}

	public int getStreamID() {
		return this.streamID;
	}

	public void setStreamID(int streamID) {
		this.streamID = streamID;
	}

	public String getStreamName() {
		return this.streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public int getStreamDuration() {
		return this.streamDuration;
	}
	
	public void setStreamDescription(String streamDescription) {
		this.streamDescription = streamDescription;
	}

	public void setStreamDuration(int streamDuration) {
		this.streamDuration = streamDuration;
	}


}
/**
 * CREATE TABLE stream (
    streamID int NOT NULL AUTO_INCREMENT,
    streamName varchar(255) NOT NULL,
    streamDuration varchar(255),
    streamDescription varchar(255),
    PRIMARY KEY (streamID)
);

* ALTER TABLE stream
  MODIFY COLUMN streamDuration INT(11);
 */
