package ftl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FTL {

	static String ftl(String filepath) throws IOException, TemplateException {
		Configuration config = new Configuration(Configuration.VERSION_2_3_0);
		config.setDirectoryForTemplateLoading(new File("A:/Github/BlueScope_Training/Task10/src"));
		
		Template template = config.getTemplate("studentdata.ftl");
		
		StringWriter writer = new StringWriter();
		
		Map<String, Map<Integer, Map<String, String>>> data = new XMLParser().xmlparser(filepath);
		
		template.process(data, writer);
		
		FileOutputStream output = new FileOutputStream(new File("A:\\Github\\BlueScope_Training\\Task10\\src\\studentdata.json"));
		output.write(writer.toString().getBytes());
		
		return writer.toString();
	}
	
	
	public static void main(String[] args) throws IOException, TemplateException {
		System.out.println(ftl("A:\\Github\\BlueScope_Training\\Task10\\src\\studentdata.xml"));	
	}

}
