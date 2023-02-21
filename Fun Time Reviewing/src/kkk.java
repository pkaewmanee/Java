
public class kkk {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*System.out.print( reverse(456));
		System.out.print( isPalindrome(456));*/
		displayPattern(3);
	}
	
	public static int reverse(int number) {
		String m = "";
		while(number >0){
			m += "" + number%10;
			number = number/10;
		}
		return Integer.parseInt(m) ;
	}
	
	public static boolean isPalindrome (int number) {
		return number == reverse(number);
	}
	
	public static void displayPattern(int n) {
		for ( int m = 1; m<=n; m++) {
			
			for (int o = 1; o<=m; o++) {
				System.out.print(o + " ");
			}
			
			System.out.println();
		}
	}

}
