package input_output;
import java.util.Scanner;
public class studentranke {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Numbeh Stodent : ");
		int x = sc.nextInt();
		
		do {
			System.out.print("Enter Stodent name: ");
			String name = sc.next();
			System.out.print("Enter Stoodent score: ");
			double score = sc.nextDouble();
			
			x--;
			
		}while(x!=0);
		
		

	}

}
