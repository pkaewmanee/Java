public class IsReverse {

	public static boolean isReverse(int[] a, int[] b) {
            // refer to same data.
            if(a==b){
                return true;
            }
            
            // one null, another not null
            if(a==null || b==null){
                return false;
            }
           
            int a_size = a.length;
            int b_size = b.length;
            
            // different size, different reveresed look
            if(a_size != b_size){
                return false;
            }
            
            // compare element wise
            for(int i=0;i<a_size;i++){ // i<b_size also work.
                if(a[i] != b[b_size-i-1]) return false;
            }
            
            return true;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 2, 3, 4, 5 };
		int[] b = { 5, 4, 3, 2, 2, 1 };
		int[] c = { 1, 2, 0, 3, 4, 5 };
		int[] d = { 1, 2, 2, 3, 4, 5, 0 };
		int[] e = { 0, 5, 4, 3, 2, 2, 1 };
		int[] f = null;
		int[] g = null;

		System.out.println("isReverse(a,b): "+ isReverse(a,b) + ", expected to be true");

		System.out.println("isReverse(b,c):" +isReverse(b,c) + ", expected to be false");
		System.out.println("isReverse(a,c):" +isReverse(a,c) + ", expected to be false");
		System.out.println("isReverse(d,e):" +isReverse(d,e) + ", expected to be true");
		System.out.println("isReverse(e,d):" +isReverse(e,d) + ", expected to be true");
		System.out.println("isReverse(d,b):" +isReverse(b,d) + ", expected to be false");
		System.out.println("isReverse(f,g):" +isReverse(f,g) + ", expected to be true");
		System.out.println("isReverse(f,a):" +isReverse(f,a) + ", expected to be false");
		System.out.println("isReverse(a,f):" +isReverse(a,f) + ", expected to be false");

	}

}
