package jsonschema;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

public class Json_Schema {

	public static void main(String[] args) throws IOException {
		
		//Validation
		
		//type
		//String filename = "Validation\\type.json";
		
		//enum
		//String filename = "Validation\\enum.json";
		
		//const
		//String filename = "Validation\\const.json";
		
		//maxLength and minLength
		//String filename = "Validation\\max&minlength.json";
		
		//exclusiveMinimum and exclusiveMaximum
		//String filename = "Validation\\exclusiveMinimum&Maximum.json";
		
		//maximum and minimum
		//String filename = "Validation\\maximum&minimum.json";
		
		//multipleOf
		//String filename = "Validation\\multipleOf.json";
		
		//dependentRequired
		//String filename = "Validation\\dependentRequired.json";
		
		//maxProperties and minProperties
		//String filename = "Validation\\max&minProperties.json";
		
		//required
		//String filename = "Validation\\required.json";
		
		//maxItems and minItems
		//String filename = "Validation\\max&minItems.json";
		
		//maxContains and minContains
		//String filename = "Validation\\min&maxContains.json";
		
		//uniqueItems
		//String filename = "Validation\\uniqueItems.json";
		
		
		//Meta Data
		
		//title
		//String filename = "MetaData\\title.json";
		
		//description
		//String filename = "MetaData\\description.json";
		
		//default
		//String filename = "MetaData\\default.json";
		
		//deprecated
		//String filename = "MetaData\\deprecated.json";
		
		//example
		String filename = "MetaData\\examples.json";
		
		File jsonFile = new File("A:\\Github\\BlueScope_Training\\Task15\\src\\files\\StudentData.json");
		File schemaFile = new File("A:\\Github\\BlueScope_Training\\Task15\\src\\files\\"+filename);
		
		ObjectMapper object = new ObjectMapper();
		
		JsonNode jsonfile = object.readTree(jsonFile);
		JsonNode schemafile = object.readTree(schemaFile);
	
		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
		JsonSchema schema = schemaFactory.getSchema(schemafile);
		
		Set<ValidationMessage> message = schema.validate(jsonfile);
		
		if(message.isEmpty()) {
			System.out.println("JSON is valid");
		}else {
			System.out.println("JSON is invalid. \nError: ");
			for(ValidationMessage messages: message) {
				System.out.println(messages.getMessage());
			}
		}	
	}
}
