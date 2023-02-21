/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;

import java.util.ArrayList;

public class ShippingCalculator implements Comparable<ShippingCalculator> {

    // Private instance variables to store shipping type, fee type, weight, and fee amounts
    private String shipping_type;
    private String fee_type;
    private int emin_weight;
    private int emax_weight;
    private int efee;
    private int smin_weight;
    private int smax_weight;
    private int sfee;
    private int surplus_threshold;
    private int weight;
    private int fee;

    // Override the compareTo method to sort ShippingCalculators by shipping type, fee type, emin_weight, and emax_weight
    @Override
    public int compareTo(ShippingCalculator other) {

        // Compare by shipping type, E first then S
        int shippingTypeComparison = this.getShippingType().compareTo(other.getShippingType());
        if (shippingTypeComparison != 0) {
            if (this.getShippingType().equals("E")) {
                return -1;
            } else if (this.getShippingType().equals("S") && other.getShippingType().equals("E")) {
                return 1;
            } else {
                return 0;
            }
        }

        // Compare by fee type, F first then V
        int feeTypeComparison = this.getFeeType().compareTo(other.getFeeType());
        if (feeTypeComparison != 0) {
            if (this.getFeeType().equals("F")) {
                return -1;
            } else {
                return 1;
            }
        }

        // Compare emin_weight, from the least to the most
        int eminWeightCmp = Integer.compare(this.emin_weight, other.emin_weight);
        if (eminWeightCmp != 0) {
            return eminWeightCmp;
        }

        // Compare emax_weight, from the least to the most
        return Integer.compare(this.emax_weight, other.emax_weight);
    }

    // Public getter methods for private instance variables
    private String getShippingType() {
        return shipping_type;
    }

    private String getFeeType() {
        return fee_type;
    }

    private int getExpressMinWeight() {
        return emin_weight;
    }

    private int getExpressMaxWeight() {
        return emax_weight;
    }

    private int getExpressFee() {
        return efee;
    }

    private int getStandardMinWeight() {
        return smin_weight;
    }

    private int getStandardMaxWeight() {
        return smax_weight;
    }

    private int getStandardFee() {
        return sfee;
    }

    private int getSurplusThreshold() {
        return surplus_threshold;
    }

    private int getWeight() {
        return weight;
    }

    private int getFee() {
        return fee;
    }

    // Constructor to create a new ShippingCalculator object
    public ShippingCalculator(String st, String ft, int miw, int maw, int f) {
        shipping_type = st;
        fee_type = ft;
        if (shipping_type.equalsIgnoreCase("E")) {
            emin_weight = miw;
            emax_weight = maw;
            efee = f;
        } else if (shipping_type.equalsIgnoreCase("S") && fee_type.equalsIgnoreCase("F")) {
            smin_weight = miw;
            smax_weight = maw;
            sfee = f;
        } else {
            surplus_threshold = miw;
            weight = maw;
            fee = f;
        }
    }

    private static int ShippingStandardCalculation(int totalWeight, ShippingCalculator sc3, ShippingCalculator sc4) {
        // Set initial values
        int totalWeightCounter = totalWeight;
        int shippingPrice = 0;

        // Check if total weight falls within standard shipping limits
        if (totalWeightCounter > sc3.getStandardMinWeight() && totalWeightCounter <= sc3.getStandardMaxWeight()) {
            shippingPrice += sc3.getStandardFee();  // If it does, add the standard shipping fee to the price
        } else {
            // Otherwise, add the standard shipping fee to the price and start calculating the additional weight surcharge
            shippingPrice += sc3.getStandardFee();
            totalWeightCounter -= sc4.getSurplusThreshold();
            int deduct = totalWeightCounter / sc4.getWeight();
            shippingPrice += (totalWeightCounter / sc4.getWeight()) * sc4.getFee();

            totalWeightCounter -= (deduct * sc4.getWeight());
            if (totalWeightCounter < sc4.getWeight() && totalWeightCounter != 0) {
                shippingPrice += sc4.getFee();
            }

        }

        return shippingPrice;

    }

    public static int Calculate(int shippingId, String shippingType, int totalWeight, String shipping, int shippingPrice, ArrayList<ShippingCalculator> shippingcal) {
        // Initialize array of ShippingCalculator objects
        ShippingCalculator[] sc = new ShippingCalculator[5];

        int totalWeightCounter = totalWeight;
        shippingPrice = 0;
        int i = 0;

        // Loop through shippingcal and add each ShippingCalculator object to sc array
        for (ShippingCalculator shippingCal : shippingcal) {
            sc[i] = shippingCal;
            ++i;
        }

        // Determine shipping type and calculate price accordingly
        if (shipping.equalsIgnoreCase("S")) {
            shippingId = 0;
            shippingType = "(standard)";
            shippingPrice = ShippingStandardCalculation(totalWeight, sc[3], sc[4]);  // If standard shipping, call ShippingStandardCalculation method
        } else {
            shippingId = 1;
            shippingType = "(express)";
            if (totalWeightCounter > sc[0].getExpressMinWeight() && totalWeightCounter <= sc[0].getExpressMaxWeight()) {
                shippingPrice = sc[0].getExpressFee();
            } else if (totalWeightCounter > sc[1].getExpressMinWeight() && totalWeightCounter <= sc[1].getExpressMaxWeight()) {
                shippingPrice = sc[1].getExpressFee();
            } else if (totalWeightCounter > sc[2].getExpressMinWeight() && totalWeightCounter <= sc[2].getExpressMaxWeight()) {
                shippingPrice = sc[2].getExpressFee();
            } else {
                shippingId = 0;
                shippingType = "(standard)";
                shippingPrice = ShippingStandardCalculation(totalWeight, sc[3], sc[4]);   // If none of the express shipping limits apply, call ShippingStandardCalculation method
            }
        }

        return shippingPrice;
    }

}
