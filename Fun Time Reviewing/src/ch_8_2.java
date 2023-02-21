public class ch_8_2{
	public static void main(String []args){ 
	System.out.println(	nextValue(1));
	}

	public static char nextValue(char y){
		return (char) (y+1);
		
	}

		public static int nextValue(int y){
			return (int) y+1;
	}

		public static float nextValue(float y){
			return (float) (y+1.0);
	}

		public static double nextValue(double y){
			return y+1;
	}

		public static String nextValue(String y){
			
			return y.substring(0, y.length()-1) + (char) (y.charAt(y.length()-1) + 1 );
		
			
	}
}


/*for( int n = 0; n < y.length(); n++ ) {
	
}
*/