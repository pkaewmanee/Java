/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ex7;

import java.util.Random;

/**
 *
 * @author fill
 */
public class Product {

    private int balance;

    public int buy() {
        Random rand = new Random();
        int itemsToBuy = rand.nextInt(101);
        itemsToBuy = Math.min(itemsToBuy, balance);
        balance -= itemsToBuy;
        return itemsToBuy;
    }

    public Product(int balance) {
        this.balance = balance;
    }

    public void refund(int items) {
// - Update product balance and report balance update by thread
        balance += items;
    }

    public int getBalance() {
        return balance;
    }
}
