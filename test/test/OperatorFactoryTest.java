package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.function.DoubleBinaryOperator;

import org.junit.Test;

import operator.OperatorFactory;

import static org.junit.Assert.*;

/**
 * Test for Problem 1, OperatorFactory.
 * 
 */
public class OperatorFactoryTest {
	final DoubleBinaryOperator op1 = Math::max;
	final DoubleBinaryOperator op2 = (a,b) -> a/b;
	
	@Test
	public void testPrivateConstructor() throws NoSuchMethodException, SecurityException {
		Class<?> clazz = operator.OperatorFactory.class;
		Constructor<?> cons = clazz.getDeclaredConstructor();
		if (cons == null) fail("Doesn't have no-arg constructor");
		int mods = cons.getModifiers();
		if (Modifier.isPrivate(mods) || Modifier.isProtected(mods)) {
			// OK
		}
		else fail("Constructor not private or protected;");
	}
	
	@Test
	public void instanceIsSingleton() {
		Object instance1 = OperatorFactory.getInstance();
		if (instance1 == null) fail("getInstance() returned null");
		if (instance1.getClass() != OperatorFactory.class) fail("getInstance() didn't return OperatorFactory");
		Object instance2 = OperatorFactory.getInstance();
		Object instance3 = OperatorFactory.getInstance();
		if (instance1 != instance2 || instance2 != instance3)
			fail("getInstance() is not a singleton");
	}
	
	@Test
	public void testPut() {
		OperatorFactory factory = OperatorFactory.getInstance();
		factory.put("m", op1);
		factory.put("/", op2);
		assertNotNull("get(m) after put(m) returned null", factory.get("m") );
		assertNotNull("get(/) after put(/) returned null", factory.get("/") );
		assertSame( op1, factory.get("m"));
		assertSame( op2, factory.get("/"));
		assertNotSame( factory.get("m"), factory.get("/") );
		// test again... get() shouldn't change 
		assertSame( op1, factory.get("m"));
		assertSame( op2, factory.get("/"));
	}
	
	@Test
	public void testNonExistentOperator() {
		String bogus = "$";
		OperatorFactory factory = OperatorFactory.getInstance();
		// make sure it contains at least one operator
		factory.put("/", op2);
		assertNull("get("+bogus+") should be null", factory.get(bogus));
	}
}
