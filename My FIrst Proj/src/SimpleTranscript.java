
public class SimpleTranscript {
	public static void main(String []args) {
		StudentGPAX s1 = new StudentGPAX("6031707921");
		StudentGPAX s2 = new StudentGPAX("6031707921");
		s1.addCourseGrade("2190101", 3, 4);
		s1.addCourseGrade("2190151", 1, 2);
		s1.addCourseGrade("2301107", 3, 4);
		s1.computeGPAX();
		s2.addCourseGrade("2304153", 3, 1);
		s2.addCourseGrade("2304192", 1, 2);
		s2.computeGPAX();
		System.out.printf("GPAX of %s is %.2f (%.2f/%.2f) \n" ,s1.studentID, s1.gpax, s1.totalGradePoint, s1.totalCredit);
		System.out.printf("GPAX of %s is %.2f (%.2f/%.2f) \n" ,s2.studentID, s2.gpax, s2.totalGradePoint, s2.totalCredit);
		
		
	}
}
