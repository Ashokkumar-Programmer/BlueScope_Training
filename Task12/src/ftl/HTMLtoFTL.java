package ftl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HTMLtoFTL {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TemplateException {
		// TODO Auto-generated method stub
		FileReader file = new FileReader(new File("A:\\Github\\BlueScope_Training\\Task12\\src\\files\\StudentData.html"));
		int i=0;
		String html = "";
		while((i=file.read())!=-1) {
			html += (char)i;
		}
		
		Document document = Jsoup.parse(html);
		Elements element = document.getAllElements();
		Map<String, String> map = new HashMap<>();
		ArrayList<String> key = new ArrayList<>();
		element.stream().forEach((e)->{
			  if(e.tagName().equals("th") && key.size()<=4) {
				  key.add(e.text());
			  }
		});
		i=0;
		Map<String, Map<String, String>> datas = new HashMap<>();
		String mapkey = null;
		for(Element e: element) {
			
			if(e.tagName().equals("td")){
				if(!e.text().equals(""))
					map.put(key.get(i), e.text());
					if(i==0) 
						mapkey = e.text();
				i++;				
			}
			if(i==4 && !mapkey.equals("")) {
				i=0;
				datas.put(mapkey, new HashMap<>(map));
				map.clear();
			}
		}
		
		Map<String, Map<String, Map<String, String>>> htmldata = new HashMap<>();
		
		htmldata.put("data", datas);
		
		Configuration config = new Configuration(Configuration.VERSION_2_3_0);
		config.setDirectoryForTemplateLoading(new File("src/files"));
		
		
		Template template = config.getTemplate("JSON.ftl");
		
		StringWriter writer = new StringWriter();
		
		template.process(htmldata, writer);
		
		System.out.println(writer.toString());
		
		FileOutputStream write = new FileOutputStream(new File("src/files/StudentData.json"));
		write.write(writer.toString().getBytes());
	}
}
