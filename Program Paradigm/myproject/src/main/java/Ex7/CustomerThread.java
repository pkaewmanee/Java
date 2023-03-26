/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ex7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author fill
 */
class CustomerThread extends Thread {

    private Product product;
    private int transactions = 5;
    private CyclicBarrier refundBarrier;

    public CustomerThread(Product product, CyclicBarrier refundBarrier) {
        this.product = product;
        this.refundBarrier = refundBarrier;
    }

    public void run() {
        try {
            for (int i = 0; i < transactions; i++) {
                int itemsToBuy = product.buy();
                System.out.println("Customer " + getId() + " >> transaction = " + (i + 1) + " buys " + itemsToBuy + " items balance = " + product.getBalance());
                refundBarrier.await();
                System.out.println("Customer " + getId() + " >> ------------------ order at barrier = " + (refundBarrier.await() + 1));
                if (refundBarrier.getNumberWaiting() == refundBarrier.getParties() - 1) {
                    product.refund(itemsToBuy);
                    System.out.println("Customer " + getId() + " >> ------------------ refunds " + itemsToBuy + " items balance " + product.getBalance());
                    refundBarrier.await();
                } else {
                    refundBarrier.await();
                }
                refundBarrier.await();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println("Exception caught: " + e.getMessage());
        }
    }

}
