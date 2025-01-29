package backend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@RestController
@RequestMapping("/json")
public class MainController {
	
	public static String xmltojson(String xml) throws IOException, TemplateException {
		try {
			JSONObject xmldata = XML.toJSONObject(xml).getJSONObject("Remittance");
			Map<String, Object> jsondata = new HashMap<>();
			jsondata.put("data", xmldata);
			
			Configuration config = new Configuration(Configuration.VERSION_2_3_0);
			config.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
			
			Template template = config.getTemplate("json.ftl");
			
			StringWriter writer = new StringWriter();
			
			template.process(jsondata, writer);
			
			FileOutputStream write = new FileOutputStream(new File("A:\\Github\\BlueScope_Training\\Task11\\src\\main\\resources\\templates\\JSONData.json"));
			write.write(writer.toString().getBytes());
		}catch(Exception e) {
			return e.getMessage();
		}
		return xml;
	}
	
	
	@PostMapping(value="/json-data", consumes="application/json", produces="application/json")
	public ResponseEntity<String> json_data(@RequestBody String json) throws IOException, TemplateException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(json, Map.class);
		
		Configuration config = new Configuration(Configuration.VERSION_2_3_0);
		config.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
		
		Template template = config.getTemplate("xml.ftl");
		
		StringWriter writer = new StringWriter();
		
		template.process(map, writer);
		
		String beeceptorUrl = "https://task11.free.beeceptor.com";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        String xmlBody = writer.toString(); 
        		
        HttpEntity<String> entity = new HttpEntity<>(xmlBody, headers);
        ResponseEntity<String> response = null;
        try {
        	response = restTemplate.exchange(beeceptorUrl, HttpMethod.POST, entity, String.class);
        }catch(HttpClientErrorException e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Too Many Requests on POST request for \"https://task11.free.beeceptor.com\"");
        }
        		
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(xmltojson(response.getBody()));
	}
}
