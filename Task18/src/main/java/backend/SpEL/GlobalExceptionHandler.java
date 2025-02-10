package backend.SpEL;

import org.springframework.expression.spel.SpelParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Invalid JSON format.\n"+ex.getMessage());
    }
    
    @ExceptionHandler(SpelParseException.class)
    public ResponseEntity<String> handleSpelParseException(SpelParseException ex) {
    	return ResponseEntity.badRequest().body("Invalid SpEL Expression.\n"+ex.getMessage());
    }
}