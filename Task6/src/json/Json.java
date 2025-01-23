package json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class Format {
    String name, department, age;
    Map<String, String> details = new HashMap<>();
    Map<String, String> address = new HashMap<>();

    Format(JsonNode node, String key, String index) {
        JsonNode studentNode = node.get(key).get(index);
 
        name = studentNode.get("name").asText();
        age = studentNode.get("age").asText();
        department = studentNode.get("department").asText();

        details.put("gender", studentNode.get("details").get("gender").asText());
        details.put("hobbies", studentNode.get("details").get("hobbies").toString().replace("[", "").replace("]", "").replace("\"", "").replace(",", ", "));
        details.put("math", studentNode.get("details").get("grades").get("math").asText());
        details.put("science", studentNode.get("details").get("grades").get("science").asText());
        details.put("english", studentNode.get("details").get("grades").get("english").asText());

        address.put("street", studentNode.get("details").get("address").get("street").asText());
        address.put("city", studentNode.get("details").get("address").get("city").asText());
        address.put("state", studentNode.get("details").get("address").get("state").asText());
        address.put("zip", studentNode.get("details").get("address").get("zip").asText());
    }
    
    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Department: " + department + "\n" +
                details.entrySet().stream().map(entry->entry.getKey()+": "+entry.getValue()).collect(Collectors.joining("\n")) + "\n"+
                address.entrySet().stream().map(entry->entry.getKey()+": "+entry.getValue()).collect(Collectors.joining("\n")) + "\n";
    }
}

public class Json {

    public static void main(String[] args) throws IOException {
        try (FileReader file = new FileReader("A:\\Github\\BlueScope_Training\\Task6\\StudentData.json")) {
            StringBuilder json = new StringBuilder();
            int i;
            while ((i = file.read()) != -1) {
                json.append((char) i);
            }
            
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json.toString());
            Iterator<String> keys = rootNode.fieldNames();
            
            Map<String, Format> main = new HashMap<String, Format>();
            
            while (keys.hasNext()) {
                String key = keys.next();
                JsonNode students = rootNode.get(key);
                
                Iterator<String> studentIds = students.fieldNames();
                while (studentIds.hasNext()) {
                    String studentId = studentIds.next();
                    Format format = new Format(rootNode, key, studentId);
//                    System.out.println("Student ID: " + studentId);
//                    System.out.println("Student name: "+ format.name);
                    main.put(studentId, format);
                }
            }
            
            while(true) {
            	System.out.println();
	            System.out.print("1. View All Data \n2. View Particular Data \n3.Exit \nEnter your choice: ");
	            int choice = Integer.parseInt(bf.readLine());
	            if(choice==1) {
	            	System.out.println(main.entrySet().stream().map(entry-> entry.getValue().toString()).collect(Collectors.joining("\n")));
	            	System.out.println();
	            }
	            else if(choice==2) {
	            	System.out.print("Enter Student ID: ");
	            	String id = bf.readLine();
	            	System.out.print("\nEnter wanted name to know: ");
	            	String key = bf.readLine();
	            	if(key.equals("")) {
	            		System.out.println();
		            	System.out.println(main.get(id));
	            	}
	            	else {
	            		System.out.println();
		            	if(main.get(id).address.get(key)!=null) {
		            		System.out.println(main.get(id).address.get(key));
		            	}
		            	else if(main.get(id).details.get(key)!=null) {
		            		System.out.println(main.get(id).details.get(key));
		            	}
		            	else if(main.get(id).name!=null) {
		            		System.out.println(main.get(id).name);
		            	}
		            	else if(main.get(id).department!=null) {
		            		System.out.println(main.get(id).department);
		            	}
		            	else if(main.get(id).age!=null) {
		            		System.out.println(main.get(id).age);
		            	}
	            	}
	            }
	            else {
	            	break;
	            }
            }
        }
    }
}
