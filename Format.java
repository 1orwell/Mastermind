import java.util.ArrayList;
import java.util.List;

/**
 * Converts Lists into Strings.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 */
public class Format {

    /**
     * Converts an ArrayList<String> to a String.
     *
     * @param arrayList
     *          Generic List of type ArrayList<String>.
     * @return  String that contains the contents of the ArrayList<String>.
     */
    public static String arrayListToString(ArrayList<String> arrayList) {
        String stringList = "";

        for (String s : arrayList) {
            stringList += s + " ";
        }

        return stringList;
    }

    /**
     * Converts a List<Integer> to a String.
     *
     * @param list
     *          Generic List of type List<Integer>.
     * @return  String that contains the contents of the List<Integer>.
     */
    public static String arrayListIntToString(List<Integer> list) {
        String stringList = "";

        for (Integer i : list) {
            stringList += i + " ";
        }

        return stringList;
    }
}
