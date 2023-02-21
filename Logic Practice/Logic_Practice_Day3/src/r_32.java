import java.util.Scanner;
public class r_32 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int first = sc.nextInt();
		int second = sc.nextInt();
		if(first != second) System.out.println(first + " != " + second);
		if(first > second) System.out.println(first + " > " + second);
		if(first < second) System.out.println(first + " < " + second);
		if(first >= second) System.out.println(first + " >= " + second);
		if(first <= second) System.out.println(first + " <= " + second);
	}

}
