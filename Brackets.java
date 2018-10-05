import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
public class Brackets {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader("C:\\Users\\gb_mo\\Desktop\\BracketsInput.txt")); //reads input from file
		String ans; //storage for solutions
		int examp = 0;
		
		while(in.hasNextLine()) {
			ans = "";
			examp ++;
			String exp = in.nextLine();
			if (exp.contains("{") && !(exp.contains("}")) ) { //tests for all different missing enclosures
				if (exp.contains("]")) {
					ans += Integer.toString(exp.indexOf("]") + 2) + " ";
					for (int i = exp.indexOf("]"); i < exp.length(); i++) {
						if (exp.substring(i, i+1).matches("\\d{1}\\D")) {ans += Integer.toString(i+2) + " ";}
						//if (exp.substring(i, i+1).matches("(\\d{1})$")) {ans += Integer.toString(i+2) + " ";}
					}
				}
				System.out.println(examp + " " + ans);
			}
			else if (exp.contains("[") && !(exp.contains("]"))) {
				
			}
			else if (exp.contains("(") && !(exp.contains(")"))) {
				
			}
			else if (exp.contains("}") && !(exp.contains("{"))) {
				
			}
			else if (exp.contains("]") && !(exp.contains("["))) {
				
			}
			else if (exp.contains(")") && !(exp.contains("("))) { //could use else statement but readability is better without
				
			}
		}
	}
}
