package myPLS.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	private static Connection con;

	public static Connection geConnection() {
		String jdbcURL = "jdbc:mysql://localhost:3306/myPLS?rewriteBatchedStatements=true&useSSL=false&allowPublicKeyRetrieval=true";
		String jdbcUser = "root";
		String jdbcPwd = "sk2677@@";
		try {
			con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
