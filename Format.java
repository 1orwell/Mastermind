import java.util.ArrayList;

public class Format {

    public static String arrayListToString(ArrayList<String> arrayList) {
        String stringList = "";

        for (String s : arrayList) {
            stringList += s + "\t";
        }

        return stringList;
    }

}
