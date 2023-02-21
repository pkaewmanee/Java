import java.util.Scanner;
public class RPS {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What is your move?");
		
		String a = sc.nextLine();
		
		System.out.println("What is the opponent's move?");
		
		String b = sc.nextLine();
		
		if (a.equalsIgnoreCase("R") && b.equalsIgnoreCase("P")) {
			System.out.print("You lose");
		}
		else if (a.equalsIgnoreCase("R") && b.equalsIgnoreCase("R")) {
			System.out.print("You draw");
		}
		
		else if (a.equalsIgnoreCase("R") && b.equalsIgnoreCase("S")) {
			System.out.print("You win");
		}
		
		else if (a.equalsIgnoreCase("P") && b.equalsIgnoreCase("P")) {
			System.out.print("You draw");
		}
		
		else if (a.equalsIgnoreCase("P") && b.equalsIgnoreCase("R")) {
			System.out.print("You win");
		}
		
		else if (a.equalsIgnoreCase("P") && b.equalsIgnoreCase("S")) {
			System.out.print("You lose");
		}
		
		else if (a.equalsIgnoreCase("S")  && b.equalsIgnoreCase("S")) {
			System.out.print("You draw");
		}
		
		else if (a.equalsIgnoreCase("S") && b.equalsIgnoreCase("R")) {
			System.out.print("You lose");
		}
		
		else if (a.equalsIgnoreCase("S") && b.equalsIgnoreCase("P")) {
			System.out.print("You win");
		}
		
		else {
			System.out.print("Illegal move, please re-run the program!");
			System.exit(0);
		}
		sc.close();
	}

}