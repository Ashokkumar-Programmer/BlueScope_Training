package ftl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FTLClass {

	public static void main(String[] args) throws IOException, TemplateException, SAXException, ParserConfigurationException {
		// TODO Auto-generated method stub
		String filename = null;
		Configuration config = new Configuration(Configuration.VERSION_2_3_0);
		config.setDirectoryForTemplateLoading(new File("src/files"));
		
		//filename = "Keywords1.ftl";
		//filename = "Functions.ftl";
		//filename = "Conditions.ftl";
		//filename = "List.ftl";
		//filename = "macro.ftl";
		filename = "xml.ftl";
		Template template = config.getTemplate(filename);
		
		StringWriter writer = new StringWriter();
		
		Map<String, Object> data = new HashMap<>();
		
		data.put("data", freemarker.ext.dom.NodeModel.parse(new File("src/files/data.xml")));
		
		template.process(data, writer);
		
		System.out.println(writer.toString());
		
	}

}
