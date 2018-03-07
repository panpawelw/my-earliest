package guess_numbers1;

import java.util.Random;
import java.util.Scanner;

public class Main1 {
	
	private static Scanner scan;
	
	public static void main(String[] args) {

		int guess;
		scan = new Scanner(System.in);
		Random r = new Random();
		int numberToGuess = r.nextInt(101);											// Draw a random number
		while(true) {
			guess = getNumber(1,100);
			if(guess < numberToGuess) {												// Check if the guess is correct
				System.out.println("Too little!");
			} else if(guess > numberToGuess) {
				System.out.println("Too much!");
			} else {
				System.out.println("Just right!");
				break;
			}
			
		}
		
	}

	public static int getNumber(int min, int max) {									// Get a number from console checking for mistakes and given range
		
		int number = 0;
		
		System.out.println("Enter a number between " + min + " and " + max + ":");
		while(true) {
			while(!scan.hasNextInt()) {
		        scan.next();
		        System.out.println("It's not a number, try again!");
		    }
			number = Integer.parseInt(scan.next());
			if(number < min || number > max) {
				System.out.println("This number is outside the given range!");
			} else {
				break;
			}
		}
	    return number;
	}
}