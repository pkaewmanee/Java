/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Lab_Ch2;



// Define an interface called MyInterface
interface MyInterface {
    // Declare a method with no implementation
    void myMethod();
}

// Define an abstract class called MyAbstractClass
abstract class MyAbstractClass {
    // Declare a field
    private int myField;

    // Define a constructor that takes an int parameter
    public MyAbstractClass(int myField) {
        // Set the value of the field to the parameter
        this.myField = myField;
    }

    // Define a method with an implementation
    public void myMethod() {
        // Output the value of the field
        System.out.println("My field is: " + myField);
    }

    // Define an abstract method with no implementation
    abstract public void myAbstractMethod();
}

// Define a class called MyClass that implements MyInterface and extends MyAbstractClass
class MyClass extends MyAbstractClass implements MyInterface {
    // Define a constructor that takes an int parameter
    public MyClass(int myField) {
        // Call the constructor of the superclass
        super(myField);
    }

    // Implement the myAbstractMethod method
    public void myAbstractMethod() {
        // Output a message
        System.out.println("Implementing myAbstractMethod");
    }
}

// Define a main method
public class Main {
    public static void main(String[] args) {
        // Create an instance of MyClass with myField set to 42
        MyClass myObject = new MyClass(42);

        // Call the myMethod method
        myObject.myMethod();

        // Call the myAbstractMethod method
        myObject.myAbstractMethod();
    }
}







