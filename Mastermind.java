import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Brings together all the other classes and their methods, allowing the game
 * to be played.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 */
public class Mastermind {

    private static int numOfPegs = 0;
    private static int numOfColours = 0;
    private static ArrayList<String> code = new ArrayList<String>();
    private static ArrayList<String> guesses = new ArrayList<String>();
    private static ArrayList<String> possibleColours = new ArrayList<String>();
    private static ArrayList<Row> rows = new ArrayList<Row>();

    // Note that ui could be set to a GUI implementation of UserInterface
    // at runtime. For example on a command line switch. However, I didn't
    // have time to complete this.
    private static UserInterface ui = new TextUserInterface();

    private static boolean isSaved = false;
    private static boolean validPlayers = false;
    private static boolean playComputer = false;
    private static boolean endGame = false;
    private static String players = "";

    /**
     * Turns the guess inputed into a form more easily manipulated in the
     * program.
     *
     * @param keyinput
     *          The guess inputed by the user
     * @param length
     *          The length of the guess the player inputed.
     * @return  The guess the user inputed in the form of an ArrayList<String>
     */
	public static ArrayList<String> addToGuess(String keyinput, int length) {
        // Use regex to split string inputed on spaces.
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

    /**
     * Finds out what player mode the user wishes the game to be in.
     */
    private static void initialiseNewGame() {

        while (!validPlayers) {
            ui.displayMastermind();
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

    /**
     * Creates a code and list of possible colours to choose when guessing the
     * code so the game can be played.
     *
     * <p>
     * Asks the player how many colour option they want and the length of the
     * code they want. The list of colour options and the code are then made.
     * Doesn't return anything as the code and possibleColours are class
     * variables.
     */
    private static void initialiseCvH() {
        validPlayers = true;

        // Ask user how many pegs they wish to use (3-8 inclusive)
        numOfPegs = ui.getNumOfPegs();

        // Ask user how many colours they wish to use (3-8 inclusive)
        numOfColours = ui.getNumOfColours();
        possibleColours = Code.makeList(numOfColours);
        code = Code.getCode(numOfPegs, possibleColours);
        ui.displayPossibleColours(possibleColours);
    }


    /**
     * Gets the code and list of possible colours from the human codemaker so
     * the game can be played.
     *
     * <p>
     * Once the code and possible colours have been entered, the codemaker
     * is asked if they are ready for the screen to be cleared for the
     * codebreaker.
     */
    private static void initialiseHvH() {
        validPlayers = true;

        String possibleColoursString = ui.getPossibleColours();
        possibleColours = new ArrayList<String>(Arrays.asList(possibleColoursString.split(" ")));

        numOfColours = possibleColours.size();
        String codeString = ui.getUsersCode();
        code = new ArrayList<String>(Arrays.asList(codeString.split(" ")));
        numOfPegs = code.size();
        ui.clearScreenForPlayerTwo(possibleColours, numOfPegs);
    }

    /**
     * Creates a pseudorandom code and list of possible colours to choose from
     * when guessing the code.
     *
     * <p>
     * The code length is randomly generated here, as is the length of the
     * list of possible colours.
     */
    private static void initialiseCvC() {
        playComputer = true;
        validPlayers = true;
        // Create a list of colours the user can choose from.
        numOfColours = Code.getRandomInt(8-3+1)+3;
        numOfPegs = Code.getRandomInt(8-3+1)+3;

        possibleColours = Code.makeList(numOfColours);

        code = Code.getCode(numOfPegs, possibleColours);

        ui.displayPossibleColours(possibleColours);
        ui.displayNumOfPegs(numOfPegs);
    }

    /**
     * Keeps asking human codebreaker for their new guess until they either
     * correctly guess the code, or run out of guesses.
     *
     * <p>
     * This method also deals with if the player saves the game. The analysing
     * of the guess, i.e. getting the indicators, is handled in analyseGuess
     *
     * @param plays
     *          This is the number of guesses the player has already used.
     *          This is needed as the game might be saved and restarted,
     *          meaning some of the guesses will have already been used.
     */
    private static void playWithHuman(int plays) {
        for (int i=plays; i<numOfPegs+2; i++) {
            if (endGame) {
                break;
            }
            ArrayList<String> guess = new ArrayList<String>();
            boolean validInput = false;
            while (!validInput)
            {
                guess = ui.getGuess(numOfPegs);
                if (guess.size() != numOfPegs)
                {
                    ui.invalidInput();
                }
                else
                {
                    validInput = true;
                }
            }
            String guessString = Format.arrayListToString(guess);
            boolean isSaved = ui.shouldBeSaved(guessString);
            if (isSaved == true) {
                String playComputerStr = String.valueOf(playComputer);
                SavedGame.saveGame(playComputerStr, code, possibleColours, rows);
                ui.displaySavingGame();
                isSaved = true;

                // Don't treat the user entering "save" as a turn.
                i--;
            }
            else {
                analyseGuess(guess, guessString, i);
            }
        }
    }

    /**
     * Keeps getting the AI to make a guess until it either correctly guesses
     * the code or runs out of guesses.
     *
     * <p>
     * Unlike above, no plays is needed as their is no option to save the
     * game, the computer uses all its guesses in one go.
     */
    private static void playWithComputer() {
        ArrayList<String> guess = new ArrayList<String>();
        ArrayList<String> coloursInCode = new ArrayList<String>();
        ArrayList<String> coloursNotChecked = possibleColours;
        String colourGuessed;
        int numOfColoursKnown = 0;
        for (int i=0; i<numOfPegs+2; i++) {
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
                // If some of the colours are already known, they will be in
                // the guess. So they will make either a 1 or 2 appear in the
                // indicators. These should be converted to 0s so the number
                // of 1s and 2s created by the new colour not previously
                // checked can be counted. The indicators are stored in order
                // from highest to lowest, so iterate through indicators from
                // the beginning.
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
            analyseGuess(guess, guessString, i);
        }
    }

    /**
     * Deals with whether the guess is correct, or if the codebreaker has ran
     * out of guesses - if not, it gets the indicators for their guess and
     * stores the information in rows.
     *
     * <p>
     * It also return a boolean, letting the method that called it know
     * whether the game has finished or not.
     *
     * @param guess
     *          The guess the codebreaker made this turn.
     * @param guessString
     *          The codebreaker guess in String form
     * @param plays
     *          The number of turn the codebreaker has had to crack the code.
     */
    private static void analyseGuess(ArrayList<String> guess, String guessString, int plays) {
        // If the codebreaker has had all their guesses and the last one
        // isn't correct.
        if (plays==numOfPegs+1 && !guess.equals(code)) {
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
            // Instantiate an new row of type Row that contains the guess and
            // its indicators, store this row in ArrayList<Row> rows. This is
            // done so the guesses and indicators can be saved and restored.
            Row row = new Row(guess, indicators);
            rows.add(row);
            guesses.add(guessString);
        }
    }

    /**
     * Main method brings all the other methods together in the correct order,
     * allowing the game to be played.
     */
    public static void main(String args[]) {

        // This is where ui could be set to a GUI implementation of
        // UserInterface based on a command line argument.
        ui.clearScreen();

        // First checks if there is a saved game.
        ArrayList<String> currentGame = SavedGame.getCurrentGame();
        if (currentGame.isEmpty()) {
            initialiseNewGame();
            if (players.equals("1")) {
                playWithComputer();
            }
            else {
                playWithHuman(0);
            }
        }
        else {
            // There is a saved game.
            // Ask user if they want to restart their saved game.
            boolean restart = ui.shouldRestart();
            if (restart == true) {
                // The user wants to load their saved game.
                isSaved = true;
                code = SavedGame.getCurrentCode();
                possibleColours = SavedGame.getCurrentPossibleColours();
                ArrayList<String> rowsArray = SavedGame.getCurrentRows();
                // Retrieve code, possible colours and rows from the
                // saved game.
                // The row are converted into a more useful form.
                for (String s : rowsArray) {
                    String[] array = s.split(" : ");
                    // The players guess will be the first element of array
                    // Need guess in String and ArrayList<String> form.
                    String guessString = array[0];
                    ArrayList<String> guess = new ArrayList<String>(Arrays.asList(array[0].split(" ")));
                    List<Integer> indicators = Indicators.getIndicators(code, guess);
                    Row row = new Row(guess, indicators);
                    // Add any guesses and indicators from the saved game
                    // to rows. This is so the game can be saved multiple
                    // times.
                    rows.add(row);
                    guesses.add(guessString);
                }
                numOfPegs = code.size();
                ui.displayPossibleColours(possibleColours);
                // Print to the screen all the players saved guesses and
                // their associated indicators.
                for (int j=0; j<rowsArray.size(); j++) {
                    ui.displayRows(rowsArray.get(j));
                }
                // Game can only be saved when it's being played with a
                // human, so we don't have to consider playWithComputer
                playWithHuman(SavedGame.getCurrentGame().size()-2);
            }
            else {
                // Player doesn't want to restore saved game, clear the
                // saved game.
                isSaved = false;
                ui.displayDataCleared();
                SavedGame.clearFile();
                initialiseNewGame();
                if (players.equals("1")) {
                    playWithComputer();
                }
                else {
                    playWithHuman(0);
                }
            }
        }
    }
}
