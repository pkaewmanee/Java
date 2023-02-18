/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Lab_Ch2;



import java.io.*;

public class TryCatchFinallyExample {

    public static void main(String[] args) {
        // We declare the FileWriter outside the try block so we can reference it in the finally block.
        FileWriter writer = null;
        
        try {
            // Create a FileWriter object to write to a file named "output.txt".
            writer = new FileWriter("output.txt");
            
            // Write some text to the file.
            writer.write("Hello, world!");
        } catch (IOException e) {
            // If an IOException is thrown, catch it and print an error message.
            System.err.println("An IOException occurred: " + e.getMessage());
        } finally {
            // The finally block is always executed, whether or not an exception was thrown.
            // This is where we should clean up any resources we have used, such as closing a file or a network connection.
            
            try {
                // We need to check if the writer is null before we try to close it, in case an exception was thrown while creating the writer.
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                // If an IOException is thrown while closing the writer, catch it and print an error message.
                System.err.println("An IOException occurred while closing the writer: " + e.getMessage());
            }
        }
    }
}






