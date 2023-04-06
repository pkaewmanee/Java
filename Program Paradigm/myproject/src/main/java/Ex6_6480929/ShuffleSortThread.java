//6480929 Phakkhapon Kaewmanee
package Ex6_6480929;

import java.io.*;
import java.util.*;

public class ShuffleSortThread extends Thread {

    private PrintWriter out;
    private ArrayList<Integer> myNumbers;
    private boolean increasing;

    public ShuffleSortThread(String name, ArrayList<Integer> org, boolean in) {
        super(name);
        myNumbers = new ArrayList<Integer>(org);
        increasing = in;
    }

    @Override
    public void run() {
        try {
            // 1. Print initial order and sorting direction to file
            String loca = "/Users/fill/Desktop/Project_Hiroshima/Java/Program Paradigm/myproject/src/main/java/Ex6_6480929/";
            out = new PrintWriter(loca + getName() + ".txt");
            out.println("Initial order: " + myNumbers);
            out.println("=== Shuffle to " + (increasing ? "increasing" : "decreasing") + " order ===");

            int round = 0;
            boolean sorted = false;
            while (!sorted) {
                // 2. Keep shuffling the numbers until they are sorted
                Collections.shuffle(myNumbers);
                round++;

                // 3. Print round number & the new order to file
                out.println("Round " + round + ": " + myNumbers);

                // Check if the list is sorted
                sorted = true;
                for (int i = 0; i < myNumbers.size() - 1; i++) {
                    if (increasing && myNumbers.get(i) > myNumbers.get(i + 1)) { //increasing
                        sorted = false;
                        break;
                    } else if (!increasing && myNumbers.get(i) < myNumbers.get(i + 1)) { //descreasing
                        sorted = false;
                        break;
                    }
                }
            }

            // 4. Once the numbers are sorted, report #rounds to screen
            System.out.println(getName() + " sorted " + myNumbers.size() + " numbers in " + round + " rounds.");

        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
