public class InsertArray {

	public static int[] insert(int[] a, int[] b, int pos) {
		//fill code
            int a_size = a.length;
            int b_size = b.length;
            int new_size = a_size+b_size;
            int[] a_b = new int[new_size];
            
            if(pos<0) pos=0;
            if(pos>a_size) pos = a_size;
            
            if(pos==0){ // b,a
                int i=0;
                for(;i<b_size;i++){
                    a_b[i] = b[i];
                }
                for(int j=0;j<a_size;j++,i++){
                    a_b[i] = a[j];
                }
            }
            else if(pos<a_size){ //a_before_pos,b,a_after_pos
                int i=0;
                for(;i<pos;i++){
                    a_b[i] = a[i];
                }
                for(int j=0;j<b_size;j++,i++){
                    a_b[i]=b[j];
                }
                for(int j=pos;j<a_size;j++,i++){
                    a_b[i] = a[j];
                }
            }
            else{ //a,b
                int i=0;
                for(;i<a_size;i++){
                    a_b[i] = a[i];
                }
                for(int j=0;j<b_size;j++,i++){
                    a_b[i] = b[j];
                }
            }
            
            return a_b;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 1, 2, 3, 4};
		int[] b = { 5, 6, 7};
		int[] c = { 8, 9, 10, 11, 12}; 

		System.out.print("Printing array a: ");  Replace.printArray(a);
		System.out.print("Printing array b: ");  Replace.printArray(b);
		System.out.print("Printing array c: ");  Replace.printArray(c);

		System.out.println("Now calling inserts!");
		System.out.println("------------------------");
		int[] d = insert(a,b,1); 
		int[] e = insert(a,b,4);
		int[] f = insert(b,a,0);
		int[] g = insert(c,a,3);
		int[] h = insert(c,a,-1);
		int[] i = insert(c,a,7); 

		System.out.print("Printing array a, expecting {1,2,3,4} : ");  Replace.printArray(a);
		System.out.print("Printing array b, expecting {5,6,7} : ");  Replace.printArray(b);
		System.out.print("Printing array c, expecting {8,9,10,11,12} : ");  Replace.printArray(c);
		System.out.print("Printing array d, expecting {1,5,6,7,2,3,4} : ");  Replace.printArray(d);
		System.out.print("Printing array e, expecting {1,2,3,4,5,6,7} : ");  Replace.printArray(e);
		System.out.print("Printing array f, expecting {1,2,3,4,5,6,7}: ");  Replace.printArray(f);
		System.out.print("Printing array g, expecting {8,9,10,1,2,3,4,11,12} : ");  Replace.printArray(g);
		System.out.print("Printing array h, expecting {1,2,3,4,8,9,10,11,12} : ");  Replace.printArray(h);
		System.out.print("Printing array i, expecting {8,9,10,11,12,1,2,3,4} : ");  Replace.printArray(i);

	}

}
