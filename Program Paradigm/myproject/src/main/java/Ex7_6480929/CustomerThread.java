/**
 * @author Phakkhapon Kaewmanee
 */
package Ex7_6480929;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CustomerThread extends Thread {

    private Product product;
    private CyclicBarrier refundBarrier;
    private static int count = 1, orderAt = 1;
    private int customerNumber;
    private int transactionsLimit = 5, transactionNumber = 1;

    public CustomerThread(Product p, CyclicBarrier rb) {
        this.product = p;
        this.refundBarrier = rb;
        this.customerNumber = count++;
    }

    @Override
    public void run() {
        for (int i = 0; i < transactionsLimit; i++) {
            int rand = new Random().nextInt(101);
            int buyItems = product.buy(rand, customerNumber, transactionNumber);
            transactionNumber++;
            try {
                int order;
                synchronized (CustomerThread.class) {
                    order = orderAt++;
                }
                refundBarrier.await();
                System.out.printf("Customer %d >> ---------------  order at barrier = %2d\n", customerNumber, order);
                if (refundBarrier.getNumberWaiting() == 0) {
                    product.refund(buyItems, customerNumber);
                }
                refundBarrier.await();
                synchronized (CustomerThread.class) {
                    orderAt = 1;
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println(e);
            }
        }
    }

    public static void resetCount() {
        count = 1;
    }
}
