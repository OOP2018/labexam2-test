package test;
import org.junit.Test;

import operator.OperatorFactory;
import static org.junit.Assert.*;
import java.util.function.DoubleBinaryOperator;
import org.junit.Before;

/**
 * Test functionality of operators in problem 2.
 * 
 * Manual inspection: verify ops implemented as anonymous class and Lambda.
 */
public class BinaryOperatorTest {
	private static final double TOL = 1.0E-4;
	private OperatorFactory factory;
	
	// arguments for testing BinaryOperators
	enum Args {
		test1(16.0, 8.0),
		test2(0.5, 0.25),
		test3(0.0, 20.0),
		test4(1024.0, 0.0),
		test5(0.0, 0.0),
		test6(1024*1024, -0.5);
		
		public final double x, y;
		private Args(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		private String of(String op) {
			return String.format("%.2f %s %.2f", this.x, op, this.y);
		}
		
	};
	
	@Before
	public void setUp() {
		factory = OperatorFactory.getInstance();
	}

	@Test
	public void testMultiply() {
		String name = "*";
		DoubleBinaryOperator op = factory.get(name);
		assertNotNull("get("+name+") returned null", op);
		for(Args test: Args.values()) {
			double expected = test.x * test.y;
			assertEquals(test.of(name), expected, op.applyAsDouble(test.x, test.y), TOL);
		}
	}
	
	@Test
	public void testAdd() {
		String name = "+";
		DoubleBinaryOperator op = factory.get(name);
		assertNotNull("get("+name+") returned null", op);
		for(Args test: Args.values()) {
			double expected = test.x + test.y;
			assertEquals(test.of(name), expected, op.applyAsDouble(test.x, test.y), TOL);
		}
	}
	
	
	@Test
	public void testPower() {
		String name = "^";
		DoubleBinaryOperator op = factory.get(name);
		assertNotNull("get("+name+") returned null", op);
		for(Args test: Args.values()) {
			double expected = Math.pow(test.x, test.y);
			assertEquals(test.of(name), expected, op.applyAsDouble(test.x, test.y), TOL);
		}
	}
	
	
	@Test
	public void testMultiplyIsLambda() {
		Object multiply = factory.get("*");
		assertNotNull("get(*) returns null", multiply);
		String name = multiply.getClass().getSimpleName();
		// for Lambdas the class name contains "$Lambda$"
		assertTrue("* is not a Lambda", name.contains("$Lambda$"));
	}
	
	@Test
	public void testAddIsAnonymousClass() {
		Object op = factory.get("+");
		assertNotNull("get(+) returns null", op);
		String name = op.getClass().getSimpleName();
		// for Anonymous class the simple name is empty
		assertTrue("+ is not Anonymous Class", name.trim().isEmpty());
	}
}
