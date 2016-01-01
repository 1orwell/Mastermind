import java.util.ArrayList;
import java.util.List;

/**
 * Contains different tests that can be run to see whether the indicators
 * returned are correct.
 *
 * <p>
 * The indicators return information to the user, telling them how close their
 * guess is to the code.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 * @release 31/12/15
 */
public class Test {

    public static void main(String args[]) {
        // The test code is all red.
        ArrayList<String> code1 = new ArrayList<String>();
        code1.add("red");
        code1.add("red");
        code1.add("red");
        code1.add("red");
        // The test guess is also all red.
        ArrayList<String> guess1 = new ArrayList<String>();
        guess1.add("red");
        guess1.add("red");
        guess1.add("red");
        guess1.add("red");
        // Because the guess and the answer are the same, the indicators
        // should all be 2.
        List<Integer> answer1 = new ArrayList<Integer>();
        answer1.add(2);
        answer1.add(2);
        answer1.add(2);
        answer1.add(2);
        if (Indicators.getIndicators(code1, guess1).equals(answer1)) {
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }

        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        // The rest of the tests follow the same logic, so commenting won't
        // be necessary.

        ArrayList<String> code2 = new ArrayList<String>();
        code2.add("red");
        code2.add("blue");
        code2.add("green");
        code2.add("red");
        ArrayList<String> guess2 = new ArrayList<String>();
        guess2.add("blue");
        guess2.add("blue");
        guess2.add("pink");
        guess2.add("red");
        List<Integer> answer2 = new ArrayList<Integer>();
        answer2.add(2);
        answer2.add(2);
        answer2.add(0);
        answer2.add(0);
        if (Indicators.getIndicators(code2, guess2).equals(answer2)) {
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }

        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        ArrayList<String> code3 = new ArrayList<String>();
        code3.add("red");
        code3.add("blue");
        code3.add("orange");
        code3.add("green");
        ArrayList<String> guess3 = new ArrayList<String>();
        guess3.add("red");
        guess3.add("red");
        guess3.add("red");
        guess3.add("red");
        List<Integer> answer3 = new ArrayList<Integer>();
        answer3.add(2);
        answer3.add(0);
        answer3.add(0);
        answer3.add(0);
        if (Indicators.getIndicators(code3, guess3).equals(answer3)) {
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }

        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        ArrayList<String> code4 = new ArrayList<String>();
        code4.add("green");
        code4.add("red");
        code4.add("orange");
        code4.add("blue");
        ArrayList<String> guess4 = new ArrayList<String>();
        guess4.add("red");
        guess4.add("green");
        guess4.add("blue");
        guess4.add("orange");
        List<Integer> answer4 = new ArrayList<Integer>();
        answer4.add(1);
        answer4.add(1);
        answer4.add(1);
        answer4.add(1);
        if (Indicators.getIndicators(code4, guess4).equals(answer4)) {
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }

        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        ArrayList<String> code5 = new ArrayList<String>();
        code5.add("green");
        code5.add("green");
        code5.add("orange");
        code5.add("blue");
        ArrayList<String> guess5 = new ArrayList<String>();
        guess5.add("red");
        guess5.add("green");
        guess5.add("blue");
        guess5.add("orange");
        List<Integer> answer5 = new ArrayList<Integer>();
        answer5.add(2);
        answer5.add(1);
        answer5.add(1);
        answer5.add(0);
        if (Indicators.getIndicators(code5, guess5).equals(answer5)) {
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }
    }

}
