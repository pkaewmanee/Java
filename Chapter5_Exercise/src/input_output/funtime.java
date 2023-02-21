package input_output;
import java.util.Scanner;
public class funtime {

	public static void main(String[] args) {
		Scanner ae = new Scanner(System.in);
		
		System.out.print("Write Whatever ");
		
		String a = ae.nextLine();
		
		System.out.print("Write Whatever to connect it with the one above ");
		
		String b = ae.nextLine();
		
		String c = a+b;
		
		int d = c.length();
		
		System.out.print("This is the combined line, you wrote " + a + " " + b + ", ");
		
		System.out.print("and the length of the combined string is " + d);
		
		ae.close();
		
		}

	}

