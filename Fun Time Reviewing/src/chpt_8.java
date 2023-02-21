
public class chpt_8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = m(1,1);
		float f = m(Math.exp(5));
		String s = m(2f,8d);
		IseStudent l = m("John", "K.", "Maddy");
		for(double d = 1; d<=256; d*=2) m(d);
		
	}
	
	public static int m(int n, int o) {
		return 1;
	}
	
	public static float m(double n) {
		return 1.0f;
	}
	
	public static String m(float n, double o) {
		return "a";
	}
	
	public static IseStudent m(String m, String n, String o) {
		return new IseStudent();
	}
	
	

}

class IseStudent{
	//temporary class for IseStudent
}
