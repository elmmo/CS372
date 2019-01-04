import java.util.Scanner; 
import java.util.Random; 

public class HW1 {
	private static Scanner console = new Scanner(System.in); 
	
	// Takes in an user-inputted number and tests that many random ints to see if they're even/odd
	public static void evenOdd() {
		System.out.print("How many numbers should I test? Must be between 10 and 100. "); 
		int n = console.nextInt();
		if (n >= 10 && n <= 100) { // input validation 
			for (int i=0; i < n; i++) {
				// obtains a random num between 0 and 1000
				int random = (int)(Math.random()*1000); 
				String state = (random % 2 == 0) ? "even" : "odd"; 
				System.out.printf("%d is %s\n", random, state); 
			}
		} else {
			System.out.print("That number isn't between 10 and 100! Try again.");
		}
		System.out.println(); // for extra space at the end of the function output 
	}
	
	// Finds the area of a circle, given the radius of that circle 
	public static void circleArea() {
		final double PI = 3.14; 
		System.out.print("Radius of a circle: ");
		double r = console.nextInt(); 
		if (r > 0) {
			double area = PI*Math.pow(r, 2);
			System.out.printf("The area of a circle with radius %.2f is %.2f\n", r, area); 
		} else {
			System.out.print("You can't have a negative radius! Try again.");
		}
		System.out.println(); // for extra space at the end of the function output
	}
	
	// converts a String to an int without using the Java library method
	public static void stringToInt() {
		System.out.print("Give me a number, any (positive) number! ");
		String num = console.next(); 
		int numValue = 0; 
		if (Character.isDigit(num.charAt(0))) {
			// iterates over every character in the string backwards
			int length = num.length()-1; // required to ensure the loop runs the correct num of times
			int iterator = 0; 
			for (int c = length; c >= 0; c--) {
				// gets the numeric value of the last letter in the string 
				int value = Character.getNumericValue(num.charAt(c)); 
				// multiples the value by the power of 10 that corresponds to its placement
				numValue += value * Math.pow(10, iterator); 
				iterator++;
				// cuts down the string so we have the latest number
				num.substring(0, num.length()-1); 
			}
			System.out.printf("The number is %d\n", numValue);
		} else {
			System.out.println("You didn't give me a positive number...");
		}
		System.out.println(); // for extra space at the end of the function output
	}
}
