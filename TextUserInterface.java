import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TextUserInterface implements UserInterface {

    public void clearScreen() {
        System.out.print(Constants.ANSI_CLS + Constants.ANSI_HOME);
        System.out.flush();
    }

    public int getIntInput() {
        Scanner user_input = new Scanner(System.in);
        String strInput = user_input.next();
        int intInput = Integer.parseInt(strInput);
        return intInput;
    }

    public int getNumOfPegs() {
        System.out.println("The code can be between 3 and 8 pegs inclusive.");
        System.out.print("How many pegs would you like? --> ");
        int numOfPegs = getIntInput();
        return numOfPegs;
    }

    public int getNumOfColours() {
        System.out.println("The code can have between 3 and 8 colour options inclusive.");
        System.out.print("How many colour options would you like? --> ");
        int numOfColours = getIntInput();
        return numOfColours;
    }

    public String getGamePlayers() {
        System.out.println("This game can be played in three ways.");
        System.out.println("Computer code maker and computer code breaker - CvC.");
        System.out.println("Computer code maker and human code breaker - CvH.");
        System.out.println("Human code maker and human code breaker - HvH");
        System.out.println("Which one would you like to play? Type 'CvC', 'CvH' or 'HvH'");
        Scanner user_input = new Scanner(System.in);
        String players = user_input.next();
        return players;
    }

    public String getPossibleColours() {
        System.out.print("Player 1, please enter the possible colour options --> ");
        Scanner user_input = new Scanner(System.in);
        String possibleColours = user_input.nextLine();
        return possibleColours;
    }

    public String getUsersCode() {
        System.out.print("Player 1, please enter your code --> ");
        Scanner user_input = new Scanner(System.in);
        String code = user_input.nextLine();
        return code;
    }

    public String clearScreenForPlayerTwo() {
        System.out.println("We will now clear the screen for player two.");
        System.out.println("Are you read? Type 'yes' or 'no' --> ");
        Scanner user_input = new Scanner(System.in);
        String clearScreen = user_input.next();
        return clearScreen;
    }

    public void invalidInput() {
        System.out.println("You did not enter a valid input. Please try again.");
    }

    public void inputOutOfRange() {
        System.out.println("You entered a number outside the specified range.");
    }

    public void displayPossibleColours(ArrayList<String> possibleColours) {
        System.out.println("The colours you can choose from are: "+possibleColours);
    }

    public void displayCanSaveGame() {
        System.out.println("You can save the game any time by writing 'save'.");
    }

    public ArrayList<String> getGuess(int numOfPegs) {
        System.out.print("guess: ");
        ArrayList<String> guess = Guess.getGuess(numOfPegs);
        return guess;
    }

    public void displayIndicators(List<Integer> indicators) {
        System.out.println(indicators);
    }

    public void displayYouLost(ArrayList<String> code) {
        System.out.println("Unlucky. The actual code was: "+code);
    }

    public void displayDataCleared() {
        System.out.println("We will now clear your saved data.");
        System.out.println("Data cleared.");
    }

    public void displaySavingGame() {
        System.out.println("saved");
    }

    public void displayYouWon() {
        System.out.println("Well done! Game over.");
    }

    public String askIfRestart() {
        System.out.println("Do you want to restart the saved game?");
        System.out.print("Type 'yes' or 'no' --> ");
        Scanner user_input = new Scanner(System.in);
        String restart = user_input.next();
        return restart;
    }

    public void displayRows(String row) {
        System.out.println("Guess and indicators : "+ row);
    }

    public void displayInvalidInput() {
        System.out.print("You did not write yes or no.");
    }

}
