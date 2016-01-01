import java.util.List;
import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Holds all the methods that should be made concrete in the class that
 * implements this interface.
 *
 * <p>
 * This is done so that the code is more easily extendable to allow a GUI.
 * If the player wanted to use the GUI interface, Mastermind.java would
 * simply instantiate the GUIUserInterface class rather than the
 * TextUserInterface class. Both classes would extend this interface. No class
 * would have to be changed.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 * @release 31/12/15
 */
public interface UserInterface {

    public void clearScreen();
    public int getIntInput();
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
