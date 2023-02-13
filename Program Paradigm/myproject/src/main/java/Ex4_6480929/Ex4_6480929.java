/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ex4_6480929;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author fill
 */
class Airport implements Comparable {

    private String airport_name, airport_code;
    private int passenger, runways, terminals;

    public Airport(String a, String b, int c, int d, int e) {
        airport_name = a;
        airport_code = b;
        passenger = c;
        runways = d;
        terminals = e;
    }

    @Override
    public int compareTo(Object param) {
        // No need for type casting if param type = MyPoint
        Airport other = (Airport) param;

        if (this.passenger < other.passenger) {
            return 1;
        } else if (this.passenger > other.passenger) {
            return -1;
        } else {
            if (this.runways < other.runways) {
                return 1;
            } else if (this.runways > other.runways) {
                return -1;
            } else {
                if (this.terminals < other.terminals) {
                    return 1;
                } else if (this.terminals > other.terminals) {
                    return -1;
                } else {
                    if (this.airport_name.charAt(0) < other.airport_name.charAt(0)) {
                        return -1;
                    } else if (this.airport_name.charAt(0) > other.airport_name.charAt(0)) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    public void print() {
        System.out.printf("%-50s %12s %21s %17s\n", airport_code + " " + airport_name, passenger, runways, terminals);
    }

}

public class Ex4_6480929 {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String path = "src/main/java/Ex4_6480929/airports.txt/";
        try (Scanner sc = new Scanner(new File(path))) {
            Airport[] ap = new Airport[23];
            int i = 0;
            
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] buf = line.split(",");
                ap[i] = new Airport(buf[0].trim(), buf[1].trim(), Integer.parseInt(buf[2].trim()), Integer.parseInt(buf[3].trim()), Integer.parseInt(buf[4].trim()));
                i++;
            }
            
            ArrayList<Airport> AL = new ArrayList<>();
            for (i = 0; i < ap.length; i++) {
                AL.add(ap[i]);
            }
            Collections.sort(AL);
            System.out.printf("%-50s %18s %18s %18s\n", "Airport", "Passenger (M)", "Runways", "Terminals");
            System.out.println("============================================================================================================");
            for (Airport p : AL) {
                p.print();
            }
        }
    }
}
