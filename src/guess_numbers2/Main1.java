package guess_numbers2;

import java.util.Scanner;

public class Main1 {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String answer = "";																								// set minimum and maximum for guesses
		int min = 0;
		int max = 1000;
		System.out.println("Think of a number between 0 and 1000 and I'll guess it in no more than 10 attempts.");
		for(int i=1;i<11;i++) {																							// guess up to 10 times adjusting minimum and maximum
			int guess = ((max-min)/2)+min;
			while(!answer.equals("correct")&&!answer.equals("too much")&&!answer.equals("too little")) {
				System.out.println(i + ". My guess is: " + guess + ". Is this correct, too much or too little?");
				answer = scan.nextLine();																				// console input
				switch (answer) {																						// recognize answers
				case "correct":
					System.out.println("I won!");
					System.exit(0);
					break;
				case "too much":
					max = guess;
					break;
				case "too little":
					min = guess;
					break;
				}
			}
			answer="";																									// wipe out the old answer
		}
	}
}