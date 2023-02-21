package input_output;

import java.util.Scanner;

public class noneincder {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter three integer please: ");
		
		int a1 = sc.nextInt();
		
		int a2 = sc.nextInt();
		
		int a3 = sc.nextInt();
		
		if (a1>a2 && a2>a3 && a1>a3) {
			System.out.print(a1 + " " + a2 + " " + a3);
		}
		
		else if (a2>a3 && a3>a1 && a2>a1) {
			System.out.print(a2 + " " + a3 + " " + a1);
		}
		
		else if (a3>a1 && a1>a2 && a3>a2) {
			System.out.print(a3 + " " + a1 + " " + a2);
		}
		
		else if (a1>a3 && a3>a2 && a1>a2) {
			System.out.print(a1 + " " + a3 + " " + a2);
		}
		
		else if (a2>a3 && a1>a3 && a2>a3) {
			System.out.print(a2 + " " + a1 + " " + a3);
		}
		
		else if (a3>a1 && a3>a2 && a2>a1) {
			System.out.print(a3 + " " + a2 + " " + a1);
		}
		
		else if (a1>a3 && a3>a2 && a1>a2) {
			System.out.print(a1 + " " + a3 + " " + a2);
		}
		
		else if (a2>a3 && a2>a1 && a3>a1) {
			System.out.print(a2 + " " + a3 + " " + a1);
		}

	}

}
