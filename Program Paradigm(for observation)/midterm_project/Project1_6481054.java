package midterm_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Define a Products class that implements the Comparable interface
class Products implements Comparable<Products> {
    // Define instance variables for the Products class
    String name;
    int price, weight;

    // Define a constructor for the Products class
    public Products(String name, int price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    // Define getter methods for the instance variables
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getWeight() { return weight; }

    // Implement the compareTo method of the Comparable interface to sort Products by price
    @Override
    public int compareTo(Products other) {
        return Integer.compare(other.price, this.price);
    }
}

class Customer {

    // Define instance variables for the Customer class
    String name;
    double cashback1, cashback2;
    boolean matched;

    // Define a constructor for the Customer class
    public Customer(String name, double cashback1, double cashback2) {
        this.name = name;
        this.cashback1 = cashback1;
        this.cashback2 = cashback2;
    }

    // Define a method to set matched to true
    public void found() {
        matched = true;
    }

    // Define a method to check if the customer is matched
    public int check() {
        return matched ? 1 : 0;
    }

    // Define methods to set, add, and subtract from cashback1
    public void setCashback(double cashback) {
        cashback1 = cashback;
    }

    public void addCashback(double cashback) {
        cashback1 += cashback;
    }

    public void subtractCashback(double cashback) {
        cashback1 -= cashback;
    }

    // Define a method to return cashback1
    public double getCashback() {
        return cashback1;
    }

    // Define methods to set and add to cashback2
    public void setCashback2(double cashback) {
        cashback2 = cashback;
    }

    public void addCashback2(double cashback) {
        cashback2 += cashback;
    }

    // Define a method to return the customer's name
    public String getName() {
        return name;
    }

    // Define a method that doesn't do anything (?)
    public void customerList(String n) {
    }

    // Commented out this method since it was not used in the code
    //public int compareTo(Customer other) { ... }
}

class Order implements Comparable<Order> {
    
    // Declare variables
    protected int ID, od1, od2, od3, od4, od5, total_sales_in_cash, total_sales_in_units;
    protected String ShipType, CusName;

    // Constructor
    public Order(int id, String name, String Ship, int Order1, int Order2, int Order3, int Order4, int Order5, int TotalCash, int TotalUnits) {
        ID = id;
        CusName = name;
        ShipType = Ship;
        od1 = Order1;
        od2 = Order2;
        od3 = Order3;
        od4 = Order4;
        od5 = Order5;
        total_sales_in_cash = TotalCash;
        total_sales_in_units = TotalUnits;
    }

    // Getters for variables
    public String getName() { return CusName; }
    public int get_total_weight() { return total_sales_in_units; }
    public int get_total_sales() { return total_sales_in_cash; }
    public int get_id() { return ID; }
    public int get_unit1() { return od1; }
    public int get_unit2() { return od2; }
    public int get_unit3() { return od3; }
    public int get_unit4() { return od4; }
    public int get_unit5() { return od5; }
    public String get_shiptype() { return ShipType; }

    // Compare two Order objects based on their total sales in cash
    public int compareTo(Order other) {
        return Integer.compare(this.total_sales_in_cash, other.total_sales_in_cash);
    }
}

class ShippingCalculator {

    // Instance variables
    protected String shiptype;
    protected int TotalFee;
    protected int min_weight;
    protected int max_weight;
    protected int Total_fee;

    // Static variables
    protected static int minWeight1, minWeight2, minWeight3, minWeight4, maxWeight1, maxWeight2, maxWeight3, maxWeight4, feeE1, feeE2, feeE3, feeStd, feeSurPlus, RW,
            minWeightStd, maxWeightStd, SurPlus;

    // Static method to set static variables
    protected static void set_all(int a, int b, int c, int e, int f, int g, int i, int j, int k, int l, int m, int n, int o, int p, int q) {
        minWeight1 = a;
        minWeight2 = b;
        minWeight3 = c;
        maxWeight1 = e;
        maxWeight2 = f;
        maxWeight3 = g;
        feeE1 = i;
        feeE2 = j;
        feeE3 = k;
        feeStd = l;
        feeSurPlus = m;
        RW = n;
        minWeightStd = o;
        maxWeightStd = p;
        SurPlus = q;
    }

