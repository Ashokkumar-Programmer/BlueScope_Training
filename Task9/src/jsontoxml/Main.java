package jsontoxml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Main {

    public static void main(String[] args) throws IOException, TemplateException {
        FileReader file = new FileReader("src/StudentData.json");
        StringBuilder jsonfile = new StringBuilder();
        int i;
        while ((i = file.read()) != -1) {
            jsonfile.append((char) i);
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonfile.toString());

        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File("A:/Github/BlueScope_Training/Task9/src"));

        Template template = config.getTemplate("student.ftl");
        
        int index = 0;
        
        Map<Integer, JsonNode> map = new HashMap<Integer, JsonNode>();
        Map<String, Map<Integer, JsonNode>> root = new HashMap<String, Map<Integer,JsonNode>>();
        
        while(node.get("students").get(index)!=null) {
        	map.put(index, node.get("students").get(index));
        	index++;
        }
        
        root.put("students", map);

        StringWriter writer = new StringWriter();
        
        template.process(root, writer);
        
        System.out.println(writer.toString());
        
        File writefile = new File("src/student.xml");
        FileOutputStream xmlfile = new FileOutputStream(writefile);
        xmlfile.write(writer.toString().getBytes());
    }
}
