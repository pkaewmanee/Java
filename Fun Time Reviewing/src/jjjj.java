
public class jjjj {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(sumSigits(284));
	}
	
	public static int sumSigits(long n) {
		long m = 0;
		
		while (n > 0) {
			m += n % 10;
			n = n / 10;
			
		}
		return (int)m;
	}

}
