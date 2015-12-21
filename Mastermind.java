import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Mastermind {

    static int pegNum = 0;
    static int colourNum = 0;
    static ArrayList<String> code = new ArrayList<String>();
    static ArrayList<String> guesses = new ArrayList<String>();
    static ArrayList<String> possibleColours = new ArrayList<String>();
    static ArrayList<Row> rows = new ArrayList<Row>();

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
            List<Integer> indicators = Indicators.getIndicators(code, guess);
            if (guessString.contains("save") || guessString.contains("Save")) {
                System.out.println("saving ");
                SavedGame.saveGame(code, rows, possibleColours);
            }
            else if (guess.equals(code)) {
                System.out.println("Well done! Game over.");
                SavedGame.clearFile();
                break;
            }
            else{
                if (i==pegNum+1) {
                    System.out.println("Unlucky. The actual code was: "+code);
                    System.out.println("We will now clear your saved data so you can't cheat.");
                    SavedGame.clearFile();
                    break;
                }
                System.out.println(indicators);
                Row row = new Row(guess, indicators);
                rows.add(row);
                guesses.add(guessString);
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
                    possibleColours = SavedGame.previousColours();
                    ArrayList<String> rowsString = SavedGame.previousRows();
                    pegNum = code.size();
                    System.out.println("The colours you can choose from are: "+possibleColours);
                    for (int j=0; j<rowsString.size(); j++) {
                        System.out.println("Guess and indicators : "+ rowsString.get(j));
                    }
                    playGame(SavedGame.getPreviousGame().size()-2);
            }
            else if (yesNo.equals("no") || yesNo.equals("No") || yesNo.equals("n")) {
                SavedGame.clearFile();
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
