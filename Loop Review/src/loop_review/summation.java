package loop_review;

public class summation {

	public static void main(String[] args) {
	/*	
		int sum = 0;
		
		for (int i = 1; i<=100; i++) {
			sum = sum+i;
		}
*/
		
		double sum = 0;
		/*
		for (int i = -1, j = 1; i<= 97 && j<=99; i++, j++) {
			
		 sum = sum + (i+2.0)/(j+2.0);
			
		}
		
		*/
		
		for( int i = 1; i<=49; i++) {
			sum += ((2.0)*i-1)/((2.0)*i+1);
		}
		
		System.out.print(sum);
		
		
	}

}
