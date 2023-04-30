package ie.atu.dip;

public class InsuranceProgram {
	private int basicInsurance = 500; 
	private int surcharge = 100; 

	/*
	 * Returns the value of basicInsurance for the purpose of testing. Allows an
	 * assertion on both branches of the if statement.
	 */
	public int surcharge(int age) {
		if (age < 25) {
			System.out.println("Additional surcharge " + surcharge);
			basicInsurance += surcharge;
		}
		if (age >= 25) {
			System.out.println("No additional surcharge");
		}
		return basicInsurance;
	}

	/*
	 * Addition of the surcharge handled before System.out.println in case 1 through
	 * to 5 allowing the getter method for basicInsurance to test the expected
	 * values. If statement accidents > 6 changed to accidents >= 6 to close gap in
	 * options.
	 */
	public void accidents(int accidents) {
		switch (accidents) {
		case 0:
			System.out.println("No surcharge");
			System.out.println("Total amount to pay: " + basicInsurance);
			break;
		case 1:
			basicInsurance += 50;
			System.out.println(
					"Additional surcharge for accident: " + 50 + " \ntotal amount to pay: " + (basicInsurance));
			break;
		case 2:
			basicInsurance += 125;
			System.out.println(
					"Additional surcharge for accident: " + 125 + " \ntotal amount to pay: " + (basicInsurance));
			break;
		case 3:
			basicInsurance += 225;
			System.out.println(
					"Additional surcharge for accident: " + 225 + " \ntotal amount to pay: " + (basicInsurance));
			break;
		case 4:
			basicInsurance += 375;
			System.out.println(
					"Additional surcharge for accident: " + 375 + " \ntotal amount to pay: " + (basicInsurance));
			break;
		case 5:
			basicInsurance += 575;
			System.out.println(
					"Additional surcharge for accident: " + 575 + " \ntotal amount to pay: " + (basicInsurance));
			break;
		}

		if (accidents >= 6) {
			System.out.println("No insurance");
		}

		// Added to allow a meaningful exception test to the program also does not allow
		// negative numbers which would cause the program to run it unexpected way.
		if (accidents < 0) {
			throw new IllegalArgumentException("Number cannot be negative.");
		}
	}

	/*
	 * Added a getter method for the instance variable basicInsurance to allow
	 * testing of the switch statement above by getting the expected value.
	 */
	public int getBasicInsurance() {
		return basicInsurance;
	}

}
