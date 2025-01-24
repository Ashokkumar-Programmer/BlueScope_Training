package backend.DB;

import java.sql.*;

public class SelectData {
    static final String rollnoquery = "select * from student where rollno=?";
    static final String query = "select * from student";
    ResultSet rs = null;

    public String selectdata() {
        try {
            Connection con = new CreateConnection().connect();
            PreparedStatement pt = con.prepareStatement(query);
            rs = pt.executeQuery();
            String result = "<!DOCTYPE html>\r\n"
                    + "<html>\r\n"
                    + "<head>\r\n"
                    + "    <title>Students Data</title>\r\n"
                    + "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" crossorigin=\"anonymous\">\r\n"
                    + "</head>\r\n"
                    + "<body>\r\n"
                    + "    <div class=\"container\"><br><br>\r\n"
                    + "        <h3 class=\"text-center\">List of Students</h3>\r\n"
                    + "        <hr>\r\n"
                    + "		<form method=\"post\" action=\"web\" id=\"new_form\">\r\n"
                    + "		    <input type=\"hidden\" name=\"action\" value=\"new\">\r\n"
                    + "		    <a class=\"btn btn-success mb-3\" href=\"AddStudent.html\">Add New Student</a>\r\n"
                    + "		</form>\r\n"
                    + "<table class=\"table table-bordered\" style=\"text-align:center;\">\r\n"
                    + "            <thead>\r\n"
                    + "                <tr>\r\n"
                    + "                    <th>Roll No</th>\r\n"
                    + "                    <th>Name</th>\r\n"
                    + "                    <th>Department</th>\r\n"
                    + "                    <th>Phone Number</th>\r\n"
                    + "                    <th>Actions</th>\r\n"
                    + "                </tr>\r\n"
                    + "            </thead>"
                    + "<tbody>";
            while (rs.next()) {
                long rollno = rs.getLong("rollno");
                result += "<tr>\r\n"
                        + "   <td>" + rollno + "</td>\r\n"
                        + "   <td>" + rs.getString("studentname") + "</td>\r\n"
                        + "   <td>" + rs.getString("dept") + "</td>\r\n"
                        + "   <td>" + rs.getString("phonenumber") + "</td>\r\n"
                        + "   <td>\r\n"
                        + "       <form method=\"post\" action=\"web\">\r\n"
                        + "           <input type=\"hidden\" name=\"action\" value=\"edit\">\r\n"
                        + "           <input type=\"hidden\" name=\"rollno\" value=\"" + rollno + "\">\r\n"
                        + "           <button type=\"submit\" class=\"btn btn-primary btn-sm\">Edit</button>\r\n"
                        + "       </form>\r\n"
                        + "       <form method=\"post\" action=\"web\">\r\n"
                        + "           <input type=\"hidden\" name=\"action\" value=\"delete\">\r\n"
                        + "           <input type=\"hidden\" name=\"rollno\" value=\"" + rollno + "\">\r\n"
                        + "           <button type=\"submit\" class=\"btn btn-danger btn-sm\">Delete</button>\r\n"
                        + "       </form>\r\n"
                        + "   </td>\r\n"
                        + "</tr>";
            }

            result += "</tbody>\r\n"
                    + "		</table>\r\n"
                    + "	</div>\r\n"
                    + "</body>\r\n"
                    + "</html>";

            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String selectByrollno(long rollno) {
        String result = null;
        try {
            Connection con = new CreateConnection().connect();
            PreparedStatement pt = con.prepareStatement(rollnoquery);
            pt.setLong(1, rollno);
            rs = pt.executeQuery();
            if (rs.next()) {
                result = "<!DOCTYPE html>\r\n"
                        + "<html>\r\n"
                        + "<head>\r\n"
                        + "    <title>Student Form</title>\r\n"
                        + "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" crossorigin=\"anonymous\">\r\n"
                        + "</head>\r\n"
                        + "<body>\r\n"
                        + "    <div class=\"container col-md-6 mt-5\">\r\n"
                        + "        <div class=\"card\">\r\n"
                        + "            <div class=\"card-body\">\r\n"
                        + "                <h2 class=\"text-center\">Edit Student</h2>\r\n"
                        + "                <form method=\"post\" action=\"web\">\r\n"
                        + "                  <input type=\"hidden\" value=\"update\" name=\"action\">\r\n"
                        + "                  <input type=\"hidden\" name=\"rollno\" value=\"" + rs.getLong("rollno") + "\">\r\n"
                        + "                    <div class=\"form-group\">\r\n"
                        + "                        <label for=\"studentname\">Student Name</label>\r\n"
                        + "                        <input type=\"text\" class=\"form-control\" id=\"studentname\" name=\"studentname\" value=\"" + rs.getString("studentname") + "\" required />\r\n"
                        + "                    </div>\r\n"
                        + "                    <div class=\"form-group\">\r\n"
                        + "                        <label for=\"dept\">Department</label>\r\n"
                        + "                        <input type=\"text\" class=\"form-control\" id=\"dept\" name=\"dept\" value=\"" + rs.getString("dept") + "\" required />\r\n"
                        + "                    </div>\r\n"
                        + "                    <div class=\"form-group\">\r\n"
                        + "                        <label for=\"phonenumber\">Phone Number</label>\r\n"
                        + "                        <input type=\"text\" class=\"form-control\" id=\"phonenumber\" name=\"phonenumber\" value=\"" + rs.getLong("phonenumber") + "\" required />\r\n"
                        + "                    </div>\r\n"
                        + "                    <button type=\"submit\" class=\"btn btn-success\">Save</button>\r\n"
                        + "                </form>\r\n"
                        + "            </div>\r\n"
                        + "        </div>\r\n"
                        + "    </div>\r\n"
                        + "</body>\r\n"
                        + "</html>";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
