import java.util.ArrayList;
import java.util.Random;

public class Code {

	public static ArrayList<String> makeList() {
		ArrayList<String> colours = new ArrayList<String>();
		colours.add("red");		
		colours.add("green");		
		colours.add("blue");		
		colours.add("yellow");		
		colours.add("orange");		
		colours.add("purple");		
		colours.add("pink");		
		colours.add("brown");		
		return colours;
	}

	public static ArrayList<String> makeCode(ArrayList<String> list) {
		ArrayList<String> codedList = new ArrayList<String>();
		Random randomGenerator = new Random();
		for (int i=1; i<9; i++) {
			int randomInt = randomGenerator.nextInt(8);
			String colour = list.get(randomInt);
			codedList.add(colour);	
		}
		return codedList;
	} 

	public static ArrayList<String> getCode() {
		ArrayList<String> colourList = makeList();
		ArrayList<String> codedList = makeCode(colourList);
		return codedList;
	}
}
