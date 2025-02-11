package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Task19Application {

	public static void main(String[] args) {
		//SpringApplication.run(Task19Application.class, args);
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("batch.xml");
	}

}
