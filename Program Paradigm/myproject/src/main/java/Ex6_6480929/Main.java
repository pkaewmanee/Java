//6480929 Phakkhapon Kaewmanee

package Ex6_6480929;

import java.util.*;

public class Main {
   public static void main(String[] args) {
      // 2.1 Ask user for #values (e.g. n) to be sorted
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter the number of values to be sorted: ");
      int n = scanner.nextInt();
      
      // Create an ArrayList containing 1, 2, ..., n
      ArrayList<Integer> values = new ArrayList<Integer>();
      for (int i = 1; i <= n; i++) {
         values.add(i);
      }
      
      // Shuffle the list to get an initial order
      Collections.shuffle(values);

      // 2.2 Ask user for #threads
      System.out.print("Enter the number of threads: ");
      int numThreads = scanner.nextInt();
      
      // Create ShuffleSortThreads and start them with the shuffled list
      ShuffleSortThread[] threads = new ShuffleSortThread[numThreads];
      boolean increasing = true;
      for (int i = 0; i < numThreads; i++) {
         threads[i] = new ShuffleSortThread("Thread_" + i, values, increasing);
         threads[i].start();
         increasing = !increasing;
      }
   }
}

