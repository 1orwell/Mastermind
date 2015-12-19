import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Checker {
    public static List<Integer> check(ArrayList<String> code, ArrayList<String> guess) {
	    int index = 0;
		//ArrayList<String> code = Code.getCode();
		//ArrayList<String> guess = Guess.getGuess();
        List<Integer> indicators = new ArrayList<Integer>();
        boolean sameColourSamePos;
		for (int i=0; i<4; i++) {
            sameColourSamePos = false;
			if (code.contains(guess.get(i)) && code.get(i).equals(guess.get(i))) { // if right colour in right position
                indicators.add(2);
                code.set(i, "used"); // So that later, we give the indicators another 2 for this
            }
            else if (code.contains(guess.get(i)) && !code.get(i).equals(guess.get(i))) { // if right colour in wrong position
                int position = 0;
                for (int j=0; j<4; j++) {
                    if (code.get(j).equals(guess.get(i))) { // Find the position in the code where the guess is
                        position = j;
                        if (code.get(j).equals(guess.get(j))) { // If there is the correct colour in that position in the guess
                            sameColourSamePos = true;
                            indicators.add(2);
                            code.set(j, "used"); // So that later, we give the indicators another 2 for this
                            break;
                        }
                    }
                }
                if (sameColourSamePos == false) {
                    indicators.add(1);
                    code.set(position, "used");
                }
            }
            else {
                indicators.add(0);
            }
		}
        Collections.sort(indicators, Collections.reverseOrder());
        return indicators;
	}
}