    // Method to return minimum weight
    protected int return_min() {
        return minWeight1;
    }

    // Method to return maximum weight
    protected int return_max() {
        return maxWeight1;
    }

    // Constructor without parameters
    public ShippingCalculator() {
    }

    // Constructor with a parameter for shipping type
    public ShippingCalculator(String Type) {
        shiptype = Type;
    }

    // Method to calculate shipping fees
    public void Shipping(int TotalWeight, String shiptype) {
        // TotalFee = 1;

        // Check shipping type
        if (shiptype.equals("E")) {
            // Calculate fees for express shipping

            if (minWeight1 < TotalWeight && TotalWeight < maxWeight1) {
                TotalFee = feeE1;
            } else if (minWeight2 < TotalWeight && TotalWeight < maxWeight2) {
                TotalFee = feeE2;
            } else if (minWeight3 < TotalWeight && TotalWeight < maxWeight3) {
                TotalFee = feeE3;
            } else if (minWeight1 > TotalWeight && TotalWeight > maxWeight3) {
                if (TotalWeight > minWeightStd && TotalWeight < maxWeightStd) {
                    TotalFee = feeStd;
                } else if (TotalWeight > SurPlus) {
                    TotalFee = feeStd + (RW / TotalWeight) * feeSurPlus;
                } else if (RW % TotalWeight > 0) {
                    TotalFee = feeStd + (RW / TotalWeight) * feeSurPlus + feeSurPlus;
                }
            }

        } else {
            // Calculate fees for standard shipping

            if (TotalWeight > minWeightStd && TotalWeight < maxWeightStd) {
                TotalFee = feeStd;
            } else if (TotalWeight > SurPlus) {
                TotalFee = feeStd + (RW / TotalWeight) * feeSurPlus;
            } else if (RW % TotalWeight > 0) {
                TotalFee = feeStd + (RW / TotalWeight) * feeSurPlus + feeSurPlus;
            }
        }
    }

    // Method to get total fee
    public int get_fee() {
        return TotalFee;
    }
}

public class Project1_6481054 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\n====222= Order processing =====");
        String path = "src/main/Java/midterm_project/";
        String[] Files = {"orders.txt, products.txt, shipping.txt"};
        //String[] Files = {"orders_errors.txt, products.txt, shipping.txt"};  //add this to see order errors
        // String Order = path + Files[0];
        // String Products = path + Files[1];
        // String Shipping = path + Files[2];

        ArrayList<Customer> customer = new ArrayList<>();
        ArrayList<Products> product = new ArrayList<>();
        ArrayList<ShippingCalculator> shipping = new ArrayList<>();
        ArrayList<Order> order = new ArrayList<>();
        Scanner scan = null;

        try {
            scan = new Scanner(new File(path + "products.txt"));
            // if(scan == null) System.out.println("Damn");
            // product.txt;
            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] buf = line.split(",");

                String ProName = buf[0].trim();
                int ProPrice = Integer.parseInt(buf[1].trim());
                int ProWeight = Integer.parseInt(buf[2].trim());

