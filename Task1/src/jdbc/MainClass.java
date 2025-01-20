package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class MainClass {

	public static void main(String[] args) {
		try {
			int choice;
			String studentname, dept;
			long rollno, phonenumber;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				System.out.println("1. Insert\n2. Update\n3. Delete\n4. View\n5. Exit\n");
				choice = Integer.parseInt(br.readLine());
				if(choice==1) {
					System.out.print("Enter rollno of student: ");
					rollno = Long.valueOf(br.readLine());
					System.out.print("\nEnter name of student: ");
					studentname = br.readLine();
					System.out.print("\nEnter department of student: ");
					dept = br.readLine();
					System.out.print("\nEnter phonenumber of student: ");
					phonenumber = Long.valueOf(br.readLine());
					System.out.println(new InsertData().insertdata(rollno, studentname, dept, phonenumber));
				}
				else if(choice==2) {
					System.out.print("Enter rollno of student: ");
					rollno = Long.valueOf(br.readLine());
					System.out.print("\nEnter name of student: ");
					studentname = br.readLine();
					System.out.print("\nEnter department of student: ");
					dept = br.readLine();
					System.out.print("\nEnter phonenumber of student: ");
					try {
						phonenumber = Long.valueOf(br.readLine());
					}
					catch(NumberFormatException e) {
						phonenumber = 0;
					}
					System.out.println(new UpdateData().updatedata(rollno, studentname.equals("")?null:studentname, dept.equals("")?null:dept, phonenumber));
				}
				else if(choice==3) {
					System.out.print("Enter rollno of student: ");
					rollno = Long.valueOf(br.readLine());
					System.out.println(new DeleteData().deletedata(rollno));
				}
				else if(choice==4) {
//					System.out.print("Enter rollno of student: ");
//					rollno = Long.valueOf(br.readLine());
					ResultSet rs = new SelectData().selectdata();
					int count = 1;
					if(rs.isBeforeFirst()) {
						while(rs.next()) {
							System.out.print("Details of Student "+count);
							System.out.print("\nRollno of student: "+rs.getLong("rollno"));
							System.out.print("\nName of student: "+rs.getString("studentname"));
							System.out.print("\nDepartment of student: "+rs.getString("dept"));
							System.out.println("\nPhonenumber of student: "+rs.getLong("phonenumber"));
							System.out.println();
							count++;
						}
					}
					else {
						System.out.println("No data available");
					}
				}
				else if(choice==5) {
					break;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
