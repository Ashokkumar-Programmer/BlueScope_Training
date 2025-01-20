<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
</head>
<body>
    <div class="container col-md-6 mt-5">
        <div class="card">
            <div class="card-body">
                <h2 class="text-center">
                    <c:if test="${students != null}">Edit Student</c:if>
                    <c:if test="${students == null}">Add New Student</c:if>
                </h2>
                 <c:if test="${error != null}">
                	<div class="alert alert-danger">${error}</div>
            	</c:if>
                <form action="${students != null ? 'update' : 'insert'}" method="post">
				    <c:if test="${students != null}">
				        <input type="hidden" name="rollno" value="${students.rollno}" />
				    </c:if>
				    <c:if test="${students == null}">
				        <div class="form-group">
				            <label for="rollno">Student Rollno</label>
				            <input type="number" class="form-control" id="rollno" name="rollno" required /><br>
				        </div>
				    </c:if>
				    <div class="form-group">
				        <label for="studentname">Student Name</label>
				        <input type="text" class="form-control" id="studentname" name="studentname" value="${students != null ? students.studentname : ''}" required />
				    </div>
				    <div class="form-group">
				        <label for="dept">Department</label>
				        <input type="text" class="form-control" id="dept" name="dept" value="${students != null ? students.dept : ''}" required />
				    </div>
				    <div class="form-group">
				        <label for="phonenumber">Phone Number</label>
				        <input type="text" class="form-control" id="phonenumber" name="phonenumber" value="${students != null ? students.phonenumber : ''}" required />
				    </div>
				    <button type="submit" class="btn btn-success">Save</button>
				</form>

            </div>
        </div>
    </div>
</body>
</html>