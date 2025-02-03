package jsonschema;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Json_Schema {

    public static void main(String[] args) throws IOException {
    	
    	//allof
    	String filename = "allof.json";
        
    	//anyof
    	//String filename = "anyof.json";
    	
    	//oneOf
    	//String filename = "oneof.json";
    	
    	//if, else, then
    	//String filename = "if_then_else.json";
    	
    	//not
    	//String filename = "not.json";
    	
    	//dependentSchemas
    	//String filename = "dependentSchemas.json";
    	
    	//propertyName
    	//String filename = "propertyNames.json";
    	
    	//contains
    	//String filename = "contains.json";
    	
    	//prefixItems
    	//String filename = "prefixItems.json";
    	
    	File jsonFile = new File("A:\\Github\\BlueScope_Training\\Task14\\src\\files\\Applicator\\StudentData.json");
        File schemaFile = new File("A:\\Github\\BlueScope_Training\\Task14\\src\\files\\Applicator\\"+filename);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonfile = mapper.readTree(jsonFile);

        JsonNode schemafile = mapper.readTree(schemaFile);

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = schemaFactory.getSchema(schemafile);

        Set<ValidationMessage> validationMessages = schema.validate(jsonfile);

        if (validationMessages.isEmpty()) {
            System.out.println("JSON is valid!");
        } else {
            System.out.println("JSON is invalid. Errors:");
            for (ValidationMessage validationMessage : validationMessages) {
                System.out.println(validationMessage.getMessage());
            }
        }
    }
}
