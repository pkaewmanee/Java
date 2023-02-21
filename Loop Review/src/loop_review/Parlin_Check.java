package loop_review;
import java.util.Scanner;

public class Parlin_Check {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter word: ");
		
		String palin = sc.next();
		
		int k = 0;
		// use i to print palindrome
		for(int i = 0; i < (palin.length()/2); i++){
			if(palin.charAt(i) != palin.charAt(palin.length()-i-1)) {
				
				k = 1;
				
				System.out.println("No, String is not a palindrome");
				
				break;
			}
		}
		if(k == 0) {
		System.out.print(palin + "is a palindrome");
	
		}

	}

}
