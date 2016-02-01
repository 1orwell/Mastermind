import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains all the methods that allow the program to interact with the user.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
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

    public int getValidNumber(String prompt)
    {

        ArrayList<String> possibleInputs = new ArrayList<String>();
        possibleInputs.add("3");
        possibleInputs.add("4");
        possibleInputs.add("5");
        possibleInputs.add("6");
        possibleInputs.add("7");
        possibleInputs.add("8");
        System.out.print(prompt);
        boolean inputValid = false;
        int numEntered = 0;
        while (!inputValid)
        {
            Scanner user_input = new Scanner(System.in);
            String strInput = user_input.next();
            if (possibleInputs.contains(strInput))
            {
                inputValid = true;
                numEntered = Integer.parseInt(strInput);
            }
            else
            {
                System.out.print("\nYou did not enter a valid input. Try again --> ");
            }
        }
        return numEntered;
    }

    /**
     * Finds out how long the user wants their code to be.
     *
     * @return  The length of code the user wants to guess.
     */
    public int getNumOfPegs() {
        UserInterface ui = new TextUserInterface();
        int numOfPegs = ui.getValidNumber("\nHow many pegs would you like (3-8)? --> ");
        return numOfPegs;
    }

    /**
     * Finds out how many colours the user wants to choose from when guessing
     * the code.
     *
     * @return  The number of possible colours the user wants to choose from.
     */
    public int getNumOfColours() {
        UserInterface ui = new TextUserInterface();
        int numOfColours = ui.getValidNumber("\nHow many colour options would you like (3-8)? --> ");
        return numOfColours;
    }

    /**
     * Finds out whether the user wants to play Computer v Computer, Human v
     * Computer or Human v Human.
     *
     * @return  What player mode the user wants the game to be in.
     */
    public String getGamePlayers() {
        UserInterface ui = new TextUserInterface();
        String userInput = "";
        boolean inputValid = false;
        while (!inputValid) {
            System.out.println("\nThis game can be played in three ways: \n");
            System.out.println("\t 1) Computer codemaker and computer codebreaker.");
            System.out.println("\t 2) Computer codemaker and human codebreaker.");
            System.out.println("\t 3) Human codemaker and human codebreaker.");
            System.out.print("\nWhich one would you like to play? Enter 1, 2 or 3 --> ");
            Scanner user_input = new Scanner(System.in);
            String players = user_input.next();
            if (players.equals("1")) {
                inputValid = true;
                userInput = "1";
            }

            else if (players.equals("2")) {
                inputValid = true;
                userInput = "2";
            }

            else if (players.equals("3")) {
                inputValid = true;
                userInput = "3";
            }

            else {
                ui.displayInvalidInput();
            }
        }
        return userInput;
    }

    /**
     * Finds out what colours the human codemaker wants to set as the list
     * of possible colours for the human codebreaker.
     *
     * @return  List of possible colours to choose from when the codebreaker
     *          is guessing.
     */
    public String getPossibleColours() {
        String possibleColours = "";
        boolean validInput = false;
        while (!validInput)
        {
            System.out.print("\nPlayer 1, please enter the possible colour options --> ");
            Scanner user_input = new Scanner(System.in);
            possibleColours = user_input.nextLine();
            String[] splitInput = possibleColours.split("\\s+");
            ArrayList<Integer> possibleLengths = new ArrayList<Integer>();
            possibleLengths.add(3);
            possibleLengths.add(4);
            possibleLengths.add(5);
            possibleLengths.add(6);
            possibleLengths.add(7);
            possibleLengths.add(8);
            if (possibleLengths.contains(splitInput.length))
            {
                validInput = true;
            }
            else
            {
                System.out.println("You must enter between 3 and 8 colours.");
            }
        }
        return possibleColours;
    }

    /**
     * Finds out the code the human codemaker is setting for the human
     * codebreaker.
     *
     * @return  Code set by human codemaker.
     */
    public String getUsersCode() {
        System.out.print("\nPlayer 1, please enter your code --> ");
        Scanner user_input = new Scanner(System.in);
        String code = user_input.nextLine();
        return code;
    }

    /**
     * Finds out if players ready to clear screen for human codebreaker.
     *
     * <p>
     * Clears the screen if the human codebreaker is ready to guess the code.
     */
    public void clearScreenForPlayerTwo(ArrayList<String> possibleColours, int numOfPegs) {
        UserInterface ui = new TextUserInterface();
        boolean readyToClear = false;
        while (!readyToClear) {
            System.out.println("\nWe will now clear the screen for player two.");
            System.out.print("\nAre you read? Type 'yes' or 'no' --> ");
            Scanner user_input = new Scanner(System.in);
            String clearScreen = user_input.next();
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
        //return clearScreen;
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
        System.out.println("\nThe colours you can choose from are: "+possibleColours + "\n");
    }

    /**
     * Tells the human codebreaker that they can save the game at any time.
     */
    public void displayCanSaveGame() {
        System.out.println("You can save the game any time by writing 'save'.\n");
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
                            // and transforms into into a usable form.
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
        System.out.println(indicators+"\n");
    }

    /**
     * Displayed when the codebreaker uses up all their guesses, tells them
     * the actual code.
     *
     * @param code
     *          The code that the codebreaker was trying to guess.
     */
    public void displayYouLost(ArrayList<String> code) {
        System.out.println("Unlucky. The actual code was: "+code+"\n");
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
        System.out.println("\nWell done! Game over.\n");
    }

    /**
     * Asks the player if they want to restart their saved game.
     *
     * @return  String indicating whether player wants to restart their saved
     *          game.
     */
    public boolean shouldRestart() {
        UserInterface ui = new TextUserInterface();
        boolean restart = false;
        boolean inputValid = false;
        while (!inputValid) {
            System.out.println("Do you want to restart the saved game?");
            System.out.print("Type 'yes' or 'no' --> ");
            Scanner user_input = new Scanner(System.in);
            String userInput = user_input.next();
            if (userInput.contains("yes") || userInput.contains("Yes")) {
                inputValid = true;
                restart = true;
            }
            else if (userInput.contains("no") || userInput.contains("No")) {
                inputValid = true;
                restart = false;
            }
            else {
                ui.displayInvalidInput();
            }
        }
        return restart;
    }

    /**
     * Tells the codebreaker a guess they have made and its associated
     * indicators.
     *
     * <p>
     * Called after the player restarts their saved game.
     *
     * @param row
     *          String containing the players previous guess and its associated
     *          indicators.
     */
    public void displayRows(String row) {
        System.out.println("Guess and indicators : "+ row);
    }

    /**
     * Tells the user they did not enter the correct input.
     */
    public void displayInvalidInput() {
        System.out.println("\nYou did not enter a valid input.\n");
    }

    /**
     * Displays the guess passed in.
     *
     * <p>
     * This method is used when there is a computer codebreaker so that the
     * human watching can see the computer's guesses.
     *
     * @param guess
     *          The guess made by the computer.
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
        System.out.println("The length of the code is "+numOfPegs+"\n");
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

    public boolean shouldBeSaved(String guess) {
        UserInterface ui = new TextUserInterface();
        if (guess.contains("save") || guess.contains("Save")) {
            return true;
        }
        else {
            return false;
        }
    }

    public void displayMastermind() {
        UserInterface ui = new TextUserInterface();
        String fileName = "mastermindASCII.txt";

        String line = null;
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                arrayList.add(line);
            }
            br.close();
        }

        catch (FileNotFoundException ex) {
            ui.displayException("a FileNotFoundException");
            System.exit(0);
        }

        catch (IOException e) {
            ui.displayException("an IOException");
            System.exit(0);
        }

        for (int i=0; i<arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }

    }

}
