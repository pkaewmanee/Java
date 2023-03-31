/**
 * @author Phakkhapon Kaewmanee
 */
package Ex7_6480929;

/**
 *
 * @author fill
 */
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueShopping = true;
        while (continueShopping) {
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

            while (true) {
                System.out.println("main >> Enter y/Y to continue, others to quit = ");
                String answer = scanner.next();
                if (answer.equals("y") || answer.equals("Y")) {
                    CustomerThread.resetCount();
                    break;
                } else {
                    continueShopping = false;
                    break;
                }
            }
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
