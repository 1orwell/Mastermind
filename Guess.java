import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Guess {

	public static ArrayList<String> getInput(int length) {
		String keyinput = "";
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader readInput = new BufferedReader(input);
		while (true) {
			try {
				keyinput = readInput.readLine();
				if (keyinput == null) {
					System.exit(0);
				}
				ArrayList<String> guess = addToGuess(keyinput, length);
				return guess;
			}
			catch (IOException e) {
			}
		}

	}

	public static ArrayList<String> addToGuess(String keyinput, int length) {
		String[] splitinput = keyinput.split("\\s+");
		ArrayList<String> guess = new ArrayList<String>();

		for (int i=0; i<splitinput.length; i++) {

			if ((guess.size() + 1) < length+1) {
				String colour = splitinput[i];
				guess.add(colour);
			}

			else {
				break;
			}
		}

		return guess;
	}

	public static ArrayList<String> getGuess(int length) {
		ArrayList<String> guess = getInput(length);
		return guess;
	}
}

