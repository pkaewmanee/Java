
public class x_38 {

	public static void main(String[] args) {
		String x = "Aa kiu, I swd skieo 236587. GH kiu: sieo?? 25.33";
		int y = 0;
		int z = 0;
		int a = 0;
		int b = 0;
		for(int i = x.length()-1; i >= 0; i--) {
			if(x.charAt(i) >= 'a' && x.charAt(i) <= 'z' || x.charAt(i) >= 'A' && x.charAt(i) <= 'Z') y++;
			else if(x.charAt(i) >= '0' && x.charAt(i) <= '9') a++;
			else if(x.charAt(i) == ' ') z++;
			else b++;
			
		}
		System.out.println("The String is " + x);
		System.out.println("letter: " + y);
		System.out.println("space: " + z);
		System.out.println("number: " + a);
		System.out.println("other: " + b);
		System.out.println(x.charAt(x.length()-1-0));
	}

}
