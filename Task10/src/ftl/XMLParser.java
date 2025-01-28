package ftl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class XMLParser {

	public Map<String, Map<Integer, Map<String, String>>> xmlparser(String filepath) throws IOException {
		
		String xmlstring = new XMLReader().readxml(filepath);
		
		JSONArray xml = XML.toJSONObject(xmlstring).getJSONObject("students").getJSONArray("student");
		
		Map<Integer, Map<String, String>> data = new HashMap<>();
		
		Map<String, Map<Integer, Map<String, String>>> xmldata = new HashMap<>();
		
		for (int i = 0; i < xml.length(); i++) {
			Map<String, String> temp = new HashMap<>();
		    JSONObject jsonObject = xml.getJSONObject(i);
		    Iterator<String> keys = jsonObject.keys();
		    while (keys.hasNext()) {
		        String key = keys.next();
		        String value = jsonObject.get(key).toString();
		        temp.put(key, value);
		    }
		    data.put(i, temp);
		}
		xmldata.put("students", data);
		
		return xmldata;
	}
}
