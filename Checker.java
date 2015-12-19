import java.util.ArrayList;

public class Checker {

	public static void main(String args[]) {
		int counter = 0;
		//ArrayList<String> code = Code.getCode();
		//ArrayList<String> guess = Guess.getGuess();
		ArrayList<String> code = new ArrayList<String>();
		code.add("blue");
		code.add("red");
		code.add("red");
		code.add("red");
		code.add("red");
		code.add("red");
		code.add("red");
		code.add("red");
		ArrayList<String> guess = new ArrayList<String>();
		guess.add("blue");
		guess.add("blue");
		guess.add("pink");
		guess.add("red");
		guess.add("red");
		guess.add("red");
		guess.add("red");
		guess.add("red");
		System.out.println(code);	
		System.out.println(guess);	
		List<Integer> indicators = new ArrayList<Integer>();
		int rightColourWrongPos = 0;
		int rightColourRightPos = 0;
		String used = "used";
		for (int i=0; i<8; i++) {
			if (code.contains(guess.get(i))) {
				counter += 1;
				rightColourWrongPos++;	
				indicators.add(1);
				System.out.println("code colour: " + code.get(i) + ". guess colour: " + guess.get(i));
				if (guess.get(i).equals(code.get(i))) {
					indicators.set(i, 2);
				}
				for (int j=0; j<8; j++) {
					if (code.get(j).equals(guess.get(i))) {
						if (code.get(j).equals(guess.get(j))) {
							System.out.println("Not too sure what to do now.");		
						}
						else {
							System.out.println("code colour: "+code.get(j)+" guess colour: "+guess.get(i));
							code.set(j, used);
							System.out.println("New code is " + code);	
							break;
						}
					}
				}
			}
		}
		System.out.println("The number of correct colours in the wrong position are " + rightColourWrongPos);
		System.out.println("The number of correct colours with the correct position are " + rightColourRightPos);
	}
}
