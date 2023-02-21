
public class hhhh {

	public static void main(String[] args) {
		
	}
	
	public static String x(int y) {
		String z = "";
		if( y<1000000 && y>0) {
			if(y >= 999999 && y<= 900000) {
				z = "nine hundred thousand";
			}
			
			if(y >= 899999 && y<= 800000) {
				z = "eight hundred thousand";
			}
		}
		
		
		return z;
	}

}
