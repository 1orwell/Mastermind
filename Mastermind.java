import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;

public class Mastermind {

    private static int numOfPegs = 0;
    private static int numOfColours = 0;
    private static ArrayList<String> code = new ArrayList<String>();
    private static ArrayList<String> guesses = new ArrayList<String>();
    private static ArrayList<String> possibleColours = new ArrayList<String>();
    private static ArrayList<Row> rows = new ArrayList<Row>();
    private static UserInterface ui = new TextUserInterface();
    private static boolean isSaved = false;
    private static boolean validPlayers = false;
    private static boolean playComputer = false;
    private static boolean endGame = false;
    private static String players = "";

	public static ArrayList<String> addToGuess(String keyinput, int length) {
		String[] splitinput = keyinput.split("\\s+");
		ArrayList<String> guess = new ArrayList<String>();

		for (int i=0; i<splitinput.length; i++) {

			if ((guess.size() + 1) < length+1) {
				String colour = splitinput[i];
				guess.add(colour);
			}

			else {
				break;
			}
		}

		return guess;
	}

    private static void initialiseCvH() {
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


    private static void initialiseHvH() {
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
                ui.displayNumOfPegs(numOfPegs);
            }
            else if (clearScreen.equals("no") || clearScreen.equals("No") || clearScreen.equals("n")) {

            }
            else {
                ui.invalidInput();
            }
        }
    }

    private static void initialiseCvC() {
        playComputer = true;
        validPlayers = true;
        // Create a list of colours the user can choose from.
        numOfColours = Code.getRandomInt(8-3+1)+3;
        numOfPegs = Code.getRandomInt(8-3+1)+3;

        possibleColours = Code.makeList(numOfColours);

        // generate code
        code = Code.getCode(numOfPegs, possibleColours);

        ui.displayPossibleColours(possibleColours);
        ui.displayNumOfPegs(numOfPegs);
    }

    private static void initialiseNewGame() {

        while (!validPlayers) {
            players = ui.getGamePlayers();

            if (players.equals("1")) {
                initialiseCvC();
            }

            else if (players.equals("2")) {
                initialiseCvH();
                ui.displayCanSaveGame();
            }

            else if (players.equals("3")){
                initialiseHvH();
                ui.displayCanSaveGame();
            }


            else {
                ui.invalidInput();
            }
        }
    }

    private static void playWithHuman(int plays) {
        for (int i=plays; i<numOfPegs+2; i++) {
            if (endGame == true) {
                break;
            }
            ArrayList<String> guess = ui.getGuess(numOfPegs);
            String guessString = Format.arrayListToString(guess);
            if (guessString.contains("save") || guessString.contains("Save")) {
                String playComputerStr = String.valueOf(playComputer);
                SavedGame.saveGame(playComputerStr, code, possibleColours, rows);
                ui.displaySavingGame();
                isSaved = true;
                i--;
            }
            else {
                analyseGuess(guess, guessString, i);
            }
        }
    }

    private static void playWithComputer(int plays) {
        ArrayList<String> guess = new ArrayList<String>();
        ArrayList<String> coloursInCode = new ArrayList<String>();
        ArrayList<String> coloursNotChecked = possibleColours;
        String colourGuessed;
        int numOfColoursKnown = 0;
        for (int i=plays; i<numOfPegs+2; i++) {
            if (endGame == true) {
                break;
            }
            AIInfo info = AI.makeGuess(possibleColours, numOfPegs, coloursInCode, coloursNotChecked);
            guess = info.getGuess();
            coloursInCode = info.getColoursInCode();
            coloursNotChecked = info.getColoursNotChecked();
            colourGuessed = info.getColourGuessed();
            List<Integer> indicators = Indicators.getIndicators(code, guess);
            if (coloursInCode.size() != numOfPegs) {
                for (int x=0; x<numOfColoursKnown; x++) {
                    indicators.set(x, 0);
                }
                if (indicators.contains(2) || indicators.contains(1)) {
                    for (int j=0; j<numOfPegs; j++) {
                        if (indicators.get(j) == 2 || indicators.get(j)==1) {
                            coloursInCode.add(numOfColoursKnown, colourGuessed);
                            numOfColoursKnown++;
                        }
                    }
                }
            }
            String guessString = Format.arrayListToString(guess);
            ui.displayGuess(guess);
            boolean endGame = analyseGuess(guess, guessString, i);
        }
    }

    private static boolean analyseGuess(ArrayList<String> guess, String guessString, int i) {
        if (i==numOfPegs+1 && !guess.equals(code)) {
            ui.displayYouLost(code);
            if (isSaved == true) {
                ui.displayDataCleared();
                SavedGame.clearFile();
            }
            endGame = true;
        }
        else if (guess.equals(code)) {
            ui.displayYouWon();
            if (isSaved == true) {
                ui.displayDataCleared();
                SavedGame.clearFile();
            }
            endGame = true;
        }
        else {
            List<Integer> indicators = Indicators.getIndicators(code, guess);
            ui.displayIndicators(indicators);
            Row row = new Row(guess, indicators);
            rows.add(row);
            guesses.add(guessString);
        }
        return endGame;
    }

    public static void main(String args[]) {
        ui.clearScreen();

        // First let's check if there is a saved game.
        ArrayList<String> currentGame = SavedGame.getCurrentGame();
        if (currentGame.isEmpty()) {
            initialiseNewGame();
            if (players.equals("1")) {
                playWithComputer(0);
            }
            else {
                playWithHuman(0);
            }
        }
        else {
            // There is a saved game.
            boolean inputValid = false;
            while (!inputValid) {
                // Ask user if they want to restart their saved game.
                String restart = ui.askIfRestart();
                if (restart.equals("yes") || restart.equals("Yes") || restart.equals("y")) {
                    // The user wants to load their saved game.
                    isSaved = true;
                    inputValid = true;
                    code = SavedGame.getCurrentCode();
                    possibleColours = SavedGame.getCurrentPossibleColours();
                    ArrayList<String> rowsArray = SavedGame.getCurrentRows();
                    for (String s : rowsArray) {
                        String[] array = s.split(" : ");
                        ArrayList<String> guess = new ArrayList<String>(Arrays.asList(array[0].split(" ")));
                        String guessString = array[0];
                        List<Integer> indicators = Indicators.getIndicators(code, guess);
                        Row row = new Row(guess, indicators);
                        rows.add(row);
                        guesses.add(guessString);
                    }
                    numOfPegs = code.size();
                    ui.displayPossibleColours(possibleColours);
                    for (int j=0; j<rowsArray.size(); j++) {
                        ui.displayRows(rowsArray.get(j));
                    }
                    if (SavedGame.getPlayComputer().equals("true")) {
                        playWithComputer(SavedGame.getCurrentGame().size()-2);
                    }
                    else {
                        playWithHuman(SavedGame.getCurrentGame().size()-2);
                    }
                }
                else if (restart.equals("no") || restart.equals("No") || restart.equals("n")) {
                    inputValid = true;
                    isSaved = false;
                    ui.displayDataCleared();
                    SavedGame.clearFile();
                    initialiseNewGame();
                    if (players.equals("CvC")) {
                        playWithComputer(0);
                    }
                    else {
                        playWithHuman(0);
                    }
                }
                else {
                    ui.displayInvalidInput();
                }
            }
        }
    }
}
