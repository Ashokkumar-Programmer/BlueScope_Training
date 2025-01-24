package backend.DB;


import java.sql.*;

public class UpdateData {

	static final String query = "update student set studentname=?, dept=?, phonenumber=? where rollno=?";
	static final String selectquery = "select * from student where rollno = ?";
	
	
	public String updatedata(long rollno, String studentname, String dept, long phonenumber ) {
		try {
			Connection con = new CreateConnection().connect();
			PreparedStatement pt = con.prepareStatement(query);
			PreparedStatement pt1 = con.prepareStatement(selectquery);
			pt1.setLong(1, rollno);
			ResultSet rs = pt1.executeQuery();
			if(rs.next()) {
				if(studentname==null) {
					studentname = rs.getString("studentname");
				}
				if(dept==null) {
					dept = rs.getString("dept");
				}
				if(phonenumber==0) {
					phonenumber = rs.getLong("phonenumber");
				}
				
				rs.close();
				pt1.close();
				
				pt.setString(1, studentname);
				pt.setString(2, dept);
				pt.setLong(3, phonenumber);
				pt.setLong(4, rollno);
				pt.executeUpdate();
				pt.close();
				return "Data Updated Successfully";
			}
			else {
				return "No data available for rollno "+rollno;
			}
		}
		catch(Exception e) {
			return "Error: "+e.toString();
		}
	}
}
