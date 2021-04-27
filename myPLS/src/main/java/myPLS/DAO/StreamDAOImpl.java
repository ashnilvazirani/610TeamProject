package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myPLS.beans.Stream;

/**
 * The StreamDAOImpl class to add, update stream details
 * @author sandeep
 *
 */
public class StreamDAOImpl implements StreamDAO {


	@Override
	/**
	 * This getStreams method will get stream from database
	 *  @return the list of all streams 
	 */
	public List<Stream> getStreams() {
		final String GET_STREAMS = "SELECT * FROM Stream";
		List<Stream> streams = new ArrayList<Stream>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_STREAMS)) {
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Stream stream = new Stream();
				stream.setStreamID(result.getInt("streamId"));
				stream.setStreamName(result.getString("streamName"));
				stream.setStreamDuration(result.getInt("streamDuration"));
				stream.setStreamDescription(result.getString("streamDescription"));
				streams.add(stream);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return streams;
	}

	@Override
	/**
	 * This getStreams method will get stream from database
	 * @param id stream id
	 *  @return the streams 
	 */
	public Stream getStream(int id) {
		final String GET_STREAM = "SELECT * FROM Stream where streamId = ?";
		Stream stream = new Stream();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_STREAM)) {
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				stream.setStreamID(result.getInt("streamId"));
				stream.setStreamName(result.getString("streamName"));
				stream.setStreamDuration(result.getInt("streamDuration"));
				stream.setStreamDescription(result.getString("streamDescription"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
	}

	@Override
	public boolean updateStream(Stream stream) {
		final String UPDATE_STREAM = "UPDATE Stream SET streamName = ?, streamDuration = ? , streamDescription = ? where streamId = ?";
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_STREAM)) {
			preparedStatement.setString(1, stream.getStreamName());
			preparedStatement.setInt(2, stream.getStreamDuration());
			preparedStatement.setString(3, stream.getStreamDescription());
			preparedStatement.setInt(4, stream.getStreamID());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	/**
	 * This addStream method will add stream to database
	 * @param stream name of stream
	 *  @return true if stream is added to DB
	 */
	public boolean addStream(Stream stream) {
		final String INSERT_STREAM = "INSERT INTO Stream (streamName, streamDuration, streamDescription) VALUES (?,?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_STREAM)) {
			preparedStatement.setString(1, stream.getStreamName());
			preparedStatement.setInt(2, stream.getStreamDuration());
			preparedStatement.setString(3, stream.getStreamDescription());
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteStream(int id) {
		final String DELETE_STREAM = "DELETE from Stream where streamId = ?";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_STREAM)) {
			preparedStatement.setInt(1, id);
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
