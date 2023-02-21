
import java.util.Scanner;
public class LLL {

	public static void main(String [] args) {
		Scanner s = new Scanner(System.in);
		
		double depo = s.nextDouble();
		double interest = 0;
		double re = 0;
		double fyr = 0;
		
		for (int n = 1; n<11; n++) {
			
			if(n>1) {
				
			interest += depo * (double) 0.015;
			
			}
			
			re += (depo + interest);
			
			if( n == 4 || n == 9 ) {
				fyr =  re * 0.05;
			}
			if ( n == 5 || n == 10) {
				re = re + fyr;
			}
			
			System.out.println("The annual fee: " + re + "\nYear Deposit: " + n +
					"\nInterest: " + interest +"\n");
			
		}
		
		s.close();
	}
	
}
