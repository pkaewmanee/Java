/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class InvalidInputException extends RuntimeException {

    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}

class MissingFormatException extends java.lang.ArrayIndexOutOfBoundsException {

    public MissingFormatException(String errorMessage) {
        super(errorMessage);
    }
}

//Feel free to make duplicate method in this class for file handling and caught Exception
//Every file handling method will be put in this class!!
public class FileHandler {

    private final String path;
    private String fileName;
    private final Scanner keyboardScan;

    public FileHandler(String p, String fn) {
        path = p;
        fileName = fn;
        keyboardScan = new Scanner(System.in);
    }

    public void productFileProcessLine(String line, Product[] P, int i) {
        try {
            String[] buf = line.split(",");

            String productName = buf[0].trim();
            int productPrice = Integer.parseInt(buf[1].trim());
            int productWeight = Integer.parseInt(buf[2].trim());

            P[i] = new Product(productName, productPrice, productWeight);
        } catch (RuntimeException e) {
            System.out.println(e);
            System.out.println(line + "\n");
        }
    }

    public void wrongProductFile_loop(Product[] P) {
        boolean opensuccess = false;
        int i = 0;
        System.out.println("Read products from file " + path + fileName + "\n");
        while (!opensuccess) {
            try (Scanner fileScan = new Scanner(new File(path + fileName))) {
                opensuccess = true;
                while (fileScan.hasNext() && i < 5) {
                    productFileProcessLine(fileScan.nextLine(), P, i);
                    i++;
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
                System.out.println("\nEnter file name for products: ");
                fileName = keyboardScan.next();
                File inputFile = new File(path + fileName);
                if (inputFile.exists()) {
                    //fileName = inputFileName;
                    System.out.println("Read products from file " + path + fileName);
                }
                System.out.print("\n");
            }

        }
    }

    public void orderFileProcessLine(ArrayList<Order> o, String line, ArrayList<Customer> c) {
        boolean correctionMade = false;
        try {
            String[] buf = line.split(",");

            int orderNum = Integer.parseInt(buf[0].trim());
            String name = buf[1].trim();
            String shipping = buf[2].trim();

            int[] order = new int[5];
            int i, j = 0;

            for (i = 0; i < 5; i++) {
                try {
                    if (buf.length < 8 && shipping.matches("\\d+")) {
                        shipping = "S";
                    }

                    if (!"E".equalsIgnoreCase(shipping) && !"S".equalsIgnoreCase(shipping) && shipping.matches("\\d+")) {
                        order[0] = Integer.parseInt(buf[2].trim());
                        throw new InvalidInputException("For input: " + buf[2].trim());
                    } else if (!"E".equalsIgnoreCase(shipping) && !"S".equalsIgnoreCase(shipping)) {
                        shipping = "S";
                        order[i] = Integer.parseInt(buf[i + 3].trim());
                        throw new InvalidInputException("For input: " + buf[2].trim());
                    } else {
                        order[i] = Integer.parseInt(buf[i + 3 - j].trim());
                    }

                    if (order[i] < 0) {
                        order[i] = 0;
                        throw new InvalidInputException("For input: " + buf[i + 3].trim());
                    }

                } catch (NumberFormatException | InvalidInputException | MissingFormatException e) {
                    correctionMade = true;
                    System.out.println(e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    e = new MissingFormatException(" 1 columns missing");
                    correctionMade = true;
                    System.out.println(e);
                    order[i] = 0;
                }
            }
            if (correctionMade) {
                System.out.print("Original [" + line + "] =========> ");
                System.out.printf("Correction [%d, %s, %s, %d, %d, %d, %d, %d]\n\n", orderNum, name, shipping,
                        order[0], order[1], order[2], order[3], order[4]);
            }

            Order addNew = new Order(orderNum, name, shipping, order[0], order[1],
                    order[2], order[3], order[4], c);
            o.add(addNew);

        } catch (RuntimeException e) {
            System.out.println(e);
            System.out.println(line + "\n");
        }
    }

    public void wrongOrderFile_loop(ArrayList<Order> o, ArrayList<Customer> c) {
        boolean opensuccess = false;
        System.out.println("Read orders from file " + path + fileName + "\n");
        while (!opensuccess) {
            try (Scanner fileScan = new Scanner(new File(path + fileName))) {
                opensuccess = true;
                while (fileScan.hasNext()) {
                    orderFileProcessLine(o, fileScan.nextLine(), c);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
                System.out.println("\nEnter file name for orders: ");
                fileName = keyboardScan.next();
                File inputFile = new File(path + fileName);
                if (inputFile.exists()) {
                    System.out.println("Read orders from file " + path + fileName);
                }
                System.out.print("\n");
            }
        }
    }

    public void shippingFileProcessLine(String line, ArrayList<ShippingCalculator> sc) {
        try {
            String[] buf = line.split(",");

            String shipping_type = buf[0].trim();
            String fee_type = buf[1].trim();
            int min_weight = Integer.parseInt(buf[2].trim());
            int max_weight = Integer.parseInt(buf[3].trim());
            int fee = Integer.parseInt(buf[4].trim());

            ShippingCalculator addNew = new ShippingCalculator(shipping_type, fee_type, min_weight, max_weight, fee);

            sc.add(addNew);

        } catch (RuntimeException e) {
            System.out.println(e);
            System.out.println(line + "\n");
        }
    }

    public void wrongShippingFile_loop(ArrayList<ShippingCalculator> sc) {
        boolean opensuccess = false;
        System.out.println("Read shipping from file " + path + fileName + "\n");
        while (!opensuccess) {
            try (Scanner fileScan = new Scanner(new File(path + fileName))) {
                opensuccess = true;
                while (fileScan.hasNext()) {
                    shippingFileProcessLine(fileScan.nextLine(), sc);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
                System.out.println("\nEnter file name for shipping: ");
                fileName = keyboardScan.next();
                File inputFile = new File(path + fileName);
                if (inputFile.exists()) {
                    System.out.println("Read products from file " + path + fileName);
                }
                System.out.print("\n");
            }
        }
    }

}
