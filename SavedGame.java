import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.Writer;
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


public class SavedGame {

    public static void saveGame(ArrayList<String> code, ArrayList<String> guesses, ArrayList<String> possibleColours) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("theGame.txt"), "utf-8"))) {
            writer.write(Format.arrayListToString(code));
            writer.write(Format.arrayListToString(guesses));
            writer.write(Format.arrayListToString(possibleColours));
        }
        catch (IOException e) {
            System.exit(0);
        }
    }

    public static ArrayList<String> getPreviousGame() {
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
            System.out.println("YOWSER got an exception");
        }

        catch (IOException e) {
        }
        return arrayList;
    }

    public static ArrayList<String> previousCode() {
        String codeString = getPreviousGame().get(0);
        ArrayList<String> code = new ArrayList<String>(Arrays.asList(codeString.split(" ")));
        return code;
    }

    public static ArrayList<String> previousGuesses() {
        ArrayList<String> guesses = new ArrayList<String>();
        ArrayList<String> allItems = getPreviousGame();
        int numOfGuesses = (allItems.size()) - 2;
        for (int i=1; i<numOfGuesses; i++) {
            guesses.add(allItems.get(i));
        }
        return guesses;
    }

    public static ArrayList<String> previousColours() {
        int length = getPreviousGame().size();
        String posColoursString = getPreviousGame().get(length-1);
        ArrayList<String> possibleColours = new ArrayList<String>(Arrays.asList(posColoursString.split(" ")));
        return possibleColours;
    }
}
