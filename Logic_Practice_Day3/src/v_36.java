import java.util.Scanner;
public class v_36 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input the latitude of coordinate 1: ");
		double x1 = sc.nextDouble();
		System.out.print("Input the longitude of coordinate 1: ");
		double y1 = sc.nextDouble();
		System.out.print("Input the latitude of coordinate 2: ");
		double x2 = sc.nextDouble();
		System.out.print("Input the longitude of coordinate 2: ");
		double y2 = sc.nextDouble();
		final double radius = 6371.01;
		System.out.println(Distance(radius, x1, x2, y1, y2));

	}
	
	public static double Distance(double radius, double x1, double x2, double y1, double y2) {
		
		x1 = Math.toRadians(x1);
		x2 = Math.toRadians(x2);
		y1 = Math.toRadians(y1);
		y2 = Math.toRadians(y2);
		
		double d = radius * Math.acos((Math.sin(x1) * Math.sin(x2)) + (Math.cos(x1) * Math.cos(x2) * Math.cos(y1-y2)));
		
		return d;
	}
	


}
