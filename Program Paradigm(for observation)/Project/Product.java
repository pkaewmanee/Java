/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;

public class Product {

    private String productName;
    private int productPrice, productWeight, totalUnits, totalSales;

    public Product(String n, int p, int w) {
        this.productName = n;
        this.productPrice = p;
        this.productWeight = w;
        this.totalSales = 0;
        this.totalUnits = 0;
    }

    public int returnPrice() {
        return productPrice;
    }

    public int returnWeight() {
        return productWeight;
    }

    public String returnName() {
        return productName;
    }

    public int calculateTotalSales(int units) {
        int totalSales = units * productPrice;
        return totalSales;
    }

    public int calculateTotalUnits(int units) {
        totalUnits += units;
        return totalUnits;
    }

    public int returnUnits() {
        return totalUnits;
    }

}

class Customer {

    private String name;
    private int cashBacks;

    public Customer(String n) {
        this.name = n;
        this.cashBacks = 0;
    }

    public String returnName() {
        return name;
    }

    public int cashBackRedemption(int totalBills) {
        int MaxCashBacks = Math.max(0, Math.min(cashBacks, 100));
        int redemption = Math.min(MaxCashBacks, totalBills);
        cashBacks -= redemption;
        return redemption;
    }

    public void addCashBacks(int totalPrices) {
        double earnCashBack = Math.floor(totalPrices * 0.01);
        int cashBacksInt = (int) cashBacks;  //get rid of demimal
        cashBacks += earnCashBack;
    }

    public void print() {
        System.out.println(name);
    }

    public int returnCashback() {
        return cashBacks;
    }

}
