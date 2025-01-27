package xmlreader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class XMLParser {
	
	public static String print(JSONObject  student) {
		return "Roll no: "+student.getInt("rollno")+"\nStudent name: "+student.getString("studentname")+"\nDepartment: "+student.getString("dept")+"\nPhonenumber: "+student.getBigInteger("phonenumber")+"\n\n";
	}
	
    public String parseXml(int rollno, String filename) throws Exception {
        String xml = new XMLReader().read_xml(filename);
        String result = "";
        JSONArray jsonObject = XML.toJSONObject(xml).getJSONObject("students").getJSONArray("student");
        for (Object student : jsonObject) {
            result += print((JSONObject)student);
//            if (student.getInt("rollno") == rollno) {
//                result += print((JSONObject)student);
//            }
        }
        return result;
    }
}
