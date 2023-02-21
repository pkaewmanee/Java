
public class u_35 {

	public static void main(String[] args) {
		System.out.println(Polygon_Area(7, 6));
	}
	
	public static double Polygon_Area(double n, double s) {
		double poli = (n*Math.pow(s, 2)) / (4*Math.tan(Math.PI/n));
		return poli;
	}

}
