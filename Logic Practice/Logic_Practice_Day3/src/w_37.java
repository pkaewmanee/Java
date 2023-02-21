import java.util.Scanner;
public class w_37 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	         Scanner scanner = new Scanner(System.in);
	         System.out.print("Input a string: ");
	         char[] letters = scanner.nextLine().toCharArray();
	         System.out.print("Reverse string: ");
	         for (int i = letters.length - 1; i >= 0; i--) {
	             System.out.print(letters[i]);
	         }
	         System.out.print("\n");
	}
}
		
		/*
		String x = "xof nworb kciuq ehT";
		
		for(int n = 1; n<=x.length(); n++) {
			
			System.out.print( x.charAt(x.length()-n) );
		}
	
	}
	*/
