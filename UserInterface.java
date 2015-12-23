import java.util.List;
import java.util.ArrayList;

public interface UserInterface {

    public void clearScreen();
    public int getIntInput();
    public int getNumOfPegs();
    public int getNumOfColours();
    public void inputOutOfRange();
    public void displayPossibleColours(ArrayList<String> possibleColours);
    public void displayCanSaveGame();
    public ArrayList<String> getGuess(int numOfPegs);
    public void displayIndicators(List<Integer> indicators);
    public void displayYouLost(ArrayList<String> code);
    public void displayDataCleared();
    public void displaySavingGame();
    public void displayYouWon();
    public String askIfRestart();
    public void displayRows(ArrayList<String> rowsArray, int i);
    public void dispalyInvalidInput();

}
