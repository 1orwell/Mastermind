import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Holds the method that compares the guess and the code, calcualtes and
 * returns the indicators.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 * @release 31/12/15
 */
public class Indicators {

    /**
     * Compares the guess and code, calculates and returns the indicators.
     *
     * <p>
     * The indicators inform the user how close their guess was to the code.
     *
     * @param code
     *          The code that the player is trying to guess.
     * @param guess
     *          The guess the player made this turn.
     * @return  List of the indicators for this turn.
     */
    public static List<Integer> getIndicators(ArrayList<String> code, ArrayList<String> guess) {
        int length = guess.size();
        ArrayList<String> codeCopy = new ArrayList<String>(code);
	    int index = 0;
        List<Integer> indicators = new ArrayList<Integer>();
        boolean sameColourSamePos;
		for (int i=0; i<length; i++) {
            sameColourSamePos = false;
            // If the colour in guess at position i is the same as the colour
            // in code at position i, add 2 to the indicators.
			if (code.contains(guess.get(i)) && code.get(i).equals(guess.get(i))) { // if right colour in right position
                indicators.add(2);
                // Edit the code so the colour is not used twice.
                code.set(i, "used");
            }
            // If the colour at position i in the guess is in the code, but not
            // in the correct position.
            else if (code.contains(guess.get(i)) && !code.get(i).equals(guess.get(i))) { // if right colour in wrong position
                int position = 0;
                // Find the colour in the code that matches with the colour
                // at position i in the guess. This position is j.
                for (int j=0; j<length; j++) {
                    if (code.get(j).equals(guess.get(i))) {
                        position = j;
                        // Look at the colour at position j in the guess. If
                        // it is the same colour as the colour at position
                        // j in the code, add 2 to the indicators.
                        if (code.get(j).equals(guess.get(j))) {
                            sameColourSamePos = true;
                            indicators.add(2);
                            code.set(j, "used");
                            break;
                        }
                    }
                }
                // The colour at position j in the guess is not the same as
                // the colour in position i in the guess. Add 1 to the
                // indicators.
                if (sameColourSamePos == false) {
                    indicators.add(1);
                    code.set(position, "used");
                }
            }
            // If the colour at position i in the guess does not occur in the
            // code, add 0 to the indicators.
            else {
                indicators.add(0);
            }
		}

        for (int k=0; k<length; k++) {
            code.set(k, codeCopy.get(k));
        }

        // Sorts the indicators, largest to smallest.
        Collections.sort(indicators, Collections.reverseOrder());
        return indicators;
	}
}
