package restapi;

import java.sql.*;

public class CreateConnection {
	
	static final String url = "jdbc:mysql://localhost:3306/BlueScope";
	static final String username = "root";
	static final String password = "root";
	
	Connection connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
}
