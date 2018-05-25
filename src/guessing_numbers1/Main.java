package guessing_numbers1;

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	private static Scanner scan;
	
	public static void main(String[] args) {

		int guess;
		scan = new Scanner(System.in);
		Random r = new Random();
		int numberToGuess = r.nextInt(101);											// draw a random number
		while(true) {
			guess = getNumber(1,100);
			if(guess < numberToGuess) {												// check if the guess is correct
				System.out.println("Too little!");
			} else if(guess > numberToGuess) {
				System.out.println("Too much!");
			} else {
				System.out.println("Just right!");
				break;
			}
		}
		scan.close();																// close the scanner just before ending the program
	}

	public static int getNumber(int min, int max) {									// this method scans console for an integer 
																					// in a given range checking for input errors
		int number = 0;
		
		System.out.println("Enter a number between " + min + " and " + max + ":");
		while(true) {
			while(!scan.hasNextInt()) {
		        scan.next();
		        System.out.println("It's not a number, try again!");				// check if answer is an integer
		    }
			number = Integer.parseInt(scan.next());
			if(number < min || number > max) {										// check if this integer is between certain minimum and maximum 
				System.out.println("This number is outside the given range!");
			} else {
				break;
			}
		}
	    return number;
	}
	
}