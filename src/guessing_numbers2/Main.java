package guessing_numbers2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String answer = "";																								// set minimum and maximum for guesses
        int min = 0;
        int max = 1000;
        System.out.println("Think of a number between 0 and 1000 and I'll guess it in no more than 10 attempts.");
        System.out.println(" Please answer using 'too much', 'too little' and 'correct' phrases.");
        for(int i=1;i<11;i++) {																							// guess up to 10 times adjusting minimum and maximum
            int guess = ((max-min)/2)+min;
            while(!answer.equals("correct")&&!answer.equals("too much")&&!answer.equals("too little")) {
                System.out.println(i + ". My guess is: " + guess + ". Is this correct, too much or too little?");
                answer = scan.nextLine();																				// console input
                switch (answer) {																						// recognize answers
                    case "correct":
                        System.out.println("I won!");
                        scan.close();																						// close the scanner
                        System.exit(0);																						// and terminate early
                        break;
                    case "too much":
                        max = guess;
                        break;
                    case "too little":
                        min = guess;
                        break;
                    default:
                        System.out.println("Please answer using 'too much', 'too little' and 'correct' phrases...");
                        break;
                }
            }
            answer="";																									// wipe out the old answer
        }
        System.out.println("Looks like I lost :( ... Just kidding, you are a liar!!!");
        scan.close();
    }
}