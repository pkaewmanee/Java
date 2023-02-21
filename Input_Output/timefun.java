package input_output;
import java.util.Scanner;
public class timefun {

	public static void main(String[] args) {
		Scanner ab = new Scanner(System.in);
		
		System.out.print("Write any 3 word, you want: ");
		
		String a = ab.nextLine();
		
		int b = a.length();
		
		boolean c = b > 0;
		
		boolean d = b < 11;
		
		int e = a.indexOf(0, 3);
		
		if(c || d){
			
			System.out.println("It should look like " + b);
			
		}else {
			
			System.exit(0);
			
		}
		
		
		
		ab.close();

	}

}
