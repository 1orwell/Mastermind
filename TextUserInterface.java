import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TextUserInterface implements UserInterface {

    public void clearScreen() {
        System.out.println(Constants.ANSI_CLS + Constants.ANSI_HOME);
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

    public void displayRows(ArrayList<String> rowsArray, int i) {
        System.out.println("Guess and indicators : "+ rowsArray.get(i));
    }

    public void displayInvalidInput() {
        System.out.print("You did not write yes or no.");
    }

}
