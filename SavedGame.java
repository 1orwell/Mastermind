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

public class SavedGame {

    public static void saveGame(String playComputerStr, ArrayList<String> code, ArrayList<Row> rows, ArrayList<String> possibleColours) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("theGame.txt"), "utf-8"))) {
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
            System.out.println("YOWSER got an exception");
        }

        catch (IOException e) {
            System.out.println("YOWSER got an exception");
        }
        return arrayList;
    }

    public static void clearFile() {
        try {
            PrintWriter writer = new PrintWriter("theGame.txt");
            writer.print("");
            writer.close();
        }

        catch (FileNotFoundException ex) {
            System.out.println("YOWSER got an exception");
        }
    }

    public static ArrayList<String> getCurrentCode() {
        String codeString = getCurrentGame().get(1);
        ArrayList<String> code = new ArrayList<String>(Arrays.asList(codeString.split(" ")));
        return code;
    }

    public static ArrayList<String> getCurrentRows() {
        ArrayList<String> currentGame = getCurrentGame();
        ArrayList<String> rowsString = new ArrayList<String>();
        for (int i=3; i<(currentGame.size()); i++) {
            rowsString.add(currentGame.get(i));
        }
        return rowsString;
    }

    public static ArrayList<String> getCurrentPossibleColours() {
        String posColoursString = getCurrentGame().get(2);
        ArrayList<String> possibleColours = new ArrayList<String>(Arrays.asList(posColoursString.split(" ")));
        return possibleColours;
    }

    public static String getPlayComputer() {
        String playComputerStr = getCurrentGame().get(0);
        return playComputerStr;
    }
}
