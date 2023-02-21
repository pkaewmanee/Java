package input_output;

import java.util.Scanner;

public class factoringandstuff {
	
	public static void main(String []args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter a,b,c: ");
		
		double a = sc.nextDouble();
		
		double b = sc.nextDouble();
		
		double c = sc.nextDouble();
		
		double d = Math.pow(b, 2)-(4*(a*c));
		
		if (d>0) {
			
			double r1 = (-b + Math.sqrt(Math.pow(b, 2)-(4*(a*c))))/ (2*a) ;
			
			double r2 = (-b - Math.sqrt(Math.pow(b, 2)-(4*(a*c))))/ (2*a) ;
			
			System.out.print("The equation has 2 real root: " + r1 + " and " + r2);
		}
		else if(d<0){
			System.out.print("The equation has 0 real root");
		}
		else if(d==0) {
			
			double r1 = (-b + Math.sqrt(Math.pow(b, 2)-(4*(a*c))))/ (2*a) ;
			
			System.out.print("The equation has 1 real root: " + r1);
			
			sc.close();
		}
	}
}
