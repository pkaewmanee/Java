package input_output;
import java.util.Scanner;
public class monthymort {
	
	double monthlymortgage;
	double amountowed;
	double annualinterestrate = 0.0749;
	
	public monthymort(double mm, double ao) {
		
		this.monthlymortgage = mm;
		this.amountowed = ao;
			
	}
	
	public static void main(String [] arg) {
		
		Scanner read = new Scanner(System.in);
		
		System.out.print("Enter your monthly mortgage payment: ");
		
		double mm1 = read.nextDouble();
		
		System.out.print("Enter the amount you still owed: ");
		
		double ao1 = read.nextDouble();
		
		monthymort personA = new monthymort(mm1, ao1);
		
		double a = ((personA.monthlymortgage*(personA.annualinterestrate*1/12)));
		
		double a1 = (a + personA.monthlymortgage);
		
		System.out.printf("This is the total payment in a month plus it's interest rate %.2f \n", a1);
		
		double b = ((personA.monthlymortgage-personA.amountowed)*(personA.annualinterestrate*1/12));
		
		double b1 = (b + personA.monthlymortgage);
		
		System.out.printf("The amount you paid reduce the debt to %.2f from %.2f \n" , b1, a1);
		
		double c = b1-personA.monthlymortgage;
		
		System.out.printf("and so, The amount you paid reduce your debt this month to %.2f " , c);
		
		double c1 = c+personA.amountowed;
		
		System.out.printf("In conclusion you still owns us %.2f \n", c1);
		
		
	}
	
}
