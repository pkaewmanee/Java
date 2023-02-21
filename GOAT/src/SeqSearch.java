
public class SeqSearch {

	public static void main(String[] args) {
		int a [] = new int[10];
		for (int i =0; i<a.length-1; i++) {
			a[i] = a[++i]+i;
		}
		for(int i=0; i<a.length;i++) {
			System.out.println(a[i]);
		}
		
		int [] [] b = new int [5] [4];
		
		System.out.println(b[0][0]);

	}

}
