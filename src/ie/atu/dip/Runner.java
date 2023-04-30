package ie.atu.dip;

import java.util.Scanner;

public class Runner {

	/*
	 * Separate class that handles the execution of the program and passes user
	 * input to the methods of InsuranceProgram class. Also allows for a better
	 * example of using a test suite by having two classes.
	 */
	public static void main(String[] args) {
		InsuranceProgram insure = new InsuranceProgram();
		Scanner input = new Scanner(System.in);

		System.out.print("Enter your age: ");
		int age = input.nextInt();
		insure.surcharge(age);

		System.out.print("\nHow many accidents did you have? ");
		int accidents = input.nextInt();
		insure.accidents(accidents);

		input.close();
	}
}
