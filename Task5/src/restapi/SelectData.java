package restapi;

import java.sql.*;

public class SelectData {
	
	//static final String query = "select * from student where rollno=?";
	static final String query = "select * from student";
	ResultSet rs = null;
	
	ResultSet selectdata() {
		try {
			Connection con = new CreateConnection().connect();
			PreparedStatement pt = con.prepareStatement(query);
			//pt.setLong(1, rollno);
			rs = pt.executeQuery();
			return rs;
		}
		catch(Exception e) {
			return rs;
		}
	}
}
