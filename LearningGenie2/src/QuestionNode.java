import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author ekstrand
 *This class contains the QuestionNode constructors and methods
 *which are the base for the recursion in this program
 */
public class QuestionNode implements DecisionNode{
	DecisionNode yes, no;
	String value;
	
	public QuestionNode(DecisionNode left, DecisionNode right, String value) {
		this.yes = left;
		this.no = right;
		this.value = value;
	}
	public QuestionNode(String value){
		this.value = value;
	}

	/**
	 * Adds up all the GuessNodes
	 */
	public int countObjects() {
		if (this == null) {
			return 0;
		} else {
			return yes.countObjects() + no.countObjects();
		}
	}

	/**
	 * Function goes down the left or right side of node based on user's answer
	 */
	public DecisionNode guess(Scanner in) {
		if(this.value.contains("#")){
		System.out.println(this.value.substring(1));
		} else{
			System.out.println(this.value);
		}
		String ans = in.nextLine().toLowerCase();
		if(ans.equals("yes")){
			this.yes = this.yes.guess(in);
		} else if(ans.equals("no")){
			this.no = this.no.guess(in);
		} else {
			System.out.println("Please enter Yes or No");
			this.guess(in);
		}
		return this;
	}

	/**
	 * Writes QuestionNode to file and adds '#' if not present.
	 */
	public void write(FileWriter out) throws IOException {
		if(!this.value.contains("#")){
		out.write("#" + this.value + "\n");
		} else{
			out.write(this.value + "\n");
		}
		yes.write(out);
		no.write(out);
	}

}
