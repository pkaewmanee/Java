package input_output;

import java.util.Scanner;

public class fourdig {

	public static void main(String[] args) {
		
		Scanner ab = new Scanner(System.in);
		
		System.out.print("Enter four-digit integer: ");
		
		int a = ab.nextInt();
		
		boolean b = 999 < a; 
		boolean c = a>10000;
		
		if ( b&&c ) {
			System.exit(0);
		}
		else {
			System.out.println("Something is wrong");
			
			if( b||c ){
				System.out.println();
				}
			else if( !b && c){
				
			}
			}

		String d = a + " ";
		
		System.out.println( "The Reverse Order Should Look Like\n" + d.charAt(3) +"\n" + d.charAt(2) + "\n" + d.charAt(1) + "\n" + d.charAt(0));
		
	}


/* Controlling the Flow
 * 
 * If
 * 
 * if-else
 * 
 * switch
 * 
 * 
 * 
 * 
 */
*/