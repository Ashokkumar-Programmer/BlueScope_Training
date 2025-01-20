package backend.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.dao.StudentDAO;
import backend.model.Student;

@WebServlet("/")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        
        switch (action) {
            case "/new":
                newPage(request, response);
                break;
            case "/insert":
                insertStudent(request, response);
                break;
            case "/delete":
                deleteStudent(request, response);
                break;
            case "/edit":
                editPage(request, response);
                break;
            case "/update":
                updateStudent(request, response);
                break;
            case "/list":
                selectStudent(request, response);
                break;
        }
    }

    void newPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("insert-update.jsp");
        dispatcher.forward(request, response);
    }

    void insertStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long rollno = Long.valueOf(request.getParameter("rollno"));
        String studentname = request.getParameter("studentname");
        String dept = request.getParameter("dept");
        long phonenumber = Long.valueOf(request.getParameter("phonenumber"));

        if (studentDAO.isDuplicate(rollno)) {
            request.setAttribute("error", "Rollnumber already exists");
            RequestDispatcher dispatcher = request.getRequestDispatcher("insert-update.jsp");
            dispatcher.forward(request, response);
        } else {
            Student student = new Student(rollno, studentname, dept, phonenumber);
            studentDAO.insertData(student);
            response.sendRedirect("list");
        }
    }

    void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long rollno = Long.valueOf(request.getParameter("rollno"));
        studentDAO.deletedata(rollno);
        response.sendRedirect("list");
    }

    void selectStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDAO.selectall();
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    void editPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long rollno = Long.valueOf(request.getParameter("rollno"));
        Student student = studentDAO.selectData(rollno);
        request.setAttribute("students", student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("insert-update.jsp");
        dispatcher.forward(request, response);
    }

    void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long rollno = Long.valueOf(request.getParameter("rollno"));
        String studentname = request.getParameter("studentname");
        String dept = request.getParameter("dept");
        long phonenumber = Long.valueOf(request.getParameter("phonenumber"));
        Student student = new Student(rollno, studentname, dept, phonenumber);
        studentDAO.upateData(student);
        response.sendRedirect("list");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
