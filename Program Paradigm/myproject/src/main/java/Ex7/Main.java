/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ex7;

/**
 *
 * @author fill
 */
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int initialBalance = askForInitialBalance(scanner);
        Product product = new Product(initialBalance);

        int numCustomers = askForNumCustomers(scanner);
        CyclicBarrier refundBarrier = new CyclicBarrier(numCustomers, new Runnable() {
            public void run() {
                System.out.print("");
            }
        });

        CustomerThread[] customers = new CustomerThread[numCustomers];
        for (int i = 0; i < numCustomers; i++) {
            customers[i] = new CustomerThread(product, refundBarrier);
            customers[i].start();
        }

        try {
            for (int i = 0; i < numCustomers; i++) {
                customers[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static int askForInitialBalance(Scanner scanner) {
        System.out.println("Enter initial balance:");
        return scanner.nextInt();
    }

    private static int askForNumCustomers(Scanner scanner) {
        System.out.println("Enter number of customers:");
        return scanner.nextInt();
    }
}
