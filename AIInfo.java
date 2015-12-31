import java.util.ArrayList;
import java.util.List;

public class AIInfo {

    private ArrayList<String> guess;
    private ArrayList<String> coloursInCode;
    private ArrayList<String> coloursNotChecked;

    public AIInfo (ArrayList<String> guess, ArrayList<String> coloursInCode, ArrayList<String> coloursNotChecked) {
        this.guess = guess;
        this.coloursInCode = coloursInCode;
        this.coloursNotChecked = coloursNotChecked;
    }

    public ArrayList<String> getGuess() {
        return guess;
    }

    public ArrayList<String> getColoursInCode() {
        return coloursInCode;
    }

    public ArrayList<String> getColoursNotChecked() {
        return coloursNotChecked;
    }

}
