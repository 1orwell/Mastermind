import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class AI {

    public static AIInfo makeGuess(ArrayList<String> possibleColours, int numOfPegs, ArrayList<String> coloursInCode, ArrayList<String> coloursNotChecked) {
        ArrayList<String> guess = new ArrayList<String>();

        if (numOfPegs != coloursInCode.size()) {
            String colour = coloursNotChecked.get(0);
            for (int i=0; i<numOfPegs; i++) {
                guess.add(colour);
            }
            coloursNotChecked.remove(0);
            AIInfo info = new AIInfo(guess, coloursInCode, coloursNotChecked);
            return info;
        }
        else {
            assert numOfPegs == coloursInCode.size();
            String lastColour = coloursInCode.get(numOfPegs-1);
            for (int j=numOfPegs-2; j>=0; j--) {
                coloursInCode.set(j+1, coloursInCode.get(j));
            }
            coloursInCode.set(0, lastColour);
            guess = coloursInCode;
            AIInfo info = new AIInfo(guess, coloursInCode, coloursNotChecked);
            return info;
        }
    }
}
