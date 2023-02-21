package loop_review;

import java.util.Scanner;

public class small_factor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter integer: " );
		
		int num = sc.nextInt();
		
		for (int i = 2; i <= num;) { 
			if((num%i) == 0) {
				System.out.print(i + " ");
				num = num/i;
			}
			else {
				i++;
			}
		}
		

	}

}
