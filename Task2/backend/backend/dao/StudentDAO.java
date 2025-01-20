package backend.dao;

import backend.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final static String url = "jdbc:mysql://localhost:3306/BlueScope";
    private final static String username = "root";
    private final static String password = "root";
    
    private final static String insert = "insert into student values(?,?,?,?)";
    private final static String update = "update student set studentname=?, dept=?, phonenumber=? where rollno=?";
    private final static String selectbyrollno = "select * from student where rollno=?";
    private final static String select = "select * from student";
    private final static String deletebyrollno = "delete from student where rollno=?";
    private final static String checkDuplicate = "select count(*) from student where rollno=?";
    
    public StudentDAO() {}
    
    Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    
    public boolean isDuplicate(long rollno) {
        boolean isDuplicate = false;
        try {
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement(checkDuplicate);
            ps.setLong(1, rollno);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                isDuplicate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDuplicate;
    }
    
    public void insertData(Student student) {
        try {
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setLong(1, student.getRollno());
            ps.setString(2, student.getStudentname());
            ps.setString(3, student.getDept());
            ps.setLong(4, student.getPhonenumber());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean upateData(Student student) {
        boolean result = false;
        try {
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement(update);
            ps.setLong(4, student.getRollno());
            ps.setString(1, student.getStudentname());
            ps.setString(2, student.getDept());
            ps.setLong(3, student.getPhonenumber());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public Student selectData(long rollno) {
        Student student = null;
        try {
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement(selectbyrollno);
            ps.setLong(1, rollno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student(rs.getLong("rollno"), rs.getString("studentname"), rs.getString("dept"), rs.getLong("phonenumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
    
    public List<Student> selectall() {
        List<Student> students = new ArrayList<>();
        try {
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(new Student(rs.getLong("rollno"), rs.getString("studentname"), rs.getString("dept"), rs.getLong("phonenumber")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
    
    public boolean deletedata(long rollno) {
        boolean result = false;
        try {
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement(deletebyrollno);
            ps.setLong(1, rollno);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
