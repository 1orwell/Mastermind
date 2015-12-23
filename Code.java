import java.util.ArrayList;
import java.util.Random;

public class Code {

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

        ArrayList<Integer> listOfNums = new ArrayList<Integer>();
        for (int j=0; j<8; j++) {
            listOfNums.add(j);
        }

		Random randomGenerator = new Random();
        while (listOfNums.size() > numOfColours) {
            int index = randomGenerator.nextInt(listOfNums.size());
            listOfNums.remove(index);
        }

		for (int i=0; i<numOfColours; i++) {
            int num = listOfNums.get(i);
            String colour = allColours.get(num);
            ourColours.add(colour);
        }
		return ourColours;
	}

	public static ArrayList<String> makeCode(ArrayList<String> possibleColours, int length) {
		ArrayList<String> code = new ArrayList<String>();
		Random randomGenerator = new Random();
		for (int i=0; i<length; i++) {
			int randomInt = randomGenerator.nextInt(possibleColours.size());
			String colour = possibleColours.get(randomInt);
			code.add(colour);
		}
		return code;
	}

	public static ArrayList<String> getCode(int length, ArrayList<String> possibleColours) {
		ArrayList<String> code = makeCode(possibleColours, length);
		return code;
	}
}
