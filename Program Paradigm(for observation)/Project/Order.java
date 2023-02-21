/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;

import java.util.ArrayList;

public class Order {

    //Order number and name
    private int orderNumber;
    private String orderName;

    //Number of each item
    private int item1, item2, item3, item4, item5;

    //Shipping type
    private String shipping, shippingType;
    //shipping is for reading from txt, shippingType is for String name
    private int shippingId, shippingPrice; //Standard = 0, Express = 1

    //Total price and weight
    private int totalPrice, totalWeight, finalBill;

    private int getShippingPrice() {
        return shippingPrice;
    }

    public Order(int orderNum, String n, String s, int i1, int i2, int i3,
            int i4, int i5, ArrayList<Customer> c) {
        orderNumber = orderNum;
        orderName = n;
        shipping = s;
        item1 = i1;
        item2 = i2;
        item3 = i3;
        item4 = i4;
        item5 = i5;

        //Check if customer already exists and if not add them
        //First Customer add
        if (orderNumber == 1) {
            Customer firstCustomer = new Customer(orderName);
            c.add(firstCustomer);
        }

        //If for loop detects a same name ONCE it just not add the customer
        boolean costomerCheck = true;
        for (int i = 0; i < c.size(); i++) {
            Customer check = c.get(i);
            if (orderName.equals(check.returnName())) {
                costomerCheck = false;
            }
        }
        if (costomerCheck == true) {
            Customer addCustomer = new Customer(orderName);
            c.add(addCustomer);
        }
    }

    public int getItem1() {
        return item1;
    }

    public void orderProcessing(Product p[], ArrayList<Customer> c, ArrayList<ShippingCalculator> shippingcal) {
        totalPrice = 0;
        totalWeight = 0;

        int[] itemQuantities = {item1, item2, item3, item4, item5};

        // Calculate total quantity of each item
        for (int i = 0; i < p.length; i++) {
            p[i].calculateTotalUnits(itemQuantities[i]);
        }

        //Calcualte total price and weight
        for (int i = 0; i < p.length; i++) {
            int item = 0;
            switch (i) {
                case 0:
                    item = item1;
                    break;
                case 1:
                    item = item2;
                    break;
                case 2:
                    item = item3;
                    break;
                case 3:
                    item = item4;
                    break;
                case 4:
                    item = item5;
                    break;
            }
            totalPrice += p[i].returnPrice() * item;
            totalWeight += p[i].returnWeight() * item;
        }

        //Cashback System
        Customer customer = null;
        for (Customer cust : c) {
            if (cust.returnName().equals(orderName)) {
                customer = cust;
                break;
            }
        }
        if (customer == null) {
            customer = new Customer(orderName);
            c.add(customer);
        }

        int currentCashback = customer.returnCashback();
        int redeemedCashback = customer.cashBackRedemption(totalPrice);
        int discountedPrice = totalPrice - redeemedCashback;
        customer.addCashBacks(totalPrice);
        int futureCashback = customer.returnCashback();

        //CHANGE FOR SHIPPING
        int shippingId;
        String shippingType;
        int shippingPrice = 0;
        if (shipping.equalsIgnoreCase("S")) {
            shippingId = 0;
            shippingType = "(standard)";
        } else {
            shippingId = 1;
            shippingType = "(express)";
        }

        int shippingFee = ShippingCalculator.Calculate(shippingId, shippingType, totalWeight, shipping, shippingPrice, shippingcal);
        int finalBill = discountedPrice + shippingFee;

        System.out.printf("\nOrder %2d%11s,%6s >> ", orderNumber, shippingType, orderName);
        System.out.printf("%17s(%2d)%17s(%2d)%17s(%2d)%17s(%2d)%17s(%2d)", p[0].returnName(), item1, p[1].returnName(), item2,
                p[2].returnName(), item3, p[3].returnName(), item4,
                p[4].returnName(), item5);
        System.out.printf("\n%40s%6s", ">> Available cashback = ", String.format("%,d", currentCashback));
        System.out.printf("\n%40s%6s", ">> Total price = ", String.format("%,d", totalPrice));
        System.out.printf("\n%40s%6s%25s%s", ">> Total weight = ", String.format("%,d", totalWeight), "Shipping Fee = ", String.format("%,d", shippingFee));
        System.out.printf("\n%40s%6s%36s%s\n", ">> Final bill = ", String.format("%,d", finalBill), "Cashback for next order = ", String.format("%,d", futureCashback));
    }

    public int returntotalWeight() {
        return totalWeight;
    }
}
