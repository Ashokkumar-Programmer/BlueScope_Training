package backend.model;

public class Student {
	private long rollno;
	private String studentname;
	private String dept;
	private long phonenumber;
	
	
	public Student() {
	}
	public Student(String studentname, String dept, long phonenumber) {
		super();
		this.studentname = studentname;
		this.dept = dept;
		this.phonenumber = phonenumber;
	}
	public Student(long rollno, String studentname, String dept, long phonenumber) {
		super();
		this.rollno = rollno;
		this.studentname = studentname;
		this.dept = dept;
		this.phonenumber = phonenumber;
	}
	public long getRollno() {
		return rollno;
	}
	public void setRollno(long rollno) {
		this.rollno = rollno;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	
}
