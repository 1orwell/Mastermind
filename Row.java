import java.util.ArrayList;
import java.util.List;

public class Row {

    private ArrayList<String> guess;
    private List<Integer> indicators;

    public Row(ArrayList<String> guess, List<Integer> indicators) {
        this.guess = guess;
        this.indicators = indicators;
    }

    public ArrayList<String> getGuess() {
        return guess;
    }

    public List<Integer> getIndicators() {
        return indicators;
    }
}
