import java.util.ArrayList;
import java.util.List;

/**
 * Defines the properties and behaviour of AIInfo.
 *
 * @author Elise Ratliffe - enr24
 * @version 1.0
 * @release 31/12/15
 *
 */
public class AIInfo {

    private ArrayList<String> guess;
    private ArrayList<String> coloursInCode;
    private ArrayList<String> coloursNotChecked;
    private String colourGuessed;

    /**
     * Constructor. Sets the field values.
     *
     * @param guess
     *          The guess the program takes.
     * @param coloursInCode
     *          The colours the program has worked out is in the code.
     * @param coloursNotChecked
     *          The colours that could be in the code that the program
     *          has not yet checked.
     * @param colourGuessed
     *          The new colour that has been introduced to the guess this
     *          turn.
     */
    public AIInfo (ArrayList<String> guess, ArrayList<String> coloursInCode, ArrayList<String> coloursNotChecked, String colourGuessed) {
        this.guess = guess;
        this.coloursInCode = coloursInCode;
        this.coloursNotChecked = coloursNotChecked;
        this.colourGuessed = colourGuessed;
    }

    /**
     * @return  Guess made by program.
     */
    public ArrayList<String> getGuess() {
        return guess;
    }

    /**
     * @return  List of colours program has worked out is in the code.
     */
    public ArrayList<String> getColoursInCode() {
        return coloursInCode;
    }

    /**
     * @return  List of colours that could be in the code but have not yet
     *          been checked.
     */
    public ArrayList<String> getColoursNotChecked() {
        return coloursNotChecked;
    }

    /**
     * @return  New colour introduced to the guess this turn.
     */
    public String getColourGuessed() {
        return colourGuessed;
    }
}
