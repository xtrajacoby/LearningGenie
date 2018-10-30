import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author ekstrand
 *This class is the wrapper class that the user interacts with
 */
public class LearningGenie {
	
	/**
	 * 
	 * Returns the answer the user gives when asked if they want to
	 * play again.
	 */
	public static String check(String v){
		if(v.equals("yes")){
			System.out.println("Let's play");
			return "yes";
		}
		else if(v.equals("no")){
			return "no";
		} else {
			System.out.println("Please enter Yes or No");
			Scanner in = new Scanner(System.in);
			String ans = in.nextLine();
			in.close();
			return check(ans);
		}
	}
	
	public static void intro(DecisionTree tree){
		System.out.println("Hello, I am Genie the Learning Genie");
		System.out.println("I can read your mind");
		System.out.println("I know " + tree.countObjects() + " things");
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		File stuff = new File("data.txt");
		DecisionTree t = new DecisionTree(stuff);
		
		intro(t);

		t.guess(in);
		
		System.out.println("Would you like to play again?");
		String ans = check(in.nextLine().toLowerCase());
		while(ans.equals("yes")){
		t.guess(in);
		System.out.println("Would you like to play again?");
		ans = check(in.nextLine().toLowerCase());
		}
		FileWriter out = new FileWriter(stuff);
		t.write(out);
		out.close();

	}

}
