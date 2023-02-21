package input_output;
import java.util.Scanner;
public class bmithingy {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter weight in pounds: ");
		
		double weight = sc.nextDouble();
		
		System.out.print("Enter feet: ");
		
		double feet = sc.nextDouble();
		
		System.out.print("Enter inches: ");
		
		double inches = sc.nextDouble();
		
		double conv = feet*12;
		
		double foot = conv + inches;
		
		double bmi = (703 * weight)/(Math.pow(foot, 2));
		
		if(bmi<18.5) {
			System.out.println("Your BMI is " + bmi);
			System.out.print("Which is underweight");
		}
		
		else if(bmi>18.5 && bmi<24.9) {
			System.out.println("Your BMI is " + bmi);
			System.out.print("Which is normal");
		}
		
		else if(bmi>25 && bmi<29.9) {
			System.out.println("Your BMI is " + bmi);
			System.out.print("Which is overweight");
		}
		
		else if(bmi>30) {
			System.out.println("Your BMI is " + bmi);
			System.out.print("Which is obese");
		}
		
		sc.close();

	}

}
