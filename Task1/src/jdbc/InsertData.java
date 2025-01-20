package jdbc;

import java.sql.*;

public class InsertData {
	
	static final String query = "insert into student(rollno, studentname, dept, phonenumber) values(?,?,?,?)";
	
	String insertdata(long rollno, String studentname, String dept, long phonenumber ) {
		try {
			Connection con = new CreateConnection().connect();
			PreparedStatement pt = con.prepareStatement(query);
			pt.setLong(1, rollno);
			pt.setString(2, studentname);
			pt.setString(3, dept);
			pt.setLong(4, phonenumber);
			pt.executeUpdate();
			return "Data Inserted Successfully";
		}
		catch(Exception e) {
			return "Error: "+e.toString();
		}
	}
	
}
