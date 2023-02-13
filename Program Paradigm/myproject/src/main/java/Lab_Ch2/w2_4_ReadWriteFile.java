package Lab_Ch2;

import java.io.*;
import java.util.*;

class w2_4_ReadWriteFile  
{
  public static void main(String[] args)  
  {
        // Path relative to src. Use forward slash (/) instead of backward slash (\)
        String path    = "src/main/Java/Lab_Ch2/";
	String infile  = path + "input.txt";
	String outfile = path + "output.txt";
        //String outfile = "output2.txt";             // file is place outside src

	try 
        {
	  Scanner scan  = new Scanner(new File(infile));
	  PrintWriter write = new PrintWriter(new File(outfile));

	  while (scan.hasNext()) 
          {							
	    String name	  = scan.next();
	    double height = scan.nextDouble();
	    int age = scan.nextInt();
            // Use \r\n when writing to file
            System.out.printf("%s  height = %.0f  age = %d \n", name, height*100, age);
            write.printf("%s  height = %.0f  age = %d \r\n", name, height*100, age);
	  }
          
	  scan.close();
	  write.close();
	}
	catch(Exception e) {
          System.err.println("An error occurs. End program.");
          System.err.println(e);	  
	  //System.exit(-1);
	}
  }
}