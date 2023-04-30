package ie.atu.dip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InsuranceProgramTest {
	/*
	 * Capturing the system output to create a less clustered console when running
	 * the test. Also using to test output in methods below.
	 * https://www.baeldung.com/java-testing-system-out-println (Also in supplied
	 * References.pdf)
	 */
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private final PrintStream standardOut = System.out;
	private InsuranceProgram insure;
	private static long start;
	private static long end;

	@BeforeAll
	public static void setup() {
		// Setting up the test and capturing time information on entire test class.
		System.out.println("InsuranceProgram Test Started");
		start = System.currentTimeMillis();
	}

	/*
	 * Testing each method individually, by using @BeforeEach a new instance of
	 * InsuranceProgram is used each time. Output is captured here instead to
	 * allow @BeforeAll output to reach console.
	 */
	@BeforeEach
	public void setupEachTest() {
		insure = new InsuranceProgram();
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	/*
	 * Checking multiple times so each if statement is tested. One case each for
	 * below, equal to and above surcharge() condition. The Strings in Csv Source
	 * are asserted against the console output.
	 */
	@ParameterizedTest
	@CsvSource({ "24, 600, Additional surcharge 100", "25, 500, No additional surcharge",
			"26, 500, No additional surcharge" })
	public void testSurcharge(int age, int value, String expected) {
		int result = insure.surcharge(age);
		assertEquals(result, value);
		assertEquals(expected, outputStreamCaptor.toString().trim());
	}

	/*
	 * First Exception being tested. Input Mismatch if user types in a string
	 * instead of an expected integer value. Scanner used within method to simulate
	 * this in a meaningful way.
	 */
	@Test
	public void inputMismatchSurcharge() {
		assertThrows(InputMismatchException.class, () -> {
			String str = "twenty";
			Scanner s = new Scanner(str);
			int mismatch = s.nextInt();
			insure.surcharge(mismatch);
			s.close();
		});
	}

	/*
	 * Testing each option available to the switch statement. The getBasicInsurance
	 * method was added to InsuranceProgram class to allow better testing of this
	 * method and to show an alternative way than the method returning a value as
	 * used previously.
	 */
	@ParameterizedTest
	@CsvSource({ "0, 500", "1,550", "2,625", "3,725", "4,875", "5,1075", "6,500" })
	public void testAccidents(int option, int value) {
		insure.accidents(option);
		int actualResult = insure.getBasicInsurance();
		assertEquals(actualResult, value);
	}

	// Testing Input Mismatch in same fashion for this method as explained above.
	@Test
	public void inputMismatchAccidents() {
		assertThrows(InputMismatchException.class, () -> {
			String str = "twenty";
			Scanner s = new Scanner(str);
			int mismatch = s.nextInt();
			insure.accidents(mismatch);
			s.close();
		});
	}

	/*
	 * Second Exception being tested. Wanted the exception to be meaningful in that
	 * if user added an amount of accidents that was not >= 0 an error would be
	 * thrown instead of program running in an unwanted way.
	 */
	@Test
	public void illegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> {
			insure.accidents(-5);
		});
	}

	// Quick test to check returning expected value.
	@Test
	public void testGetBasicInsurance() {
		int result = insure.getBasicInsurance();
		assertEquals(result, 500);
	}

	// After Each test stop capturing the output.
	@AfterEach
	public void cleanUp() {
		System.setOut(standardOut);
	}

	// Closing down the class and displaying some useful information on the overall
	// runtime of the class.
	@AfterAll
	public static void finishTest() {
		end = System.currentTimeMillis();
		System.out.print("InsuranceProgram Test Ended");
		System.out.print("\tTest time: " + (end - start) + " milliseconds\n");
	}
}
