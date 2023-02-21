import java.util.ArrayList;

public class bob {
	float width,height;
	int x,y;
	
	public bob( float wid, float hei, int X, int Y) {
		this.width = wid;
		this.height = hei;
		this.x = X;
		this.y = Y;
	}
	public static void ma
	
	
	// DO NOT MODIFY THE MAIN METHOD
	public static void main(String[] args) {
		//double[] data = readFromFile("bisection.in");
		//double[] expected = readFromFile("bisection.out");
		
		//modify
		double[] data = {2,3,4,9,20,100,144};
		double[] expected = {1.4142135623842478,
				1.7320508075645193,
				2.0,
				3.0000000000873115,
				4.472135955002159,
				9.99999999985448,
				12.000000000349246};
		
		String filename = args[0];
		ArrayList<Integer> data = readFromFile(filename);
		  /*String filename = "data.txt";
		  int data[] = {123,2346,1285434,25993669,458299015};
		  I apologies for modifying code, my can't use my terminal for
		  a while now. Sorry.
		*/
}
