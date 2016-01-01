import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls how the computer will make it's guess.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 */
public class AI {

    /**
     * Attempts to make an intelligent guess based off parameters passed in.
     *
     * @param possibleColours
     *          Possible colours to choose from when guessing code.
     * @param numOfPegs
     *          The length of the code.
     * @param coloursInCode
     *          The colours the program knows are in the code. If a colour
     *          occurs n times in the code, it occurs n times here.
     * @param coloursNotChecked
     *          List of colours in that could be in code that have not been
     *          checked.
     * @return  Object called info, of type AIInfo, this contains the guess
     *          the program has made, the colours the program has worked out
     *          is in the code so far, the possible colours that have not
     *          yet been checked and the colour that was added to the guess
     *          this turn.
     */
    public static AIInfo makeGuess(ArrayList<String> possibleColours, 
            int numOfPegs, ArrayList<String> coloursInCode, 
            ArrayList<String> coloursNotChecked) {

        ArrayList<String> guess = new ArrayList<String>();

        // Has the program worked out which colours are in the code.
        if (numOfPegs != coloursInCode.size()) {
            String colourGuessed = coloursNotChecked.get(0);
            // If the program has worked out some of the colours in the code
            // from a previous guess. Those colours are added to the new guess.
            if (!coloursInCode.isEmpty()) {
                for (int i=0; i<coloursInCode.size(); i++) {
                    guess.add(coloursInCode.get(i));
                }
            }
            // The rest of the colours in the guess are a single colour, taken
            // from the list of colours that have not yet been checked.
            for (int j=coloursInCode.size(); j<numOfPegs; j++) {
                guess.add(colourGuessed);
            }
            coloursNotChecked.remove(0);
            AIInfo info = new AIInfo(guess, coloursInCode, coloursNotChecked, colourGuessed);
            return info;
        }
        else {
            // Make sure that the program has indeed found all the colours in
            // the code, if not in the correct order.
            String lastColour = coloursInCode.get(numOfPegs-1);
            String colourGuessed = "no longer relevant";

            // Shift all the colours in the list of colours the program knows
            // is in the code to the right by one. The last colour gets sent
            // to the front.
            for (int j=numOfPegs-2; j>=0; j--) {
                coloursInCode.set(j+1, coloursInCode.get(j));
            }
            coloursInCode.set(0, lastColour);
            guess = coloursInCode;
            AIInfo info = new AIInfo(guess, coloursInCode, coloursNotChecked, colourGuessed);
            return info;
        }
    }
}
