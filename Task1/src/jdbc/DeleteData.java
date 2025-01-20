package jdbc;

import java.sql.*;

public class DeleteData {

	static final String query = "delete from student where rollno=?";
	static final String selectquery = "select * from student where rollno=?";
	static PreparedStatement pt;
	
	String deletedata(long rollno ) {
		try {
			Connection con = new CreateConnection().connect();
			pt = con.prepareStatement(selectquery);
			pt.setLong(1, rollno);
			ResultSet rs = pt.executeQuery();
			if(!rs.next()) {
				return "No data available for "+rollno;
			}
			else {
				pt = con.prepareStatement(query);
				pt.setLong(1, rollno);
				pt.executeUpdate();
				return "Data Deleted Successfully";
			}
		}
		catch(Exception e) {
			return "Error: "+e.toString();
		}
	}
	
}
