package mbuchatskyi.database.connection;

import java.sql.*;

public class DBManager {
	private static final String URL = "jdbc:mysql://localhost:3306/equations";
	private static final String ACCESS = "?user=root&password=root";
	
	private static DBManager instance;

	private DBManager() {
	}

	public static DBManager getInstance() {
		
		if (instance == null) {
			instance = new DBManager();
		}

		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL + ACCESS);
	}
}
