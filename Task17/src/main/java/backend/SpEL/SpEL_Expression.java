package backend.SpEL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import backend.service.Expression_Service;
import backend.service.Properties_Service;

@RestController
@RequestMapping("/SpEL")
public class SpEL_Expression {

	Expression_Service service = new Expression_Service("Ashok","Tirunelveli", 22);
	
	EvaluationContext context = new StandardEvaluationContext(service);
	
	//Expression Evaluation
	
	@PostMapping("/expression")
	public ResponseEntity<String> expression() {
		
		ExpressionParser parser = new SpelExpressionParser();
		
		//Expression exp = parser.parseExpression("'Hello World'");
		
		//Expression exp = parser.parseExpression("'Hello World'.concat('!')");
		
		//Expression exp = parser.parseExpression("'Hello World'.bytes");
		
		//Expression exp = parser.parseExpression("'Hello World'.bytes.length");
		
		//Expression exp = parser.parseExpression("'Hello World'.toUpperCase()");
		
		parser.parseExpression("name").setValue(context, "Kumar");
		
		Expression exp = parser.parseExpression("name");
		
		Object message = exp.getValue(context);
		
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
	
	public static String print_name() {
		return "Ashok";
	}
	
	//Properties, Arrays, Lists, Maps, Indexers, Operators, Functions
	
	@PostMapping("/properties")
	public ResponseEntity<String> properties() throws NoSuchMethodException, SecurityException{
		
		ExpressionParser parser = new SpelExpressionParser();
		
		//Map
		
		Map<String, String> maps1 = new HashMap<String, String>();
		
		maps1.put("name", "Ashok");
		maps1.put("dept", "MCA");
		maps1.put("age", "22");
		
		//ArrayList
		
		ArrayList<String> arrays1 = new ArrayList<String>();
		
		arrays1.add("Ashok");
		arrays1.add("MCA");
		arrays1.add("22");
		
		//HashMap
		
		HashMap<String, String> maps2 = new HashMap<String, String>();
		
		maps2.put("name", "Ashok");
		maps2.put("dept", "MCA");
		maps2.put("age", "22");
		
		Properties_Service properties = new Properties_Service(maps1, arrays1, maps2);
		
		EvaluationContext context = new StandardEvaluationContext(properties);
		
		//Expression exp = parser.parseExpression("map1['name']");
		
		//Expression exp = parser.parseExpression("array1[0]");
		
		//Expression exp = parser.parseExpression("map2['name']");
		
		//List<Integer> exp = (List) parser.parseExpression("{1,2,3}").getValue();
		
		//Object message = exp.getValue(context); 
		
		//int exp[] = (int[]) parser.parseExpression("new int[]{1,2,3}").getValue();
		

		//functions
		
		StandardEvaluationContext context1 = new StandardEvaluationContext();
		
		context1.registerFunction("ashok", SpEL_Expression.class.getDeclaredMethod("print_name"));
		
		Object result = parser.parseExpression("#ashok()").getValue(context1);
		
		String exp = (String) parser.parseExpression("'ashok'.substring(0,2)").getValue();
	
		return ResponseEntity.status(HttpStatus.OK).body(result.toString());
	}
	
	//operators
	
	@PostMapping("/operators")
	ResponseEntity<String> operators(){
		
		ExpressionParser parser = new SpelExpressionParser();
		
		//Expression exp = parser.parseExpression("'ashokkumar@gmail.com' matches '\\b[A-Za-z0-9.%-_]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b'");
		
		//Expression exp = parser.parseExpression("5==5");

		//Expression exp = parser.parseExpression("5==5");

		//Expression exp = parser.parseExpression("5==5 and 5==4");

		//Expression exp = parser.parseExpression("5==5 or 5==4");
		
		//Expression exp = parser.parseExpression("!(5==5)");
		
		//Expression exp = parser.parseExpression("5+5");
		
		
		//Assignment
		
		context.setVariable("college", "FX");
		
		parser.parseExpression("name").setValue(context, "Kumar");
		
		parser.parseExpression("name = #college");
		
		//ternary operator
		
		//Expression exp = parser.parseExpression("name=='Kumar'?'true':'false'");
		
		//Elvis operator
		
		Expression exp = parser.parseExpression("name=='Kumar'?:'false'");
		
		Object message = exp.getValue(context);
		
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
}
