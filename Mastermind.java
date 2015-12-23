import java.util.Arrays;
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
    private static UserInterface ui = new TextUserInterface();
    private static boolean isSaved = false;

    public static void initialiseNewGame() {

        boolean validPlayers = false;
        while (!validPlayers) {
            String players = ui.getGamePlayers();
            if (players.equals("CvH")) {

                validPlayers = true;

                boolean numInputValid = false;
                while (!numInputValid) {
                    numOfPegs = ui.getNumOfPegs();
                    if (numOfPegs < Constants.MIN_NUM_OF_PEGS || numOfPegs > Constants.MAX_NUM_OF_PEGS) {
                        ui.inputOutOfRange();
                    }
                    else {
                        numInputValid = true;
                    }
                }

                numInputValid = false;
                while (!numInputValid) {
                    numOfColours = ui.getNumOfColours();
                    if (numOfColours < Constants.MIN_NUM_OF_COLOURS || numOfColours > Constants.MAX_NUM_OF_COLOURS) {
                        ui.inputOutOfRange();
                    }
                    else {
                        numInputValid = true;
                    }

                    // Create a list of colours the user can choose from.
                    possibleColours = Code.makeList(numOfColours);

                    // generate code
                    code = Code.getCode(numOfPegs, possibleColours);

                    ui.displayPossibleColours(possibleColours);
                }
            }

            else if (players.equals("HvH")){
                validPlayers = true;
                String possibleColoursString = ui.getPossibleColours();
                possibleColours = new ArrayList<String>(Arrays.asList(possibleColoursString.split(" ")));
                numOfColours = possibleColours.size();
                String codeString = ui.getUsersCode();
                code = new ArrayList<String>(Arrays.asList(codeString.split(" ")));
                numOfPegs = code.size();
                boolean readyToClear = false;
                while (!readyToClear) {
                    String clearScreen = ui.clearScreenForPlayerTwo();
                    if (clearScreen.equals("yes") || clearScreen.equals("Yes") || clearScreen.equals("y")) {
                        readyToClear = true;
                        ui.clearScreen();
                        ui.displayPossibleColours(possibleColours);
                    }
                    else if (clearScreen.equals("no") || clearScreen.equals("No") || clearScreen.equals("n")) {

                    }
                    else {
                        ui.invalidInput();
                    }
                }
            }

            else if (players.equals("CvC")) {
                //validPlayers = true;
                System.out.println("Sorry, we don't have this functionality yet.");
            }

            else {
                ui.invalidInput();
            }
        }
        ui.displayCanSaveGame();
    }

    public static void playGame(int plays) {
        for (int i=plays; i<numOfPegs+2; i++) {
            ArrayList<String> guess = ui.getGuess(numOfPegs);
            String guessString = Format.arrayListToString(guess);
            if (i==numOfPegs+1) {
                ui.displayYouLost(code);
                if (isSaved == true) {
                    ui.displayDataCleared();
                    SavedGame.clearFile();
                }
                break;
            }
            if (guessString.contains("save") || guessString.contains("Save")) {
                SavedGame.saveGame(code, rows, possibleColours);
                ui.displaySavingGame();
                isSaved = true;
                i--;
            }
            else if (guess.equals(code)) {
                ui.displayYouWon();
                if (isSaved == true) {
                    ui.displayDataCleared();
                    SavedGame.clearFile();
                }
                break;
            }
            else {
                List<Integer> indicators = Indicators.getIndicators(code, guess);
                ui.displayIndicators(indicators);
                Row row = new Row(guess, indicators);
                rows.add(row);
                guesses.add(guessString);
            }
        }
    }

    public static void main(String args[]) {
        ui.clearScreen();

        // First let's check if there is a saved game.
        ArrayList<String> currentGame = SavedGame.getCurrentGame();
        if (currentGame.isEmpty()) {
            initialiseNewGame();
            playGame(0);
        }
        else {
            // There is a saved game.
            boolean inputValid = false;
            while (!inputValid) {
                // Ask user if they want to restart their saved game.
                String restart = ui.askIfRestart();
                if (restart.equals("yes") || restart.equals("Yes") || restart.equals("y")) {
                    // The user wants to load their saved game.
                    inputValid = true;
                    code = SavedGame.getCurrentCode();
                    possibleColours = SavedGame.getCurrentPossibleColours();
                    ArrayList<String> rowsArray = SavedGame.getCurrentRows();
                    numOfPegs = code.size();
                    ui.displayPossibleColours(possibleColours);
                    for (int j=0; j<rowsArray.size(); j++) {
                        ui.displayRows(rowsArray.get(j));
                    }
                    playGame(SavedGame.getCurrentGame().size()-2);
                }
                else if (restart.equals("no") || restart.equals("No") || restart.equals("n")) {
                    inputValid = true;
                    ui.displayDataCleared();
                    SavedGame.clearFile();
                    initialiseNewGame();
                    playGame(0);
                }
                else {
                    ui.displayInvalidInput();
                }
            }
        }
    }
}
