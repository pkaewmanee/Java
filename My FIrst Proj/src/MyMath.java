import java.util.Scanner;
public class MyMath {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the value of x: ");
		
		int x = sc.nextInt();
		
		if ( x <= 0 ) {
			double x1 = x+2;
			System.out.println(x1);
		}
		else if ( x==1 ) {
			System.out.println("13");
		}
		else if (1<x && x<=10) {		
			double x2 = (Math.pow(x, 2))-(2*x)+3;
			System.out.print(x2);
		}
		else {
			double x3 = (Math.pow(x, 3))-3*(Math.pow(x, 2));
			System.out.println(x3);
		}
		sc.close();

	}

}
