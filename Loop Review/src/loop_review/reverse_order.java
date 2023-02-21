package loop_review;
import java.util.Scanner;

public class reverse_order {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter a string: ");
		
		String rstring = sc.next();
		
		int i;
		
		System.out.print("The reverse String is ");
		
		for( i = rstring.length()-1; i >= 0; i--) {
			
			System.out.print(rstring.charAt(i));
			
		}
		
	}

}
