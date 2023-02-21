/**
 *6480929 Phakkhapon Kaewmanee
 */
package Ex1_6480929;

import java.util.Scanner;

public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc =  new Scanner(System.in);
        System.out.println("Enter positive radius (or <=0 to exit): ");
        float r = sc.nextFloat();
        while(r>=0){
        float ssq = 2*r;
        float assq = ssq * ssq;
        
        float bsq = (float) (Math.sqrt(2.0)*r);
        float absq = bsq * bsq;
        System.out.printf("Smallest Square >> width = %.2f", ssq);
        System.out.printf(", area = %.2f", assq);
        System.out.print("\n");
        System.out.printf("Biggest Square >> width = %.2f", bsq);
        System.out.printf(", area = %.2f", absq);
        System.out.print("\n");
        
        System.out.println("Enter positive radius (or <=0 to exit): ");
        r = sc.nextFloat();
        }
        sc.close();
    }
    
}
