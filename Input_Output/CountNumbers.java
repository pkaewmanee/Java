
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CountNumbers {

    public static int greater(int[] a,int v){
        int greater_than_counter = 0;
        int size = a.length;
        for(int i=0;i<size;i++){
            if(a[i]>v) greater_than_counter +=1;
        }
        
        return greater_than_counter;
    }
    public static void main(String[] args) throws IOException {
        File numbers = new File(args[0]);
        Scanner sc = new Scanner(numbers);
        int count = 0;
        while(sc.hasNextInt()){
            sc.nextInt();
            count++;
        }
        
        sc.close();
        sc = new Scanner(numbers);
        
        int[] arr = new int[count];
        for(int i=0;i<count;i++){
            arr[i] = sc.nextInt();
        }
        
        System.out.println("The number of integers greater than 25: "+greater(arr,25));
        System.out.println("The number of integers greater than 50: "+greater(arr,50));
        System.out.println("The number of integers greater than 75: "+greater(arr,75));
    }
    
}
