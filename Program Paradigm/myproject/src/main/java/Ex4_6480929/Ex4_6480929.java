/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ex4_6480929;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Create a class called Airport that implements the Comparable interface.
class Airport implements Comparable<Airport> {
    // Define instance variables for the airport's name, code, number of passengers, runways, and terminals.
    private String name, code;
    private int passenger, runways, terminals;

    // Define a constructor that initializes the instance variables.
    public Airport(String name, String code, int passenger, int runways, int terminals) {
        this.name = name;
        this.code = code;
        this.passenger = passenger;
        this.runways = runways;
        this.terminals = terminals;
    }

    // Implement the compareTo method from the Comparable interface.
    @Override
    public int compareTo(Airport other) {
        // Compare airports by number of passengers, then by number of runways, then by number of terminals, then by name.
        int result = Integer.compare(other.passenger, this.passenger);
        result = (result != 0) ? result : Integer.compare(other.runways, this.runways);
        result = (result != 0) ? result : Integer.compare(other.terminals, this.terminals);
        result = (result != 0) ? result : Character.compare(this.name.charAt(0), other.name.charAt(0));
        return result;
    }

    // Define a method to print the airport's information.
    public void print() {
        System.out.printf("%-50s %12s %21s %17s\n", code + " " + name, passenger, runways, terminals);
    }
}

// Define the main class.
public class Ex4_6480929 {
    public static void main(String[] args) throws FileNotFoundException {
        // Define the path to the airports.txt file.
        String path = "src/main/java/Ex4_6480929/airports.txt/";

        // Create an ArrayList to store Airport objects.
        ArrayList<Airport> airportList = new ArrayList<>();
        // Read in the airports from the file and create Airport objects to add to the ArrayList.
        try (Scanner sc = new Scanner(new File(path))) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] buf = line.split(",");
                airportList.add(new Airport(buf[0].trim(), buf[1].trim(), Integer.parseInt(buf[2].trim()),
                        Integer.parseInt(buf[3].trim()), Integer.parseInt(buf[4].trim())));
            }
        }

        // Sort the ArrayList of Airport objects.
        Collections.sort(airportList);

        // Print the headers for the table of airport information.
        System.out.printf("%-50s %18s %18s %18s\n", "Airport", "Passenger (M)", "Runways", "Terminals");
        System.out.println("============================================================================================================");

        // Print the information for each airport in the ArrayList.
        for (Airport airport : airportList) {
            airport.print();
        }
    }
}

