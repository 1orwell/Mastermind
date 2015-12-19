import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Guess {

	public static ArrayList<String> getInput() {
		String keyinput = "";
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader readInput = new BufferedReader(input);
		while (true) {
			try {
				keyinput = readInput.readLine();
				if (keyinput == null) {
					System.exit(0);
				}
				ArrayList<String> guess = addToGuess(keyinput);
				return guess;
			}
			catch (IOException e) {
			}
		}

	}	

	public static ArrayList<String> addToGuess(String keyinput) {
		String[] splitinput = keyinput.split("\\s+");
		ArrayList<String> guess = new ArrayList<String>();

		for (int i=0; i<splitinput.length; i++) {

			if ((guess.size() + 1) < 9) {
				String colour = splitinput[i];
				guess.add(colour);
			}

			else {
				System.out.println("First 8 colours you entered have been used.");
				break;
			}
		}

		return guess;
	}

	public static ArrayList<String> getGuess() {
		ArrayList<String> guess = getInput();
		return guess;
	}
}

	
