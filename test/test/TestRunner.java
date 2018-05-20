package test;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/**
 * Run JUnit test suites and print results on console.
 * You can run JUnit tests in an IDE without using this class.
 * For running on console, this TestRunner prints messages
 * about failed tests that are easier to read than JUnit output.
 * 
 * Run this class as an ordinary Java application (using main).
 * 
 * @author James Brucker
 *
 */
public class TestRunner {
	static final java.util.Scanner console = new java.util.Scanner(System.in);
	
	/**
	 * Run the JUnit tests and display results.
	 * @param args: "u" unit tests, "p" printReceipt, "m" run Main. No args: do everything.
	 */
	public static void main(String[] args) {

		System.out.println(">>> Run JUnit Tests");
		int failures = runTestSuite( OperatorFactoryTest.class );
		failures += runTestSuite( BinaryOperatorTest.class );
		failures += runTestSuite( ComboFoodTest2.class );
		if (failures > 0) System.out.println("Failures: "+failures);

		
//		if (printReceipt) {
//			System.out.println("\n>>> Test Calculaor.main().");
//			pause("Press ENTER to start: ");
//			// return code indicates success or failure
//			try {
//				testCalculator();
//			} catch (Exception ex) {
//				System.out.println("Threw exception");
//				System.out.println(ex.getMessage());
//				ex.printStackTrace();
//			}
//		}
		
	}
		
	/** Print a prompt and wait for user to press enter. */
	public static void pause(String prompt) {
		System.out.print(prompt);
		console.nextLine();
	}
	
	/**
	 * Run a JUnit test suite and print summary of results.
	 * @param clazz class object for the JUnit test suite.
	 * @return number of failed tests
	 */
	public static int runTestSuite( Class<?> clazz ) {
		// Print class name first in case it crashes
		String name = clazz.getSimpleName();
		System.out.println("Running "+name);
		Result result = org.junit.runner.JUnitCore.runClasses( clazz );
		int count = result.getRunCount();
		int failed = result.getFailureCount();
		int success = count - failed;
		System.out.printf("%-24.24s   Success: %d  Failures: %d\n",
				clazz.getSimpleName(), success, failed);
		
		// this sometimes doesn't seem to return all the failures
		List<Failure> failures = result.getFailures();
		for(Failure f: failures) {
			Description d = f.getDescription();			
			System.out.println( f.getTestHeader() +": "+ f.getMessage() );
			System.out.println( d.getDisplayName() );
		}
		// return the number of failures
		return failed;
	}
	
	/** Add some test products to Store. */
	public static void testCalculator() {
	//	calculator.Calculator.main();
	}

}
