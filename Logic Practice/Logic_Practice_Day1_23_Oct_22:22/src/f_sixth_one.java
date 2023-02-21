import java.util.Scanner;
public class f_sixth_one {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		for(int n = 1; n<=10; n++) {
			System.out.print(x + " x " + n + " = ");
			System.out.println(x*n);
			sc.close();
		}
	}

}