                Products prod = new Products(ProName, ProPrice, ProWeight);
                product.add(prod);
            }
        } catch (FileNotFoundException e) {
        }
        //========================
        try {
            //scan = new Scanner(new File(path + "orders.txt"));
            scan = new Scanner(new File(path + "orders_errors.txt")); //add this to see order errors

            while (scan.hasNext()) {

                String line = scan.nextLine();
                String[] buf = line.split(",");

                int Order_id = Integer.parseInt(buf[0].trim());
                String Customer_name = buf[1].trim();
                String Shipping_type = buf[2].trim();
                int[] unit = new int[5];
                boolean correctionMade = false;

                int i, j = 0;

                for (i = 0; i < 5; i++) {
                    try {
                        if (buf.length < 8 && Shipping_type.matches("\\d+")) {
                            Shipping_type = "S";
                            j = 1;
                            unit[0] = Integer.parseInt(buf[2].trim());
                            throw new MissingFormatException(" 1 columns missing");
                        }

                        if (!"E".equalsIgnoreCase(Shipping_type) && !"S".equalsIgnoreCase(Shipping_type) && Shipping_type.matches("\\d+")) {
                            unit[0] = Integer.parseInt(buf[2].trim());
                            throw new InvalidInputException("For input: " + buf[2].trim());
                        } else if (!"E".equalsIgnoreCase(Shipping_type) && !"S".equalsIgnoreCase(Shipping_type)) {
                            Shipping_type = "S";
                            unit[i] = Integer.parseInt(buf[i + 3].trim());
                            throw new InvalidInputException("For input: " + buf[2].trim()); //ADD THIS LINE AT 20 Feb
                        } else {
                            unit[i] = Integer.parseInt(buf[i + 3 - j].trim());
                        }

                        if (unit[i] < 0) {
                            unit[i] = 0;
                            throw new InvalidInputException("For input: " + buf[i + 3].trim());
                        }
                    } catch (NumberFormatException | InvalidInputException | MissingFormatException e) {
                        correctionMade = true;
                        System.out.println(e);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        correctionMade = true;
                        System.out.println(e);
                        unit[i] = 0;
                    }
                }
                
                int total_price = unit[0] * product.get(0).getPrice() + unit[1] * product.get(1).getPrice()
                        + unit[2] * product.get(2).getPrice() + unit[3] * product.get(3).getPrice() + unit[4] * product.get(4).getPrice();
                int total_weight = unit[0] * product.get(0).getWeight() + unit[1] * product.get(1).getWeight()
                        + unit[2] * product.get(2).getWeight() + unit[3] * product.get(3).getWeight() + unit[4] * product.get(4).getWeight();
                
                Order a = new Order(Order_id, Customer_name, Shipping_type, unit[0], unit[1], unit[2], unit[3], unit[4], total_price, total_weight);

                /*int unit_1 = Integer.parseInt(buf[3].trim());
                int unit_2 = Integer.parseInt(buf[4].trim());
                int unit_3 = Integer.parseInt(buf[5].trim());
                int unit_4 = Integer.parseInt(buf[6].trim());
                int unit_5 = Integer.parseInt(buf[7].trim());
                
                int total_price = unit_1 * product.get(0).getPrice() + unit_2 * product.get(1).getPrice()
                        + unit_3 * product.get(2).getPrice() + unit_4 * product.get(3).getPrice() + unit_5 * product.get(4).getPrice();
                int total_weight = unit_1 * product.get(0).getWeight() + unit_2 * product.get(1).getWeight()
                        + unit_3 * product.get(2).getWeight() + unit_4 * product.get(3).getWeight() + unit_5 * product.get(4).getWeight();
                
                Order a = new Order(Order_id, Customer_name, Shipping_type, unit_1, unit_2, unit_3, unit_4, unit_5, total_price, total_weight);*/
                order.add(a);
                System.out.println(order.get(0).get_total_sales());
                //  System.out.println("order.get(h).get_unit1(),");
                Customer b = new Customer(Customer_name, 0, 0);
                customer.add(b);
            }
            //System.out.println(order.size());
        } // System.out.println(order.size());
        catch (FileNotFoundException e) {
        }
        //================
        // System.out.println(order.size());
        String[] shipping_type = new String[5];
        String[] fee_type = new String[5];
        int[] min = new int[5];
        int[] max = new int[5];
        int[] fee = new int[5];
        int i = 0;
        try {
            scan = new Scanner(new File(path + "shipping.txt"));

            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] buf = line.split(",");
                shipping_type[i] = buf[0].trim();
                fee_type[i] = buf[1].trim();
                min[i] = Integer.parseInt(buf[2].trim());
                max[i] = Integer.parseInt(buf[3].trim());
                fee[i] = Integer.parseInt(buf[4].trim());
                i++;
                /*  ShippingCalculator b  = new ShippingCalculator(shipping_type[i]);
            shipping.add(b);*/
            }
            ShippingCalculator.set_all(min[0], min[1], min[2], max[0], max[1], max[2], fee[0], fee[1], fee[2], fee[3], fee[4], max[4], min[3], max[3], min[4]);
            for (int k = 0; k < order.size(); k++) {
                ShippingCalculator b = new ShippingCalculator();
                shipping.add(b);
            }
            for (int k = 0; k < order.size(); k++) {
                shipping.get(k).Shipping(order.get(k).get_total_weight(), order.get(k).get_shiptype());
            }
        } catch (FileNotFoundException e) {
        }
        double f = 0, fp = 0, total_bill = 0;
        for (int k = 0; k < i + 7; k++) {
            for (int j = 0; j < i - 1; j++) {
                //if (C[k].GetName().equals(C[j+1].GetName())&& C[j+1].check()==0){
                if (customer.get(k).getName().equals(customer.get(j + 1).getName()) && customer.get(j + 1).check() == 0) {  // get(2).getPrice()
                    customer.get(j + 1).found();

                    //f=customer.get(k).AddCashback2(fp);
                    //customer.get(j+1).GetCashback();
                    if (customer.get(k).getCashback(fp) >= 100) {
                        f = 100;
                    }//customer.get(k).MinusCashback(f);   }
                    else {
                        f = customer.get(k).getCashback(fp);
                        customer.get(k).subtractCashback(f);
                    }
                    total_bill = order.get(j + 1).get_total_sales() + shipping.get(j + 1).get_fee() - f;

                    f = (int) 0.01 * order.get(k).get_total_sales();
                    //if(f>=100) f=100;
                    customer.get(k).addCashback(f);

                } else if ((customer.get(k)).getCashback() == 0) {//C[k].SetCashback(f);
                    customer.get(j + 1).found();
                    total_bill = order.get(j + 1).get_total_sales() + shipping.get(j + 1).get_fee();
                    fp = order.get(k).get_total_sales() * 0.01;
                    customer.get(k).addCashback2(fp);
                }
                // System.out.println(f);
            }
        }

        System.out.println("\n===== Order processing =====");
        // System.out.println(order.size());
        /*Collections.sort(order);
        for(Order done: order){ System.out.println(done); }*/
        // for(int h=0;h<order.size();h++){
        for (int h = 0; h < order.size(); h++) {
            if (order.get(h).get_shiptype().equals("E")) {
                System.out.printf("Order %2d (%8s) , %6s >> %s(%2d)   %s(%2d)   %s(%2d)   %s(%2d)   %s(%2d) \n", order.get(h).get_id(),
                        "express", order.get(h).getName(),
                        product.get(0).getName(), order.get(h).get_unit1(), product.get(1).getName(), order.get(h).get_unit2(),
                        product.get(2).getName(), order.get(h).get_unit3(), product.get(3).getName(),
                        order.get(h).get_unit4(), product.get(4).getName(), order.get(h).get_unit5());
            } else {
                System.out.printf("Order %2d (%8s) , %6s >> %s(%2d)   %s(%2d)   %s(%2d)   %s(%2d)   %s(%2d) \n", order.get(h).get_id(),
                        "standard", order.get(h).getName(),
                        product.get(0).getName(), order.get(h).get_unit1(), product.get(1).getName(), order.get(h).get_unit2(),
                        product.get(2).getName(), order.get(h).get_unit3(), product.get(3).getName(),
                        order.get(h).get_unit4(), product.get(4).getName(), order.get(h).get_unit5());
            }

            // System.out.printf("%d",order.get(0));
            System.out.printf("%29s>> Available cashback = %4.0f\n", " ", Math.floor(customer.get(h).getCashback()));
            System.out.printf("%29s>> Total prices = %,6d\n", " ", order.get(h).get_total_sales());

        }
        //  System.out.printf("%27s>> Total prices = %,6d" ," ", //prices);
        //  System.out.printf("%27s>> Total weight = %,-10d Shipping fee = %d"," ",//weight, //Shipfee);
        //  System.out.printf("%27s>> Final Bill   = %,-10d Cashback for next order = %4d"," ",//finalbill, //cashback);
        //  }
        // }
        // System.out.println(shipping.get(0).get_fee());

    }
}
