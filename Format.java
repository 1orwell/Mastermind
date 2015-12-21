import java.util.ArrayList;
import java.util.List;

public class Format {

    public static String arrayListToString(ArrayList<String> arrayList) {
        String stringList = "";

        for (String s : arrayList) {
            stringList += s + " ";
        }

        return stringList;
    }

    public static String arrayListIntToString(List<Integer> list) {
        String stringList = "";

        for (Integer i : list) {
            stringList += i + " ";
        }

        return stringList;
    }
}
