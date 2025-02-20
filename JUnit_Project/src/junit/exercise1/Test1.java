package junit.exercise1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class Test1 {

	@Test
	void addition() {
		int sum = 1+1;
		assertEquals(2, sum, "Addition is failed");
	}
	
	@Test
	void multiplication() {
		int sum = 1*1;
		assertEquals(1, sum, "Multiplication is failed");
	}
	
	@Test
	void subtraction() {
		int sum = 1-1;
		assertEquals(0, sum, "Subtraction is failed");
	}
	
	@Test
	void division() {
		int sum = 2/1;
		assertEquals(1, sum, "Division is failed");
	}

}
