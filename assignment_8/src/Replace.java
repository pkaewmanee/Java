public class Replace {

	public static int[] replace(int[] a, int v, int v2) {
            if(a==null) return null;
            
            int size = a.length;
            
            for(int i=0;i<size;i++){
                if(v==a[i]) a[i] = v2;
            }
            
            return a;
	}
	
	public static void printArray(int[] a) {
            
            if(a==null){ // implicit ending of method.}
                System.out.println(); 
                return;
            }
            
            int size = a.length;
            
            System.out.print("{");
            
            for(int i=0;i<size-1;i++){
                System.out.print(a[i]+",");
            }
            
            System.out.println(a[size-1]+"}");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 1, 2, 3, 1, 5, 4, 1, 2, 3, 1 };
		int[] b = { 1, 5, 5, 1, 4, 5, 4, 2, 1, 5, 5, 2};
		int[] b2 = { 1, 5, 5, 1, 4, 5, 4, 2, 1, 5, 5, 2};
		int[] c = null;
		int[] d = { 1, 2, 3, 4}; 

		System.out.print("Printing array a: ");  printArray(a);
		System.out.print("Printing array b: ");  printArray(b);
		System.out.print("Printing array b2: ");  printArray(b2);
		System.out.print("Printing array c: ");  printArray(c);
		System.out.print("Printing array d: ");  printArray(d);

		System.out.println("Now calling replace in each!");
		replace(a,1,55);
		replace(b,5,66);
		replace(b2,4,77);
		replace(c,1,5);
		replace(d,5,7);

		System.out.println("Printing result of each replace!");
		System.out.print("Printing array a, replacing 1 with 55: ");  printArray(a);
		System.out.print("Printing array b, replacing 5 with 66: ");  printArray(b);
		System.out.print("Printing array b2, replacing 4 with 77: ");  printArray(b2);
		System.out.print("Printing array c, replacing 1 with 5: ");  printArray(c);
		System.out.print("Printing array d, replacing 5 with 7: ");  printArray(d);

	}

}
