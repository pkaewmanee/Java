package input_output;
import java.util.Scanner;
public class Lottery {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String lottery = (int)(Math.random()*10)+""+(int)(Math.random()*10)+(int)(Math.random()*10);
	    
		System.out.println(lottery);
		
		System.out.print("Enter any three digit number: ");
		
		String num1 = sc.next();
		
		if(num1.equals(lottery)) {
			System.out.print("You win $12000, nice");
		}
		
		else if((num1.charAt(0) == lottery.charAt(0) || num1.charAt(0) == lottery.charAt(1) || num1.charAt(0) == lottery.charAt(2))||
				(num1.charAt(1) == lottery.charAt(0) || num1.charAt(1) == lottery.charAt(1) || num1.charAt(1) == lottery.charAt(2)) || (num1.charAt(2) == lottery.charAt(0) || num1.charAt(2) == lottery.charAt(1) || num1.charAt(2) == lottery.charAt(2))) {
			System.out.print("You win $5000, nice");
		}
		
		
		
			
		}

	}

