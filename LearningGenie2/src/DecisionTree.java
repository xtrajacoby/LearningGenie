import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * 
 * @author ekstrand
 * This class creates a tree that the user plays the game with
 */
public class DecisionTree {
	
	static DecisionNode root;
	
	public DecisionTree(){
		root = new GuessNode("dog");
	}
	
	public DecisionTree(File file) throws IOException{
		Scanner in = new Scanner(file);
		String check = in.nextLine();
		if(check.contains("#")){
			String next = in.nextLine();
			QuestionNode start = new QuestionNode(check);
			start.yes = build(in, start, next);
			start.no = build(in, start, in.nextLine());
			root = start;
		} else{
		root = new GuessNode(check);
		}
		in.close();
	}
	
	public static DecisionNode build(Scanner in, QuestionNode cur, String val) throws IOException{
		if(val.contains("#")){
			QuestionNode add = new QuestionNode(val);
			add.yes = build(in, add, in.nextLine());
			add.no = build(in, add, in.nextLine());
			return add;
		} 
			DecisionNode ret = new GuessNode(val);
			return ret;
	}
	
	
	public int countObjects() {
		return root.countObjects();
	}
	
	public DecisionNode guessH(DecisionNode cur, Scanner in){
		cur = cur.guess(in);
		return cur;
	}
	public void guess(Scanner in) {
		root = guessH(root, in);
	}
	
	public void write(FileWriter out) throws IOException {
		root.write(out);
	}

}
