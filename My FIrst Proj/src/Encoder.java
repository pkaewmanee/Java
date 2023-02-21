import java.util.Scanner;
public class Encoder {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Type any character: ");
		
		String a = sc.nextLine();
		
		switch(a){
		case "a":
			System.out.print("B");	
		break;
		case "A":
			System.out.print("B");	
		break;
		case "b":
			System.out.print("C");	
		break;
		case "B":
			System.out.print("C");	
		break;
		case "c":
			System.out.print("D");	
		break;
		case "C":
			System.out.print("D");	
		break;
		default:
		System.out.print("Z");
		break;
			
		}

	}

}
