package xmlreader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainClass {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int rollno = 0;
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		System.out.print("Enter the student rollno: ");
//		rollno = Integer.parseInt(bf.readLine());
		String result = new XMLParser().parseXml(rollno, "A:\\Github\\BlueScope_Training\\Task8\\src\\studentdata.xml");
		System.out.println(result);
	}

}
