import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// import as needed

public class MutipleChoiceStudy {
	public static void main(String[] args) throws FileNotFoundException {
		// enter your code here
		//----------------------
		
		//read file
		File sheet1in = new File("/Users/fill/Desktop/L06_2020-toStudent/sheet2.in");
		Scanner sc = new Scanner(sheet1in);
		
		//read solution
		String solution = sc.nextLine();
		
		//print solution
		System.out.println("\t[" + solution.length()+ "]\t" + "{" + solution + "}");

		//read answer has used for checking wheter does it have another line
		while(sc.hasNext()) {
			String name_ans = sc.nextLine();
			String name = name_ans.substring(0, name_ans.indexOf(" "));
			String ans = name_ans.substring(name_ans.lastIndexOf(" ")+1);
			String correct="";
			int score = 0;
			for(int i = 0; i<solution.length(); i++) {
				if(ans.charAt(i) == solution.charAt(i)) {
					score++;
					correct += ans.charAt(i);
				}
				else {
					correct += "-";
				}								
			}
			
			System.out.println(name + "\t[" + score+ "]\t" + "{" + correct + "}");
			
		}
		
	}
}