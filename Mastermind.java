import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Mastermind {

    private static int numOfPegs = 0;
    private static int numOfColours = 0;
    private static ArrayList<String> code = new ArrayList<String>();
    private static ArrayList<String> guesses = new ArrayList<String>();
    private static ArrayList<String> possibleColours = new ArrayList<String>();
    private static ArrayList<Row> rows = new ArrayList<Row>();
    private static UserInterface UI = new TextUserInterface();
    private static boolean everBeenSaved = false;

    public static void startNewGame() {
        UI.clearScreen();
        boolean inputValid = false;
        while (!inputValid) {
            numOfPegs = UI.getNumOfPegs();
            if (numOfPegs < Constants.MIN_NUM_OF_PEGS || numOfPegs > Constants.MAX_NUM_OF_PEGS) {
                UI.inputOutOfRange();
            }
            else {
                inputValid = true;
            }
        }
        inputValid = false;
        while (!inputValid) {
            numOfColours = UI.getNumOfColours();
            if (numOfColours < Constants.MIN_NUM_OF_COLOURS || numOfColours > Constants.MAX_NUM_OF_COLOURS) {
                UI.inputOutOfRange();
            }
            else
                inputValid = true;
        }

        possibleColours = Code.makeList(numOfColours);
        code = Code.getCode(numOfPegs, possibleColours);
        UI.tellUserPossibleColours(possibleColours);
        UI.displayCanSaveGame();
    }

    public static void playGame(int plays) {
        for (int i=plays; i<numOfPegs+2; i++) {
            ArrayList<String> guess = UI.getGuess(numOfPegs);
            String guessString = Format.arrayListToString(guess);
            if (i==numOfPegs+1) {
                UI.displayYouLost(code);
                if (everBeenSaved == true) {
                    UI.displayDataCleared();
                    SavedGame.clearFile();
                }
                break;
            }
            if (guessString.contains("save") || guessString.contains("Save")) {
                SavedGame.saveGame(code, rows, possibleColours);
                UI.displaySavingGame();
                everBeenSaved = true;
                i--;
            }
            else if (guess.equals(code)) {
                UI.displayYouWon();
                if (everBeenSaved == true) {
                    UI.displayDataCleared();
                    SavedGame.clearFile();
                }
                break;
            }
            else {
                List<Integer> indicators = Indicators.getIndicators(code, guess);
                UI.displayIndicators(indicators);
                Row row = new Row(guess, indicators);
                rows.add(row);
                guesses.add(guessString);
            }
        }
    }

    public static void main(String args[]) {
        ArrayList<String> currentGame = SavedGame.getCurrentGame();
        if (currentGame.isEmpty()) {
            startNewGame();
            playGame(0);
        }
        else {
            boolean inputValid = false;
            while (!inputValid) {
                String restart = UI.askIfRestart();
                if (restart.equals("yes") || restart.equals("Yes") || restart.equals("y")) {
                    inputValid = true;
                    code = SavedGame.getCurrentCode();
                    possibleColours = SavedGame.getCurrentPossibleColours();
                    ArrayList<String> rowsArray = SavedGame.getCurrentRows();
                    numOfPegs = code.size();
                    UI.displayPossibleColours(possibleColours);
                    for (int j=0; j<rowsArray.size(); j++) {
                        UI.displayRows(rowsArray, j);
                    }
                    playGame(SavedGame.getCurrentGame().size()-2);
                }
                else if (restart.equals("no") || restart.equals("No") || restart.equals("n")) {
                    inputValid = true;
                    UI.displayDataCleared();
                    SavedGame.clearFile();
                    startNewGame();
                    playGame(0);
                }
                else {
                    UI.displayInvalidInput();
                }
            }
        }
    }
}
