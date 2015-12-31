import java.util.ArrayList;
import java.util.Random;

/**
 * Generates the pseudorandom list of possible colours, and the pseudorandom
 * code.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 * @release 31/12/15
 */
public class Code {

    /**
     * @param max
     *          Maximum number the random number generated can be.
     * @return  Pseudorandom number in the range 0-max.
     */
    public static int getRandomInt(int max) {
		Random randomGenerator = new Random();
        int randomNum = randomGenerator.nextInt(max);
        return randomNum;
    }

    /**
     * @param numOfColours
     *          The number of colours in the list of possible colours to
     *          choose from when guessing or making the code.
     * @return  List of possible colours.
     */
	public static ArrayList<String> makeList(int numOfColours) {
		ArrayList<String> allColours = new ArrayList<String>();
		ArrayList<String> ourColours = new ArrayList<String>();
        allColours.add("red");
        allColours.add("blue");
        allColours.add("green");
        allColours.add("yellow");
        allColours.add("purple");
        allColours.add("orange");
        allColours.add("brown");
        allColours.add("pink");

        // Create list of numbers from 0 to 7.
        ArrayList<Integer> listOfNums = new ArrayList<Integer>();
        for (int j=0; j<8; j++) {
            listOfNums.add(j);
        }

        // Create a list of random numbers, length of list equal to number
        // of possible colours that can be chosen from. Done by removing
        // random numbers from the listOfNums. Done this way so there are
        // no repeats in the list of numbers.
        while (listOfNums.size() > numOfColours) {
            int index = getRandomInt(listOfNums.size());
            listOfNums.remove(index);
        }

        // Create list of possible colours. Done by itereating through the list
        // of random numbers created above. Use this to index into the
        // ArrayList containing all colour options.
		for (int i=0; i<numOfColours; i++) {
            int num = listOfNums.get(i);
            String colour = allColours.get(num);
            ourColours.add(colour);
        }
		return ourColours;
	}

    /**
     * @param possibleColours
     *          List of possible colours to choose from when making or
     *          guessing the code.
     * @param length
     *          The length of the code.
     * @return  The pseudorandom code generated.
     */
	public static ArrayList<String> makeCode(ArrayList<String> possibleColours, int length) {
		ArrayList<String> code = new ArrayList<String>();
        // Make the pseudorandom code. Done by getting a random integer
        // and indexing into the list of possible colours - this
        // colour is added to the code.
		for (int i=0; i<length; i++) {
			int randomInt = getRandomInt(possibleColours.size());
			String colour = possibleColours.get(randomInt);
			code.add(colour);
		}
		return code;
	}

    /**
     * @param length
     *          The length of the code.
     * @param possibleColours
     *          List of possible colours to choose from when making or
     *          guessing the code.
     * @return  The psedorandom code generated in the method makeCode.
     */
	public static ArrayList<String> getCode(int length, ArrayList<String> possibleColours) {
		ArrayList<String> code = makeCode(possibleColours, length);
		return code;
	}
}
