/*
 * 6480929 Phakkhapon Kaewmanee
 * 6480279 Supakorn Unjindamanee
 */
package Ex2_6480929;

/*
import java.io.*;
import java.util.*;

public class Ex2_6480929 {

    public static void main(String[] args) {
        String path    = "src/main/java/Ex2_6480929/prices.txt/";
        Scanner ss = new Scanner(System.in);
        System.out.println("Enter lot size = ");
        int scn = ss.nextInt();
        System.out.println("Read product prices from " + path);
        System.out.println("Write output to " + outfile);
        
        try {
            Scanner sc  = new Scanner(new File(path));
            //PrintWriter write = new PrintWriter(new File(outfile));
            String[] name = new String[10];
            double[] price = new double[10];
            double[] price_vat = new double[10];
            for(int i = 0; i < 10; i++){
                name[i] = sc.next();
                price[i] = sc.nextDouble();
                price_vat[i] = price[i]/1.07;
                /*String vat = "VAT";
                System.out.printf("Product %20s",vat);
                System.out.printf("%20s","VAT");
                System.out.println(name[i] + " " + price_vat[i]*scn + " " + ((-price_vat[i] + price[i])*scn) + " " + price[i]*scn);
            }
        } catch(Exception e) {
          System.err.println("An error occurs. End program.");
          System.err.println(e);	  
	  //System.exit(-1);
	}
    }
    
}*/
