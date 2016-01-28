import java.util.List;
import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * This is the contract for all classes that wish to implement a user
 * interface.
 *
 * <p>
 *
 * This is done to allow alternative user interfaces to be easily
 * integrated into the code base.
 *
 * Currently I have only implemented a text user interface. However, a GUI
 * user interface could be introduced with minimum changes to the existing
 * code thanks to polymorphism and abstraction.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 * {@inheritDoc} TextUserInterface.java
 */
public interface UserInterface {

    public void clearScreen();
    public int getIntInput();
    public int getValidNumber(String prompt);
    public int getNumOfPegs();
    public int getNumOfColours();
    public String getGamePlayers();
    public String getPossibleColours();
    public String getUsersCode();
    public void clearScreenForPlayerTwo(ArrayList<String> possibleColours, int numOfPegs);
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
    public boolean shouldRestart();
    public void displayRows(String row);
    public void displayInvalidInput();
    public void displayGuess(ArrayList<String> guess);
    public void displayNumOfPegs(int numOfPegs);
    public void displayException(String exceptionType);
    public boolean shouldBeSaved(String guess);
    public void displayMastermind();
}
