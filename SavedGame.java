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
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.lang.StringBuilder;
import java.lang.System;

/**
 * Deals with saving a game and restoring a saved game.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 * @release 31/12/15
 */
public class SavedGame {

    private static UserInterface ui = new TextUserInterface();

    /**
     * Saves the game played so far.
     *
     * @param playComputerStr
     *          "true" if game is Computer v. Computer, "false" otherwise.
     * @param code
     *          The code the player is trying to guess.
     * @param possibleColours
     *          List of possible colours to choose from when guessing code.
     * @param rows
     *          Contains the guesses made so far and their indicators.
     */
    public static void saveGame(String playComputerStr, ArrayList<String> code, ArrayList<String> possibleColours, ArrayList<Row> rows) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("theGame.txt"), "utf-8"))) {
            writer.write(playComputerStr);
            writer.write("\n");
            writer.write(Format.arrayListToString(code));
            writer.write("\n");
            writer.write(Format.arrayListToString(possibleColours));
            writer.write("\n");

            for (Row r : rows) {
                writer.write(Format.arrayListToString(r.getGuess()));
                writer.write(": ");
                writer.write(Format.arrayListIntToString(r.getIndicators()));
                writer.write("\n");
            }

        }
        catch (IOException e) {
            System.exit(0);
        }
    }

    /**
     * Writes all the information stored in theGame file to an array list.
     *
     * @return  ArrayList<String> containing all the information about
     *          the last, unfinished, game that was saved.
     */
    public static ArrayList<String> getCurrentGame() {
        String fileName = "theGame.txt";

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
        return arrayList;
    }

    /**
     * Clears the file where the game was being saved.
     */
    public static void clearFile() {
        try {
            PrintWriter writer = new PrintWriter("theGame.txt");
            writer.print("");
            writer.close();
        }

        catch (FileNotFoundException ex) {
            ui.displayException("a FileNotFoundException");
            System.exit(0);
        }
    }

    /**
     * Retrieves the code of the saved game.
     *
     * @return  The code from the saved game.
     */
    public static ArrayList<String> getCurrentCode() {
        String codeString = getCurrentGame().get(1);
        ArrayList<String> code = new ArrayList<String>(Arrays.asList(codeString.split(" ")));
        return code;
    }

    /**
     * Retrieves all the rows of the saved game.
     *
     * <p>
     * Rows are an object of type Row. Each row contains a single guess and
     * its associated indicators.
     *
     * @return  All the rows from the saved game.
     */
    public static ArrayList<String> getCurrentRows() {
        ArrayList<String> currentGame = getCurrentGame();
        ArrayList<String> rowsString = new ArrayList<String>();
        // Start at three because three other things are saved to the file
        // before the rows are.
        for (int i=3; i<(currentGame.size()); i++) {
            rowsString.add(currentGame.get(i));
        }
        return rowsString;
    }


    /**
     * Retrieves the list of possible colours of the saved game.
     *
     * <p>
     * The possible colours are all the colours that can be chosen from
     * when the user is making their guess.
     *
     * @return  The list of possible colours from the saved game.
     */
    public static ArrayList<String> getCurrentPossibleColours() {
        String posColoursString = getCurrentGame().get(2);
        ArrayList<String> possibleColours = new ArrayList<String>(Arrays.asList(posColoursString.split(" ")));
        return possibleColours;
    }

    /**
     * Retrieves whether a human is breaking the code or whether it is a human.
     *
     * @return  String indicating whether a human or computer is breaking
     *          the code.
     */
    public static String getPlayComputer() {
        String playComputerStr = getCurrentGame().get(0);
        return playComputerStr;
    }
}
