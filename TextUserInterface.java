import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains all the methods that allow the program to interact with the user.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 * @release 31/12/15
 * @See UserInterface.java
 */
public class TextUserInterface implements UserInterface {

    /**
     * Clears the terminal screen of previous text.
     */
    public void clearScreen() {
        System.out.print(Constants.ANSI_CLS + Constants.ANSI_HOME);
        System.out.flush();
    }

    /**
     * Get an integer input from the user.
     *
     * @return  The integer entered by the user.
     */
    public int getIntInput() {
        Scanner user_input = new Scanner(System.in);
        String strInput = user_input.next();
        int intInput = Integer.parseInt(strInput);
        return intInput;
    }

    /**
     * Finds out how long the user wants their code to be.
     *
     * @return  The length of code the user wants to guess.
     */
    public int getNumOfPegs() {
        System.out.println("The code can be between 3 and 8 pegs inclusive.");
        System.out.print("How many pegs would you like? --> ");
        int numOfPegs = getIntInput();
        return numOfPegs;
    }

    /**
     * Finds out how many colours the user wants to choose from when guessing
     * the code.
     *
     * @return  The number of possible colours the user wants to choose from.
     */
    public int getNumOfColours() {
        System.out.println("The code can have between 3 and 8 colour options inclusive.");
        System.out.print("How many colour options would you like? --> ");
        int numOfColours = getIntInput();
        return numOfColours;
    }

    /**
     * Finds out whether the user wants to play Computer v Compuer, Human v
     * Computer or Human v Human.
     *
     * @return  What player mode the user wants the game to be in.
     */
    public String getGamePlayers() {
        System.out.println("This game can be played in three ways.");
        System.out.println("\t 1) Computer codemaker and computer codebreaker.");
        System.out.println("\t 2) Computer codemaker and human codebreaker.");
        System.out.println("\t 3) Human codemaker and human codebreaker.");
        System.out.print("Which one would you like to play? Enter 1, 2 or 3 --> ");
        Scanner user_input = new Scanner(System.in);
        String players = user_input.next();
        return players;
    }

    /**
     * Finds out what colours the human codemaker wants to set as the list
     * of possible colours for the human codebreaker.
     *
     * @return  List of possible colours to choose from when the codebreaker
     *          is guessing.
     */
    public String getPossibleColours() {
        System.out.print("Player 1, please enter the possible colour options --> ");
        Scanner user_input = new Scanner(System.in);
        String possibleColours = user_input.nextLine();
        return possibleColours;
    }

    /**
     * Finds out the code the human codemaker is setting for the human
     * codebreaker.
     *
     * @return  Code set by human codemaker.
     */
    public String getUsersCode() {
        System.out.print("Player 1, please enter your code --> ");
        Scanner user_input = new Scanner(System.in);
        String code = user_input.nextLine();
        return code;
    }

    /**
     * Finds out the the screen should be cleared in preparation for the
     * human codebreaker to guess the code.
     *
     * @return  Whether the players are ready for the screen to be cleared.
     */
    public String clearScreenForPlayerTwo() {
        System.out.println("We will now clear the screen for player two.");
        System.out.print("Are you read? Type 'yes' or 'no' --> ");
        Scanner user_input = new Scanner(System.in);
        String clearScreen = user_input.next();
        return clearScreen;
    }

    /**
     * Tells the user they did not enter a valid input.
     */
    public void invalidInput() {
        System.out.println("You did not enter a valid input. Please try again.");
    }

    /**
     * Tells the user their input was out of range.
     */
    public void inputOutOfRange() {
        System.out.println("You entered a number outside the specified range.");
    }

    /**
     * Tells the codebreaker the list of possible colours to choose from
     * when guessing the code.
     *
     * @param possibleColours
     *          The list of possible colours that can be chosen from.
     */
    public void displayPossibleColours(ArrayList<String> possibleColours) {
        System.out.println("The colours you can choose from are: "+possibleColours);
    }

