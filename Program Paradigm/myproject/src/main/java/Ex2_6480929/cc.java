import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class cc {
    private static final double VAT = 0.07;
    private static final String INPUT_FILE_PATH = "src/main/java/Ex2_6480929/prices.txt";
    private static final String OUTPUT_FILE_PATH = "src/main/java/Ex2_6480929/output.txt";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,###.00");

        System.out.print("Enter the lot size: ");
        int lotSize = scan.nextInt();

        try {
            File inputFile = new File(INPUT_FILE_PATH);
            if (!inputFile.exists()) {
                System.out.println("File not found. Please make sure the file is in the correct folder and named correctly.");
                return;
            }

            File outputFile;
            FileWriter fw;
            try (Scanner fileScan = new Scanner(inputFile)) {
                outputFile = new File(OUTPUT_FILE_PATH);
                fw = new FileWriter(outputFile);
                while (fileScan.hasNextLine()) {
                    String line = fileScan.nextLine();
                    String[] parts = line.split(" ");
                    String productName = parts[0];
                    double netPrice = Double.parseDouble(parts[1]);
                    double vatAmount = netPrice * VAT;
                    double priceBeforeVAT = netPrice + vatAmount;
                    double pricePerLot = priceBeforeVAT * lotSize;
                    
                    String output = String.format("%s, %s, %s", productName, df.format(priceBeforeVAT), df.format(pricePerLot));
                    fw.write(output + "\r\n");
                }
            }
            fw.close();
            System.out.println("Output written to " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred while reading or writing the file.");
        } finally {
            scan.close();
        }
    }
}

