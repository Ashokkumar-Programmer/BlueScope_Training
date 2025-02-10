package backend.SpEL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.service.StudentData;

@RestController
@RequestMapping("/SpEL")
public class SpEL_Expression {

    @PostMapping(value = "/validate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> validate(@RequestBody StudentData json) {
        
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(json);

        for (int i = 0; i < json.getStudents().size(); i++) {
            StudentData.Student student = json.getStudents().get(i);
            context.setVariable("i", i);

            String result = "";
            
            result = parser.parseExpression("students[#i].id matches '^[1-9][0-9]*$'?'Valid':'Invalid'").getValue(context, String.class);
            if (!result.equals("Valid")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON. Error: id is invalid.");
            }

            result = parser.parseExpression("students[#i].name!=null and students[#i].name matches '^[A-Z][a-z]+(\\s[A-Z][a-z]+)*$'?'Valid':'Invalid'").getValue(context, String.class);
            if (!result.equals("Valid")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON. Error: Name is invalid.");
            }

            result = parser.parseExpression("students[#i].age!=0 and students[#i].age matches '^[1-9][0-9]{0,2}$'?'Valid':'Invalid'").getValue(context, String.class);
            if (!result.equals("Valid")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON. Error: Age is invalid.");
            }

            result = parser.parseExpression("students[#i].gender matches '(?i)^(Male|Female)$'?'Valid':'Invalid'").getValue(context, String.class);
            if (!result.equals("Valid")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON. Error: Gender is invalid.");
            }

            result = parser.parseExpression("students[#i].subjects.size()>0?'Valid':'Invalid'").getValue(context, String.class);
            if (!result.equals("Valid")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON. Error: Subjects cannot be empty.");
            }

            List<String> gradeSubjects = new ArrayList<>();
            for (Entry<String, Integer> value : student.getGrades().entrySet()) {
                if (value.getValue() == 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON. Error: grades contain zero.");
                }
                gradeSubjects.add(value.getKey());
            }

            List<String> subjects = new ArrayList<>(student.getSubjects());
            subjects.removeAll(gradeSubjects);
            if (!subjects.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON. Error: Subjects do not match grades.");
            }

            result = parser.parseExpression("students[#i].address.city!=null and students[#i].address.zip!=null and students[#i].address.city matches '^[A-Z][a-z]+(\\s[A-Z][a-z]+)*$' and students[#i].address.zip matches '^[0-9]+$' and students[#i].address.city!='' and students[#i].address.zip!=''?'Valid':'Invalid'").getValue(context, String.class);

            if (!result.equals("Valid")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON. Error: Address details are incorrect.");
            }

            result = parser.parseExpression("students[#i].graduated == false or students[#i].graduated == true?'Valid':'Invalid'").getValue(context, String.class);
            if (!result.equals("Valid")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON. Error: Graduated field must be true or false.");
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body("Valid JSON");
    }
}
