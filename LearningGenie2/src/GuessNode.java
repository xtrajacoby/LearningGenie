import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author ekstrand
 *This class contains the GuessNode constructor and methods
 *which act as the leaves of the decision tree.
 */
public class GuessNode implements DecisionNode{
	String value;
	
	public GuessNode(String value) {
		this.value = value;
	}

	public int countObjects() {
		return 1;
	}

	/**
	 * Guesses what the user is thinking and asks for a new question to make a node out of
	 * to learn more things.
	 */
	public DecisionNode guess(Scanner in) {
		System.out.println(this.value);
		DecisionNode ret = this;
		String ans = in.nextLine().toLowerCase();
		if (ans.equals("yes")) {
			System.out.println("See! I am ingenious.");
		} else if (ans.equals("no")) {
			System.out.println("Dagnabit! I was wrong!");
			System.out.println("What were you thinking of?");
			String str = in.nextLine();
			if (str.contains("#")) {
				int check = 1;
				while (check == 1) {
					System.out.println("Please don't enter a hashtag");
					str = in.nextLine();
					check = 0;
					if (str.contains("#")) {
						check = 1;
					}
				}
			}
			GuessNode corrected = new GuessNode(str);
			System.out.println("What is a question that can help me tell the difference between "
							+ this.value + " and " + corrected.value);
			System.out.println("(Yes corresponds to " + this.value
					+ " No corresponds to " + corrected.value + ")");
			
			String strdos = in.nextLine();
			if (strdos.contains("#")) {
				int check = 1;
				while (check == 1) {
					System.out.println("Please don't enter a hashtag");
					strdos = in.nextLine();
					check = 0;
					if (strdos.contains("#")) {
						check = 1;
					}
				}
			}
			String question = strdos;
			QuestionNode helper = new QuestionNode(question);
			helper.yes = this;
			helper.no = corrected;
			ret = helper;
		} else {
			System.out.println("Please enter Yes or No");
			ret = guess(in);
		}
		return ret;
	}

	public void write(FileWriter out) throws IOException {
		out.write(this.value + "\n");
	}
}
