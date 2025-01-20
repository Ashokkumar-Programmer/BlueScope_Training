<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Students Data</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
</head>
<body>
	<!--<p><%

		    String serverXmlPath = System.getProperty("catalina.base") + "\\conf"; 
		
		    out.println(serverXmlPath); 
		
		%></p>-->
    <div class="container"><br><br>
        <h3 class="text-center">List of Students</h3>
        <hr>
        <a href="<%=request.getContextPath()%>/new" class="btn btn-success mb-3">Add New Student</a>
        <a href="<%=request.getContextPath()%>/list" class="btn btn-secondary mb-3">Student List</a>
        <table class="table table-bordered" style="text-align:center;">
            <thead>
                <tr>
                    <th>Roll No</th>
                    <th>Name</th>
                    <th>Department</th>
                    <th>Phone Number</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td><c:out value="${student.rollno}" /></td>
                        <td><c:out value="${student.studentname}" /></td>
                        <td><c:out value="${student.dept}" /></td>
                        <td><c:out value="${student.phonenumber}" /></td>
                        <td>
                            <a href="edit?rollno=${student.rollno}" class="btn btn-primary btn-sm">Edit</a>
                            <a href="delete?rollno=${student.rollno}" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>