package input_output;

import java.util.Scanner;

public class costw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		double w = sc.nextDouble();
		
		if(w <= 2 && 0 < w) {
			System.out.print("2.5");
		}
		
		else if(w <= 4 && 2 < w) {
			System.out.print("4.5");
		}
		
		else if(w <= 10 && 4 < w) {
			System.out.print("10");
		}

		else if(w <= 10 && 20 < w) {
			System.out.print("20");
		}
		else {
			System.out.print("Package can't be ship");
		}


	}

}
