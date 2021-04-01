package myPLS.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	private static Connection con;

	public static Connection geConnection() {
		String jdbcURL = "jdbc:mysql://localhost:3306/myPLS?allowPublicKeyRetrieval=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String jdbcUser = "root";
		String jdbcPwd = "Ashnil@1998";
		try {
			con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
