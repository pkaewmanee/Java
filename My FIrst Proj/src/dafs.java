import java.util.Scanner;
public class dafs {
	public static void main(String []Main) {
		Scanner s = new Scanner(System.in);
		String t = s.nextLine();
		int max = Integer.MIN_VALUE;
		boolean neg = false;
		
		
		for (int n = 0; n < t.length(); n++) {
			if(t.charAt(n) == '-') {
				neg = true;
			}
			else {
				max = Math.max(max, (neg?-1:1) * Character.getNumericValue(t.charAt(n)));
				neg = false;
			}
		}
		
		System.out.println(max);
		System.out.println(3/4*(4/3));
	}
}
