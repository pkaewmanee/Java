
public class t_34 {

	public static void main(String[] args) {
		System.out.print(HexArea(6));

	}
	
	public static double HexArea(double s) {
		double hex_a = (6*s*s)/(4*Math.tan(Math.PI/6));
		return hex_a;
	}

}
