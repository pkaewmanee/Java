/**
 * @author Phakkhapon Kaewmanee
 */
package Ex7_6480929;

public class Product {

    private int balance;

    public Product(int b) {
        this.balance = b;
    }

    public int buy(int itemsToBuy, int customerId, int transactionId) {
        int itemsBought = Math.min(itemsToBuy, balance);
        balance -= itemsBought;
        System.out.printf("Customer %d >> transaction = %d  buys       %3d items    balance = %3d\n", customerId, transactionId, itemsBought, balance);
        return itemsBought;
    }

    public synchronized void refund(int items, int customerId) {
        balance += items;
        System.out.printf("Customer %d >> ---------------  refunds    %3d items    balance = %3d\n", customerId, items, balance);
    }

    public int getBalance() {
        return balance;
    }
}
