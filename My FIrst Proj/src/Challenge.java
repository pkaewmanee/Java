// This program calculate the approximated value of n!
//   using Stirling formula

public class Challenge {
	public static void main(String[] args) {

		int n = Integer.parseInt("100");
		
		System.out.print("java FactorialApproximate ");	    	

	    	double simp = (Math.sqrt(2D*Math.PI*n))*(Math.pow(n/Math.E,  n));
			double low = Math.sqrt(2D*Math.PI)*Math.pow(n, n+0.5d)*Math.pow(Math.E, -n+1d/(12.0d*n+1d));
			double up = Math.sqrt(2D*Math.PI)*Math.pow(n, n+0.5d)*Math.pow(Math.E, -n+1d/(12.0d*n));
	    	// fill your code here\\\\\\\\

			System.out.println("The approximated value of " + n + "! using Stirling:");
			
			System.out.printf("simple: %.15f %n",simp );
			
			System.out.printf("lower: %.15f %n",low);
			
			System.out.printf("upper: %.15f %n",up);

	}
}
