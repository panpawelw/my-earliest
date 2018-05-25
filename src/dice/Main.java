package dice;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String input = "";
		Scanner scan = new Scanner(System.in);
		while (true) {
			try {
				System.out.println(
						"Enter dice throw parameters using XdY+Z format. X = number of dice (>=1, optional), Y = number of faces, Z = modifier (+/-, optional).");
				System.out.println(
						"Valid dice types are d3, d4, d6, d8, d10, d12, d20, d100. Enter quit command to exit program.");
				input = scan.nextLine();
				if (input.equals("quit")) {
					break; // recognize quit command
				}
				int inputLength = input.length();
				char[] inputarr = input.toCharArray(); // INPUT STRING PARSING BEGINS
				System.out.println(inputarr);
				int i = 0;
				String multiplierS = ""; // recognize multiplier
				while (inputarr[i] != 'd' && inputarr[i] != 'D') {
					if (i > inputLength)
						throw new IllegalArgumentException();
					multiplierS += inputarr[i];
					i++;
				}
				int multiplier = 1;
				if (!multiplierS.equals(null) && !multiplierS.equals("")) {
					multiplier = Integer.parseInt(multiplierS);
					if (multiplier < 1) {
						throw new IllegalArgumentException();
					}
				}
				String facesS = ""; // recognize dice type
				String modifierS = "";
				for (int j = i + 1; j < inputLength; j++) {
					if (inputarr[j] == '+') {
						for (int k = j + 1; k < inputLength; k++) { // recognize positive modifier
							modifierS += inputarr[k];
						}
						break;
					}
					if (inputarr[j] == '-') {
						for (int k = j; k < inputLength; k++) { // recognize negative modifier
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
				if (faces != 3 && faces != 4 && faces != 6 && faces != 8 && faces != 10 && faces != 12 && faces != 20
						&& faces != 100) {
					throw new IllegalArgumentException();
				} // PARSING FINISHED

				Random rand = new Random(); // dice throw and result output
				long finalResult = 0;
				for (int l = 1; l < multiplier; l++) {
					int result = rand.nextInt(faces) + 1;
					finalResult = finalResult + result;
				}
				finalResult = finalResult + modifier;
				System.out.println("Result: " + finalResult);
			} catch (Exception e) {
				System.out.println("Incorrect format!");
				input = "";
			}
		}
		scan.close(); // close scanner and terminate
		System.out.println("Bye!");
	}
}