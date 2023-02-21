import java.util.Scanner;
public class p_sixthteen_seventhteen_eighteen_ninteen_twenty_one {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("For Decimal to Binary: 1 "
				+ "\nFor Decimal to Hex: 2 "
				+ "\nFor Decimal to Oct: 3"
				+ "\nFor Binary to Decimal: 4 "
				+ "\nFor Hex to Decimal: 5"
				+ "\nFor Oct to Decimal: 6");
		int y = sc.nextInt();
		
		System.out.print("Enter the number you want to transfer: ");
		int x = sc.nextInt();
		
		//Still have room to improve on the Bi, Hex, Oct to Decimal
		
		switch(y) {
		case 1:
			System.out.println(IntoBi(x));
			break;
		case 2:
			System.out.println(IntoHex(x));
			break;
		case 3:
			System.out.println(IntoOct(x));
			break;
		case 4:
			System.out.println(BiToDec(x));
			break;
		case 5:
			System.out.println(HexToDec(x));
			break;
		case 6:
			System.out.println(OctToDec(x));
			break;
		}
		sc.close();

	}
	public static String IntoBi(int x) {
		String y = Integer.toBinaryString(x);
		
		return y;
	}
	public static String IntoHex(int x) {
		String z = Integer.toHexString(x);
		
		return z;
	}
	public static String IntoOct(int x) {
		String z1 = Integer.toOctalString(x);
		
		return z1;
	}
	public static int BiToDec(int x) {
		String z2 = IntoBi(x);
		int z3 = Integer.parseInt(z2, 2);
		return z3;
	}
	public static int HexToDec(int x) {
		String z2 = IntoHex(x);
		int z3 = Integer.parseInt(z2, 16);
		return z3;
	}
	public static int OctToDec(int x) {
		String z2 = IntoOct(x);
		int z3 = Integer.parseInt(z2, 8);
		return z3;
	}
}
