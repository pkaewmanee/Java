package Lab_07;

import java.util.Scanner;

public class TwoTermsMethod {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter 5 inputs (a, x, b, r, n): ");
		int a = sc.nextInt();
		int x = sc.nextInt();
		int b = sc.nextInt();
		int r = sc.nextInt();
		int n = sc.nextInt();
		printInputsAndOutput(a, x, b, r, n, calculateY(a, x, b, r, n));
		sc.close();
	}

	public static double axMinusLnB(int a, int x, int b) {
		if (b == 0)
			return a * x;
		return (double) (a * x) - Math.log(b);
	}

	public static double sumNegSquareI(int n) {
		double result = 0;
		for (int i = 1; i <= n; i++)
			result += Math.pow((-1.0), (n + 1.0)) * Math.pow(i, 2.0);
		return result;
	}

	public static double calculateY(int a, int x, int b, int r, int n) {
		return Math.round(((axMinusLnB(a, x, b) * r) + sumNegSquareI(n) * Math.pow(r, 2))*100.0)/100.0;
	}

	public static void printInputsAndOutput(int a, int x, int b, int r, int n, double y) {
		System.out.printf("Inputs (a,x,b,r,n) = %d,%d,%d,%d,%d\nOutput (y) = %.02f", a, x, b, r, n, y);
	}
}
