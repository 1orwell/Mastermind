import java.util.List;
import java.util.ArrayList;

public interface UserInterface {

    public void clearScreen();
    public int getIntInput();
    public int getNumOfPegs();
    public int getNumOfColours();
    public String getGamePlayers();
    public String getPossibleColours();
    public String getUsersCode();
    public String clearScreenForPlayerTwo();
    public void invalidInput();
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
    public void displayRows(String row);
    public void displayInvalidInput();

}
