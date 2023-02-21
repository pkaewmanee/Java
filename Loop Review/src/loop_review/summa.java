package loop_review;

public class summa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double sum = 0;
		
		for(int i = 0; i<=999; i++) {
			sum += (Math.pow(1, i))/(Math.sqrt(i)+Math.sqrt(1+i));
		}
		System.out.print(sum);
	}

}
