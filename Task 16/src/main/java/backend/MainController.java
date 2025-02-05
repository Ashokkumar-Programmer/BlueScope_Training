package backend;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Set;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

@RestController
@RequestMapping("/json")
public class MainController {
	
	@PostMapping(value="/validate", consumes="application/json", produces="application/json")
	public ResponseEntity<String> json_validate(@RequestBody String json) throws IOException, InterruptedException{
		
        try {
//            JSONTokener tokener = new JSONTokener(new StringReader(json));
//            JSONObject jsonObject = new JSONObject(tokener);
            File schemaFile = new File("A:\\Github\\BlueScope_Training\\Task 16\\src\\main\\resources\\templates\\json-schema.json");
    		
    		ObjectMapper object = new ObjectMapper();
    		
    		JsonNode jsonfile = object.readTree(json);
    		JsonNode schemafile = object.readTree(schemaFile);
    	
    		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
    		JsonSchema schema = schemaFactory.getSchema(schemafile);
    		
    		Set<ValidationMessage> message = schema.validate(jsonfile);
    		String error = "Invalid JSON\nError: ";
    		if(!message.isEmpty()) {
    			for(ValidationMessage messages: message) {
    				error += messages.getMessage()+"\n";
    			}
    			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    		}
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid JSON");
        }
		return ResponseEntity.status(HttpStatus.OK).body("Valid JSON");
	}
}
