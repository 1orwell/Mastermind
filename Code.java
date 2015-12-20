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
        String colour = " ";
        for (int i=0; i<numOfColours; i++) {
            colour = allColours.get(i);
		    ourColours.add(colour);
        }
		return ourColours;
	}

	public static ArrayList<String> makeCode(ArrayList<String> list, int length) {
		ArrayList<String> codedList = new ArrayList<String>();
		Random randomGenerator = new Random();
		for (int i=1; i<length+1; i++) {
			int randomInt = randomGenerator.nextInt(length);
			String colour = list.get(randomInt);
			codedList.add(colour);
		}
		return codedList;
	}

	public static ArrayList<String> getCode(int length, int numOfColours) {
		ArrayList<String> colourList = makeList(numOfColours);
		ArrayList<String> codedList = makeCode(colourList, length);
		return codedList;
	}
}
