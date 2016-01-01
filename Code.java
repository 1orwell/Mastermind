import java.util.ArrayList;
import java.util.Random;

/**
 * Generates the pseudorandom list of possible colours, and the pseudorandom
 * code.
 *
 * @author Elise Ratcliffe - enr24
 * @version 1.0
 */
public class Code {

    /**
     * Generates a random number between 0 and a max provided by caller.
     *
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
     * Makes a list of possible colours to be chosen from when creating
     * the code.
     *
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


        // Keep looping until we have the correct number of unique colours.
        while (ourColours.size() != numOfColours)
        {
            int index = getRandomInt(allColours.size());
            String colour = allColours.get(index);
            if (!ourColours.contains(colour))
                ourColours.add(colour);

        }
        return ourColours;

	}

    /**
     * Makes a code based of list of possible colours to choose from.
     *
     * @param length
     *          The length of the code.
     * @param possibleColours
     *          List of possible colours to choose from when making or
     *          guessing the code.
     * @return  The pseudorandom code generated.
     */
	public static ArrayList<String> getCode(int length, ArrayList<String> possibleColours) {
        // DEBUG only - do some checks on input
        assert(length <= Constants.MAX_NUM_OF_PEGS);
        assert(length >= Constants.MIN_NUM_OF_PEGS);

		ArrayList<String> code = new ArrayList<String>();

        // Generate pseudorandom code, by selecting "random" colours
        // from possible colours. 
		for (int i=0; i<length; i++) {
			int randomInt = getRandomInt(possibleColours.size());
			String colour = possibleColours.get(randomInt);
			code.add(colour);
		}
		return code;
	}

}
