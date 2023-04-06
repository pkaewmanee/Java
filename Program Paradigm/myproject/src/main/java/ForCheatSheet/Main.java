package ForCheatSheet;

// Create a new class that extends Thread
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class ShuffleSortThread extends Thread {

    // Declare private fields for PrintWriter, list of numbers, and sort direction
    private PrintWriter out;
    private ArrayList<Integer> myNumbers;
    private boolean increasing;

    // Constructor to initialize fields
    public ShuffleSortThread(String name, ArrayList<Integer> org, boolean in) {
        // Call superclass constructor with name
        super(name);
        // Copy the original list into a new list
        myNumbers = new ArrayList<>(org);
        // Store sort direction
        increasing = in;
    }

    // Override the run() method
    @Override
    public void run() {
        try {
            // Open a PrintWriter to a file and print initial order and sort direction
            String loca = "/Users/fill/Desktop/Project_Hiroshima/Java/Program Paradigm/myproject/src/main/java/ForCheatSheet/";
            out = new PrintWriter(loca + getName() + ".txt");
            out.println("Initial order: " + myNumbers);
            out.println("=== Shuffle to " + (increasing ? "increasing" : "decreasing") + " order ===");

            // Declare variables for the number of rounds and whether the list is sorted
            int round = 0;
            boolean sorted = false;
            // Loop until the list is sorted
            while (!sorted) {
                // Shuffle the list
                Collections.shuffle(myNumbers);
                // Increment the round counter
                round++;

                // Print the new order to the file
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

            // Print the number of rounds to the console
            System.out.println(getName() + " sorted " + myNumbers.size() + " numbers in " + round + " rounds.");

        } catch (FileNotFoundException e) {
            // Handle file not found error
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Close the PrintWriter
            if (out != null) {
                out.close();
            }
        }
    }
}

// Main class
public class Main {

    public static void main(String[] args) {
        // Create a scanner to get input from the user
        Scanner scanner = new Scanner(System.in);
        // Ask the user for the number of values to sort
        System.out.print("Enter the number of values to be sorted: ");
        int n = scanner.nextInt();
        // Create an ArrayList containing 1, 2, ..., n
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            values.add(i);
        }
        // Shuffle the list to get an initial order
        Collections.shuffle(values);
        // Ask the user for the number of threads
        System.out.print("Enter the number of threads: ");
        int numThreads = scanner.nextInt();
        // Create an array of ShuffleSortThreads and start them with the shuffled list
        ShuffleSortThread[] threads = new ShuffleSortThread[numThreads];
        boolean increasing = true;
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new ShuffleSortThread("Thread_" + i, values, increasing);
            threads[i].start();
            increasing = !increasing;
        }
    }
}



