import java.util.ArrayList;
import java.util.Scanner;

public class Mastermind {

    static int pegNum = 0;
    static int colourNum = 0;
    static ArrayList<String> code = new ArrayList<String>();
    static ArrayList<String> guesses = new ArrayList<String>();
    static ArrayList<String> possibleColours = new ArrayList<String>();

    public static void startNewGame() {
            System.out.println("The code can be between 3 and 8 pegs inclusive.");
            System.out.print("How many pegs would you like? --> ");
            Scanner user_input = new Scanner(System.in);
            String pegNumStr = user_input.next();
            pegNum = Integer.parseInt(pegNumStr);
            System.out.println("The code can have between 3 and 8 colour options inclusive.");
            System.out.print("How many colours would you like? --> ");
            Scanner user_input2 = new Scanner(System.in);
            String colourNumStr = user_input2.next();
            colourNum = Integer.parseInt(colourNumStr);
	        code = Code.getCode(pegNum, colourNum);
            possibleColours = Code.makeList(colourNum);
            System.out.println("The colours you can choose from are: "+possibleColours);
    }

    public static void playGame(int plays) {
        for (int i=plays; i<pegNum+2; i++) {
            System.out.print("guess: ");
	        ArrayList<String> guess = Guess.getGuess(pegNum);
            String guessString = Format.arrayListToString(guess);
            guesses.add(guessString);
            System.out.println(Indicators.getIndicators(code, guess));
            if (guess.equals(code)) {
                System.out.println("Well done! Game over.");
                break;
            }
            else if (guessString.equals("save") || guessString.equals("Save")) {
                String codeString = Format.arrayListToString(code);
                SavedGame.saveGame(code, guesses, possibleColours);
            }
        }
    }

    public static void main(String args[]) {
        ArrayList<String> currentGame = SavedGame.getPreviousGame();
        if (currentGame.isEmpty()) {
            startNewGame();
            playGame(0);
        }
        else {
            System.out.println("Do you want to restart the saved game?");
            System.out.print("Type 'yes' or 'no' --> ");
            Scanner user_input3 = new Scanner(System.in);
            String yesNo = user_input3.next();
            if (yesNo.equals("yes") || yesNo.equals("Yes") || yesNo.equals("y")) {
                code = SavedGame.previousCode();
                guesses = SavedGame.previousGuesses();
                possibleColours = SavedGame.previousColours();
                pegNum = code.size();
                System.out.println("The colours you can choose from are: "+possibleColours);
                System.out.println("Your previous guesses were: "+ guesses);
                System.out.println("Their indicators were: ");
                playGame(guesses.size());
            }
            else if (yesNo.equals("no") || yesNo.equals("No") || yesNo.equals("n")) {
                startNewGame();
                playGame(0);
            }
            else {
                System.out.print("You did not write yes or no.");
                System.out.println("We assume you want to start a new game.");
                startNewGame();
                playGame(0);
            }
        }
    }


}
