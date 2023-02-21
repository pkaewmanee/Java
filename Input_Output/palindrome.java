package input_output;
import java.util.Scanner;
public class palindrome {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter a three-digit integer: ");
		
		int a = sc.nextInt();

		String b = a + " ";
		
		boolean c = b.charAt(0) == b.charAt(2);
		
		if(c){
			System.out.println("The number " + a + " is palindrome");
		}
		else if(!c) {
			System.out.println("The number " + a + " is not a palindrome");
		}

	}

}
