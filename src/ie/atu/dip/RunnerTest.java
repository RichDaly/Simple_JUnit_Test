package ie.atu.dip;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Implementing the Suite function, all tests will run from here.
@Suite
@SelectClasses({ InsuranceProgramTest.class })

class RunnerTest {
	/*
	 * Capturing the system output to create a less clustered console when running
	 * the test. Also using to test output in methods below.
	 * https://www.baeldung.com/java-testing-system-out-println (Also in supplied
	 * References.pdf)
	 */
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private final PrintStream standardOut = System.out;
	private static long start;
	private static long end;

	@BeforeEach
	public void setup() {
		// Setting up the test and capturing time information on entire test class.
		System.out.println("Runner Test Started");
		System.setOut(new PrintStream(outputStreamCaptor));
		start = System.currentTimeMillis();

		/*
		 * Making an instance of Runner to test operating as normal. Added suppression
		 * to stop unused warning as main method was called in a static way below.
		 * Adding to test Coverage.
		 */
		@SuppressWarnings("unused")
		Runner run = new Runner();
	}

	/*
	 * Found some useful information on setting system input to better test the main
	 * method and the Scanner used. Although not required to test Scanner, wanted a
	 * better and more meaningful test. Added better overall coverage to the test
	 * also.
	 * 
	 * https://www.danvega.dev/blog/2020/12/16/testing-standard-in-out-java/
	 * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/
	 * Formatter.html#syntax (Also in supplied References.pdf)
	 */
	@Test
	@Timeout(value = 100, unit = TimeUnit.MILLISECONDS) // Added Timeout here as larger test.
	public void mainTest() {
		String userInput = String.format("24%s1", System.lineSeparator());
		ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(bais);

		Runner.main(null);

		String expected = "Enter your age: Additional surcharge 100\r\n"
				+ "\nHow many accidents did you have? Additional surcharge for accident: 50 "
				+ "\ntotal amount to pay: 650";
		assertEquals(expected, outputStreamCaptor.toString().trim());
	}

	// Closing down the class and displaying some useful information on the overall
	// runtime of the class.
	@AfterEach
	public void cleanUp() {
		System.setOut(standardOut);
		end = System.currentTimeMillis();
		System.out.print("Runner Test Ended");
		System.out.print("\tTest time: " + (end - start) + " milliseconds\n");
	}
}
