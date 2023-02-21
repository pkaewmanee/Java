import java.util.Scanner;
import java.util.InputMismatchException;
public class TranscriptForThreeCoursesWithTryCatch {
	
	public static void main(String[] args) {
		
		Scanner ab = new Scanner(System.in);
		System.out.print("Enter student id: ");
		StudentGPAX s1 = new StudentGPAX(ab.nextLine());
		String s1_courseID = null;
		double s1_credit, in_grade;
		
		try {
			for (int i =1; i<=3; i++) {
				System.out.print("Enter course" + i + "'s id: ");
				s1_courseID = ab.nextLine();
				System.out.print("Enter course" + i + "'s credit: ");
				s1_credit = ab.nextDouble();
				ab.nextLine();
				System.out.print("Enter course" + i + "'s grade: ");
				in_grade = ab.nextDouble();
				ab.nextLine();
				System.out.println();
				s1.addCourseGrade(s1_courseID, s1_credit, in_grade);	
			}
			s1.computeGPAX();
			
		} catch (InputMismatchException a) {
			System.err.println("ERROR: credit and grade must be numeric!");
			System.exit(0);
		} catch (ArithmeticException a) {
			System.err.println("ERROR: credit must greater than 0!");
			System.exit(0);
		}
		ab.close();
		System.out.printf("GPAX of %s is %.2f (%.2f/%.2f) \n" ,s1.studentID, s1.gpax, s1.totalGradePoint, s1.totalCredit);
}
	}
