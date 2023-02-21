/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ex5_6480929;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Create a class representing an airport

class Airport implements Comparable<Airport> {

    private String airport_name, airport_code;
    private int passenger, runways, terminals;

    // Constructor for an airport, taking its name, code, passenger count, runway count, and terminal count
    public Airport(String name, String code, int p, int r, int t) {
        airport_name = name;
        airport_code = code;
        passenger = p;
        runways = r;
        terminals = t;
    }

    // Compare two airports for sorting purposes
    @Override
    public int compareTo(Airport other) {
        // Sort by decreasing passenger count
        if (this.passenger != other.passenger) {
            return Integer.compare(other.passenger, this.passenger);
        }
        // If passenger count is equal, sort by decreasing runway count
        if (this.runways != other.runways) {
            return Integer.compare(other.runways, this.runways);
        }
        // If runway count is also equal, sort by increasing terminal count
        if (this.terminals != other.terminals) {
            return Integer.compare(this.terminals, other.terminals);
        }
        // If all other fields are equal, sort by airport name
        return this.airport_name.compareTo(other.airport_name);
    }

    // Print an airport's information in a formatted way
    public void print() {
        System.out.printf("%-50s %12d %12d %12d\n", airport_code + " " + airport_name, 
                passenger, runways, terminals);
    }
}

// Main class of the program
public class Ex5_6480929 {

    public static void main(String[] args) {
        // Set the path to the input file
        String path = "src/main/java/Ex4_6480929/airports.txt/";
        // Initialize a scanner and fileFound boolean for error handling
        Scanner sc = null;
        boolean fileFound = false;
        while (!fileFound) {
            try {
                // Attempt to read the input file
                sc = new Scanner(new File(path));
                // Create an ArrayList to store airport objects
                ArrayList<Airport> airportList = new ArrayList<>();
                // Read in each line of the input file
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    // Split the line into tokens separated by commas
                    String[] tokens = line.split(",");
                    // Ensure that each line has the correct number of tokens
                    if (tokens.length != 5) {
                        throw new RuntimeException("Invalid input: " + line);
                    }
                    // Extract the relevant data from the tokens
                    String name = tokens[0].trim();
                    String code = tokens[1].trim();
                    int passenger = Integer.parseInt(tokens[2].trim());
                    int runways = Integer.parseInt(tokens[3].trim());
                    int terminals = Integer.parseInt(tokens[4].trim());
                    // Ensure that the airport code is the correct length
                    if (code.length() != 3) {
                        throw new RuntimeException("Invalid airport code: " + code);
                    }
                    // Ensure that the other fields are non-negative
                    if (passenger < 0 || runways < 0 || terminals < 0) {
                        throw new RuntimeException("Negative value");
                    }
                    // Create a new airport object and add it to the ArrayList
                    airportList.add(new Airport(name, code, passenger, runways, terminals));
                }

                Collections.sort(airportList);
                System.out.printf("%-50s %12s %12s %12s\n", "Airport", "Passenger (M)", "Runways", "Terminals");
                System.out.println("===========================================================");
                for (Airport a : airportList) {
                    a.print();
                }
                fileFound = true;
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
                System.out.print("Enter new file name: ");
                Scanner sc_err = new Scanner(System.in);
                String new_path = sc_err.nextLine();
                path = "src/main/java/Ex5_6480929/" + new_path + "/";
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } finally {
                if (sc != null) {
                    sc.close();
                }
            }
        }
    }
}





