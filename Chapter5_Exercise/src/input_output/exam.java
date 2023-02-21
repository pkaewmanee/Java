package input_output;
import java.util.Scanner;
public class exam {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int score;
		
		do{
			
			System.out.print("Enter your score: ");
			
			score = sc.nextInt();
			
			if(score>=60) {
				System.out.println("You pass the exam \n");
			}
			else {
				System.out.println("You pass not the exam \n");
			}

		}while(!(score == -1));
		

	}

}
