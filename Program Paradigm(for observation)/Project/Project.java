/*
Possathorn Sujipisut 6480274
Phakkhapon Kaewmanee 6480929
Supakorn Unjindamanee 6480279
Jawit Poopradit      6480087
 */
package Project;

import java.io.*;
import java.util.*;

public class Project {

    public static void main(String args[]) {

        Product P[] = new Product[5];
        String path = "src/main/java/Project_6480929/";
        String productInFile = "products.txt";
        //String orderInFile = "orders_errors.txt";
         String orderInFile = "orders.txt";
        String shippingInFile = "shipping.txt";

        FileHandler FH = new FileHandler(path, productInFile);
        FH.wrongProductFile_loop(P);

        ArrayList<ShippingCalculator> shipping = new ArrayList<>();
        FileHandler SHF = new FileHandler(path, shippingInFile);
        SHF.wrongShippingFile_loop(shipping);

        //GET RID ONCE HAVE SHIPPING
        //Handling Orders Errors
        ArrayList<Order> orders = new ArrayList<>();
        FileHandler OFH = new FileHandler(path, orderInFile);

        //Adding each new customer
        ArrayList<Customer> c = new ArrayList<>();
        OFH.wrongOrderFile_loop(orders, c);

        //Order processing and printing
        System.out.printf("\n==== Order Processing ====");
        for (Order o : orders) {
            o.orderProcessing(P, c, shipping);
        }
        /*System.out.println("===== Product Summary =====");
        for(int t = 0 ; t<5 ; t++){
            System.out.printf("Total item %d: ", t+1); System.out.println(P[t].returnUnits());
        }*/
        Arrays.sort(P, (p1, p2) -> p2.calculateTotalSales(p2.returnUnits()) - p1.calculateTotalSales(p1.returnUnits()));
        System.out.println("===== Product Summary =====");
        for (int t = 0; t < 5; t++) {
            Product currentProduct = P[t];
            System.out.printf("%17s  %15s %,7d, %2d units\n", currentProduct.returnName(), "Total sales:", currentProduct.calculateTotalSales(currentProduct.returnUnits()), currentProduct.returnUnits());
        }

    }
}