package input_output;
import java.io.*;
public class DecomVect {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bob = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Insert F: ");
		String F1 = bob.readLine();
		System.out.print("Angle: ");
		String A1 = bob.readLine();
		double angle = Double.parseDouble(A1);
		double force = Double.parseDouble(F1);
		double thetaRad = Math.toRadians(angle);
		double fx =  force*Math.cos(thetaRad);
		double fy = force*Math.sin(thetaRad);
		System.out.println("fx = " + fx);
		System.out.println("fy ="  + fy);
		
		
		
	}

}
