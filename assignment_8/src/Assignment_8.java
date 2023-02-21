/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Iterator;

/**
 *
 * @author Kpatt
 */
public class Assignment_8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println(nextValue("ABC"));
        //System.out.println(sumDigits(234));
        printPattern(4);
    }
    public static String nextValue(String s){
        return s.substring(0,s.length()-1)+(char)(s.charAt(s.length()-1)+1);
    }
    
    public static int sumDigits(int n){
        int sum=0;
        while(n>0){
            sum+= n%10;
            n = n/10;
        }
        return sum;
    }
    
    public static void printPattern(int n){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}
