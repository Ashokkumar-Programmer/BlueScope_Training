package backend.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.DB.DeleteData;
import backend.DB.InsertData;
import backend.DB.SelectData;
import backend.DB.UpdateData;

public class WebActivity extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public WebActivity() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            response.setContentType("text/html");
            String result = new SelectData().selectdata();
            PrintWriter out = response.getWriter();
            out.println(result);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("new")) {
            response.sendRedirect("AddStudent.html");
        }
        else if (action.equals("insert")) {
            long rollno = Long.valueOf(request.getParameter("rollno"));
            String studentname = request.getParameter("studentname");
            String dept = request.getParameter("dept");
            long phonenumber = Long.valueOf(request.getParameter("phonenumber"));
            String result = new InsertData().insertdata(rollno, studentname, dept, phonenumber);
            if(result.equals("error")) {
            	PrintWriter pw = response.getWriter();
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
            			+ "                <h2 class=\"text-center\">\r\n"
            			+ "                </h2>\r\n"
            			+ "                <form method=\"post\" action=\"web\">\r\n"
            			+ "				 	<input type=\"hidden\" value=\"insert\" name=\"action\">\r\n"
            			+ "			        <div class=\"form-group\">\r\n"
            			+ "			            <label for=\"rollno\">Student Rollno</label>\r\n"
            			+ "			            <input type=\"number\" class=\"form-control\" id=\"rollno\" name=\"rollno\" required /><br>\r\n"
            			+ "			           	<label style=\"color: red; font-size: 17px;\">Rollno already exist</label>\r\n"
            			+ "			        </div>\r\n"
            			+ "				    <div class=\"form-group\">\r\n"
            			+ "				        <label for=\"studentname\">Student Name</label>\r\n"
            			+ "				        <input type=\"text\" class=\"form-control\" id=\"studentname\" name=\"studentname\" value=\"\" required />\r\n"
            			+ "				    </div>\r\n"
            			+ "				    <div class=\"form-group\">\r\n"
            			+ "				        <label for=\"dept\">Department</label>\r\n"
            			+ "				        <input type=\"text\" class=\"form-control\" id=\"dept\" name=\"dept\" value=\"\" required />\r\n"
            			+ "				    </div>\r\n"
            			+ "				    <div class=\"form-group\">\r\n"
            			+ "				        <label for=\"phonenumber\">Phone Number</label>\r\n"
            			+ "				        <input type=\"text\" class=\"form-control\" id=\"phonenumber\" name=\"phonenumber\" value=\"\" required />\r\n"
            			+ "				    </div>\r\n"
            			+ "				    <button type=\"submit\" class=\"btn btn-success\">Save</button>\r\n"
            			+ "				</form>\r\n"
            			+ "            </div>\r\n"
            			+ "        </div>\r\n"
            			+ "    </div>\r\n"
            			+ "</body>\r\n"
            			+ "</html>";
            	pw.println(result);
            }
            else response.sendRedirect("/Task7/web?action=view");
        }
        else if (action.equals("edit")) {
            response.setContentType("text/html");
            long rollno = Long.valueOf(request.getParameter("rollno"));
            String result = new SelectData().selectByrollno(rollno);
            PrintWriter out = response.getWriter();
            out.println(result);
        }
        else if (action.equals("delete")) {
            long rollno = Long.valueOf(request.getParameter("rollno"));
            String result = new DeleteData().deletedata(rollno);
            response.sendRedirect("/Task7/web?action=view");
        }
        else if(action.equals("update")) {
        	long rollno = Long.valueOf(request.getParameter("rollno"));
            String studentname = request.getParameter("studentname");
            String dept = request.getParameter("dept");
            long phonenumber = Long.valueOf(request.getParameter("phonenumber"));
            String result = new UpdateData().updatedata(rollno, studentname, dept, phonenumber);
            response.sendRedirect("/Task7/web?action=view");
        }
    }
}
