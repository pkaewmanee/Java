public class StudentGPAX {
	static double MIN_CREDIT = 3;
	static double MIN_GPAX = 2;

	String studentID;
	double totalCredit = 0;
	double totalGradePoint = 0;
	int totalCourse = 0;
	double gpax = 0;

	public StudentGPAX(String studentID) {
		this.studentID = studentID;
	}

	public void addCourseGrade(String courseID, double credit, double grade) {
		this.totalCredit += credit;
		this.totalGradePoint += (grade * credit);
		++this.totalCourse;
	}
	
	public void computeGPAX() {
		if(this.totalCredit==0.0) throw new ArithmeticException();
		this.gpax = this.totalGradePoint / this.totalCredit;
	}
		
}









/*
double credit1 =0 , credit2 = 0, credit3=0, grade1=0, grade2=0, grade3=0;

try {
	credit1 = bbc.nextDouble();
	credit2 = bbc.nextDouble();
	credit3 = bbc.nextDouble();
	grade1 = bbc.nextDouble();
	grade2 = bbc.nextDouble();
	grade3= bbc.nextDouble();
	
} catch (InputMismatchException e) {
	System.out.println("Error: you must enter numbers!");
}
*/