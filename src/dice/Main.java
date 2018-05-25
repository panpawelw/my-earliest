package dice;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String input = "";
		while (true) {
			try(Scanner scan = new Scanner(System.in)){							// try with resources to make sure scanner is closed when program terminates
				System.out.println(
						"Enter dice throw parameters using XdY+Z format. X = number of dice (>=1, optional), Y = number of faces, Z = modifier (+/-, optional).");
				System.out.println("Valid dice types are d3, d4, d6, d8, d10, d12, d20, d100. Enter quit command to exit program.");
				input = scan.nextLine();
				if (input.equals("quit")) {
					break;                                                 		// recognize quit command
				}
				
				char[] inputarr = input.toCharArray();                      	// INPUT STRING PARSER
				System.out.println(inputarr);
				int i = 0;
				String multiplierS = "";                                   		// recognize multiplier
				while (inputarr[i] != 'd' && inputarr[i] != 'D') {
					multiplierS += inputarr[i];
					i++;
				}
				int multiplier = 1;
				if (!multiplierS.equals(null) && !multiplierS.equals("")) {
					multiplier = Integer.parseInt(multiplierS);
					if(multiplier<1) {
						throw new IllegalArgumentException();
					}
				}
				int inputLength = input.length();                      		    // recognize dice type
				String facesS = "";
				String modifierS = "";
				for (int j = i + 1; j < inputLength; j++) {
					if (inputarr[j] == '+') {
						for (int k = j + 1; k < inputLength; k++) {        		// recognize positive modifier
							modifierS += inputarr[k];
						}
						break;
					}
					if (inputarr[j] == '-') {
						for (int k = j; k < inputLength; k++) {            		// recognize negative modifier
							modifierS += inputarr[k];
						}
						break;
					}
					facesS += inputarr[j];
				}
				int faces = Integer.parseInt(facesS);
				int modifier = 0;
				if (!modifierS.equals(null) && !modifierS.equals("")) {
					modifier = Integer.parseInt(modifierS);
				}
				if(faces!=3&&faces!=4&&faces!=6&&faces!=8&&faces!=10&&faces!=12&&faces!=20&&faces!=100) {
					System.out.println("Incorrect dice type!");             	// check if dice type matches
					throw new IllegalArgumentException();
				}																// PARSING FINISHED
				
				Random rand = new Random();                                 	// Dice throw and output
				int result = rand.nextInt(faces)+1;
				System.out.println("Result: " + ((multiplier*result)+modifier));
			} catch (Exception e) {
				System.out.println("Incorrect format!");
				input = "";
				e.printStackTrace();
			}
		}
		System.out.println("Bye!");
	}
}