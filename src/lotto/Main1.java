package lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main1 {

	private static Scanner scan;

	public static void main(String[] args) {

		int[] chosenNumbers = new int[6];
		int[] drawnNumbers = new int[6];

		scan = new Scanner(System.in);
		chosenNumbers = getNumbers(1, 49);							// input, sort and display chosen numbers
		Arrays.sort(chosenNumbers);
		System.out.print("Numbers you have chosen: ");
		System.out.println(Arrays.toString(chosenNumbers));

		Integer[] arr = new Integer[49];							// generate an array of 49 consecutive numbers
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		Collections.shuffle(Arrays.asList(arr));					// shuffle the array
		for (int i = 0; i < 6; i++) {								// draw first 6 numbers
			drawnNumbers[i] = arr[i];
		}
		System.out.print("Numbers drawn: ");						// display drawn numbers
		System.out.println(Arrays.toString(drawnNumbers));
		int numberOfHits = 0;										// calculate the number of correct hits
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (chosenNumbers[i] == drawnNumbers[j]) {
					numberOfHits++;
				}
			}
		}
		System.out.println("Number of hits: " + numberOfHits);		// display the number of correct hits

	}

	public static int[] getNumbers(int min, int max) {				// this method gets 6 integers from console input
																	// checking for mistakes, numbers outside the range 
		String temp = "";											// and duplicates
		Integer num = 0;
		int[] numbers = new int[6];
		System.out.println("You need 6 numbers to play.");
		for (int i = 0; i < 6; i++) {
			while (true) {
				System.out.println("Pick a number between " + min + " and " + max + ": ");
				while (!scan.hasNextInt()) {
					temp = scan.next();
					System.out.println("That's not a number you fool, try again: ");
				}
				temp = scan.next();
				num = Integer.parseInt(temp);
				if (num < min || num > max) {
					System.out.println("The number is outside the range, try again: ");
					i--;
					break;
				}
				for (int j = 0; j < 6; j++) {
					if (num == numbers[j]) {
						System.out.println("You have already picked that number, try again: ");
						i--;
						break;
					}
				}
				numbers[i] = num;
				break;
			}
		}
		return numbers;
	}

}
