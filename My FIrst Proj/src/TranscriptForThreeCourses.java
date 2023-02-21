import java.util.Scanner;
public class TranscriptForThreeCourses {
	
		public static void main(String [] args) {
			
			Scanner bbc = new Scanner(System.in);
			System.out.print("Enter student id: ");
			StudentGPAX s1 = new StudentGPAX(bbc.nextLine());
			String s1_courseID = null;
			double s1_credit, in_grade;
			
			for (int i =1; i<=3; i++) {
				System.out.print("Enter course" + i + "'s id: ");
				s1_courseID = bbc.nextLine();
				System.out.print("Enter course" + i + "'s credit: ");
				s1_credit = bbc.nextDouble();
				bbc.nextLine();
				System.out.print("Enter course" + i + "'s grade: ");
				in_grade = bbc.nextDouble();
				bbc.nextLine();
				System.out.println();
				s1.addCourseGrade(s1_courseID, s1_credit, in_grade);	
			}
			bbc.close();
			s1.computeGPAX();
			
			System.out.printf("GPAX of %s is %.2f (%.2f/%.2f) \n" ,s1.studentID, s1.gpax, s1.totalGradePoint, s1.totalCredit);
			
			boolean a = StudentGPAX.MIN_CREDIT < s1.totalCredit;
			boolean b = StudentGPAX.MIN_GPAX < s1.gpax;		
			
			if (a&&b) {
				System.out.println("Finish the program: true");
				}
				else {
				System.out.println("Finish the program: false"  );
				}
			
			
			
		}
	}
