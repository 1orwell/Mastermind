import java.util.ArrayList;
import java.util.List;

/**
 * Defines the properties and behaviour of a row.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 */
public class Row {

    private ArrayList<String> guess;
    private List<Integer> indicators;

    /**
     * Constructor. Sets the field values.
     *
     * @param guess
     *          Guess made by player.
     * @param indicators
     *          Indicators calculated by comparing guess and code.
     */
    public Row(ArrayList<String> guess, List<Integer> indicators) {
        this.guess = guess;
        this.indicators = indicators;
    }

    /**
     * @return  Guess made by player.
     */
    public ArrayList<String> getGuess() {
        return guess;
    }

    /**
     * @return Indicators calculated by comparing guess and code.
     */
    public List<Integer> getIndicators() {
        return indicators;
    }
}
