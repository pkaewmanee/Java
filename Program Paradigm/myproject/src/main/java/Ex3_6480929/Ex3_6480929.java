//6480929 Phakkhapon Kaewmanee
package Ex3_6480929;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// Base class for all films
class FilmBase {

    // Declare instance variables
    protected String name;
    protected int age, currentYear = 2023;

    // Constructor
    public FilmBase(String n, int a) {
        // Set instance variables
        name = n;
        age = a;
    }

    // Method to print film details
    public void print() {
        // Calculate the year the film was released
        String year = "(" + (currentYear - age) + ")";
        // Print the film name and year
        System.out.printf("%-20s %6s", name, year);
    }
}

// Subclass for live-action films
class LiveAction extends FilmBase {

    // Declare additional instance variables
    protected String director;
    protected int gross;

    // Constructor
    public LiveAction(String n, int a, String d, int g) {
        // Call superclass constructor
        super(n, a);
        // Set additional instance variables
        director = d;
        gross = g;
    }

    // Method to print live-action film details
    @Override
    public void print() {
        // Call superclass print method to print name and year
        super.print();
        // Calculate opening gross in millions of THB
        int thb = (gross * 35);
        // Create formatter to format gross as a number with commas
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        // Format opening gross as a string with the correct format
        String th_gross = "opening gross = " + formatter.format(thb) + " million THB";
        // Print director and opening gross
        System.out.printf("%-35s %-20s\n", "Director = " + director.trim(), th_gross);
    }
};

// Subclass for animated films
class Animation extends FilmBase {

    // Declare additional instance variable
    protected int min;

    // Constructor
    public Animation(String n, int a, int m) {
        // Call superclass constructor
        super(n, a);
        // Set additional instance variable
        min = m;
    }

    // Method to print animation details
    @Override
    public void print() {
        // Call superclass print method to print name and year
        super.print();
        // Convert minutes to hours and minutes
        double hrs = min / 60;
        int minute = min % 60;
        // Format hours and minutes as a string
        String hm = ((int) hrs + " hrs, " + minute + " mins");
        // Print hours and minutes
        System.out.printf("%18s\n", hm);
    }
};

// Main class
public class Ex3_6480929 {

    public static void main(String[] args) throws FileNotFoundException {
        // Path to the file containing film data
        String path = "src/main/java/Ex3_6480929/allFilms.txt/";

        // Attempt to read the file
        try (Scanner sc = new Scanner(new File(path))) {
            // Create an ArrayList to hold the films
            ArrayList<FilmBase> films = new ArrayList<>();

            // Read each line of the file
            while (sc.hasNext()) {
                // Split the line by comma into an array of strings
                String line = sc.nextLine();
                String[] buf = line.split(",");
                String name = buf[1].trim(); // Extract the film name
                int age = Integer.parseInt(buf[2].trim()); // Extract the film age

                // Check if the film is live action or animation
                if (buf.length == 5 && buf[0].equals("L")) {
                    int gross = Integer.parseInt(buf[4].trim()); // Extract the gross
                    // Create a new LiveAction object with the extracted data and add it to the ArrayList
                    LiveAction la = new LiveAction(name, age, buf[3], gross);
                    films.add(la);
                } else {
                    int min = Integer.parseInt(buf[3].trim()); // Extract the length of the animation in minutes
                    // Create a new Animation object with the extracted data and add it to the ArrayList
                    Animation a = new Animation(name, age, min);
                    films.add(a);
                }
            }

            // Print all films in the ArrayList
            System.out.println("=== Both LiveAction and Animation ===\"");
            for (FilmBase film : films) {
                film.print();
            }

            // Print only the LiveAction films in the ArrayList
            System.out.println("\n=== LiveAction only ===");
            for (FilmBase film : films) {
                if (film instanceof LiveAction) {
                    film.print();
                }
            }

            // Print only the Animation films in the ArrayList
            System.out.println("\n=== Animation only ===");
            for (FilmBase film : films) {
                if (film instanceof Animation) {
                    film.print();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

}