    /**
     * Tells the human codebreaker that they can save the game at any time.
     */
    public void displayCanSaveGame() {
        System.out.println("You can save the game any time by writing 'save'.");
    }

    /**
     * Gets the guess the human codebreaker is making for this turn.
     *
     * @param length
     *          The number of colours that should be in the guess.
     *
     * @return  The guess the human codebreaker decided on.
     */
    public ArrayList<String> getGuess(int length) {
        System.out.print("guess: ");
        String keyinput = "";
            InputStreamReader input = new InputStreamReader(System.in);
                BufferedReader readInput = new BufferedReader(input);
                    while (true) {
                        try {
                            keyinput = readInput.readLine();
                            if (keyinput == null) {
                                System.exit(0);
                            }
                            // addToGuess method interprets the user input
                            // and transforms into into a useable form.
                            ArrayList<String> guess = Mastermind.addToGuess(keyinput, length);
                            return guess;
                        }
                        catch (IOException e) {
                        }
                    }
    }

    /**
     * Prints out the indicators for the guess the user just made.
     *
     * <p>
     * Indicators let the user know how close their guess was to the code.
     *
     * @param indicators
     *          The indicators that have been calculated for that guess.
     */
    public void displayIndicators(List<Integer> indicators) {
        System.out.println(indicators);
    }

    /**
     * Displayed when the codebreaker uses up all their guesses, tells them
     * the actual code.
     *
     * @param code
     *          The code that the codebreaker was trying to guess.
     */
    public void displayYouLost(ArrayList<String> code) {
        System.out.println("Unlucky. The actual code was: "+code);
    }

    /**
     * Tells the user the game they saved has been cleared.
     */
    public void displayDataCleared() {
        System.out.println("We will now clear your saved data.");
        System.out.println("Data cleared.");
    }

    /**
     * Tells the user that their game has been saved.
     */
    public void displaySavingGame() {
        System.out.println("saved");
    }

    /**
     * Tells the codebreaker that they successfully guessed the code and that
     * the game is over.
     */
    public void displayYouWon() {
        System.out.println("Well done! Game over.");
    }

    /**
     * Asks the player if they want to restart their saved game.
     *
     * @return  String indicating whether player wants to restart their saved
     *          game.
     */
    public String askIfRestart() {
        System.out.println("Do you want to restart the saved game?");
        System.out.print("Type 'yes' or 'no' --> ");
        Scanner user_input = new Scanner(System.in);
        String userInput = user_input.next();
        return userInput;
    }

    /**
     * Tells the codebreaker a guess they have made and its associated
     * indicators.
     *
     * <p>
     * Called after the player restarts thier saved game.
     *
     * @param row
     *          String containing the players previous guess and its associatd
     *          indicators.
     */
    public void displayRows(String row) {
        System.out.println("Guess and indicators : "+ row);
    }

    /**
     * Tells the user they did not enter the correct input.
     */
    public void displayInvalidInput() {
        System.out.print("You did not write yes or no.");
    }

    /**
     * Displays the guess passed in.
     *
     * <p>
     * This method is used when there is a computer codebreaker so that the
     * human watching can see the computer's guesses.
     *
     * @param guess
     *          The guess made by the compuer.
     */
    public void displayGuess(ArrayList<String> guess) {
        System.out.println("guess: "+guess);
    }

    /**
     * Prints to the screen the length of the code.
     *
     * <p>
     * This is used when the player mode is either Computer v Computer or
     * Human v Human, as the codebreaker did not choose the length of the
     * code.
     *
     * @param numOfPegs
     *          The length of the code that has been made.
     */
    public void displayNumOfPegs(int numOfPegs) {
        System.out.println("The length of the code is "+numOfPegs);
    }

    /**
     * Tells the player there was an exception when the program tried to run.
     *
     * @param exceptionType
     *          The type of exception that was encountered.
     */
    public void displayException(String exceptionType) {
        System.out.println("There was "+exceptionType);
    }

}
