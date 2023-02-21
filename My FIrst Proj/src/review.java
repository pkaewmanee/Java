import java.util.Scanner;
public class review {
	
	int j = 0;
	int k = 0;
	int l;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		String s1 = sc.nextLine();
		
		int j = 0;
		int k = 0;
		String s2 = " ";
		
		//System.out.println(s1.substring(s1.indexOf("Java")).toUpperCase());
		
		for(int n = 0; n <= s1.length()-1 ; n++) {
			
			s2 = s1.charAt(n) + "";
			
			System.out.print(s2.toUpperCase());
			
			if(s1.charAt(n) == 'a' || s1.charAt(n) == 'e' || s1.charAt(n) == 'i' ||
					s1.charAt(n) == 'o' || s1.charAt(n) == 'u') {
				j++;
			}
			else if (s1.charAt(n) != ' '){
				k++;
			}
		}
		
		int i = 0;
		
		do {
			i++;
		}while(i<3);
		
		switch(i){
		
		case 0:
			i = 1;
			
		case 1:
			i=2;
			
		case 2:
			i=3;
			break;		
			
		case 3:
			i=4;
			break;
			
		default:
			
			i = 5;
			break;
		}
		
		System.out.println("\n" + j);
		System.out.println(k);
		System.out.println(i);
		System.out.println(s1.length());
		System.out.println(s1.charAt(0));
		System.out.println(s1.indexOf(4));
		System.out.println(s1.substring(5, 10));
		
		sc.close();
	}

}
