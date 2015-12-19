import java.util.ArrayList;
import java.util.Scanner;

public class Mastermind {

    public static void main(String args[]) {
        System.out.println("The code can be between 3 and 8 pegs inclusive.");
        System.out.print("How many pegs would you like? --> ");
        Scanner user_input = new Scanner(System.in);
        String pegNumStr = user_input.next();
        int pegNum = Integer.parseInt(pegNumStr);
	    ArrayList<String> code = Code.getCode(pegNum);
        for (int i=0; i<pegNum+2; i++) {
            System.out.print("guess: ");
	        ArrayList<String> guess = Guess.getGuess();
            System.out.println(Indicators.getIndicators(code, guess, pegNum));
            if (guess.equals(code)) {
                System.out.println("Well done! Game over.");
                break;
            }
        }
    }

}
