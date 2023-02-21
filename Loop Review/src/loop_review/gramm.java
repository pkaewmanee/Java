package loop_review;

import java.util.Scanner;

public class gramm {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter String: ");
		
		//next() read until first white space
		//nextLine() until find a enter
		
		String a_string = sc.nextLine();
		
		int j = 0;
		
		int k = 0;
		
		a_string = a_string.toLowerCase();
		
		for(int i = 0; i<= a_string.length()-1; i++) {
			
			if(a_string.charAt(i) == 'a' || a_string.charAt(i) == 'e' || a_string.charAt(i) == 'i'
					|| a_string.charAt(i) == 'o' || a_string.charAt(i) == 'u') {
				
				j++;
				
			}
			else {
				
				if ( a_string.charAt(i) != ' ') {
					
				k++;
				
				}
				
			}
			
		}
		System.out.print("The number of vowels is " + j + "\n" + 
				"The number of consonants is " + k);

	}

}
