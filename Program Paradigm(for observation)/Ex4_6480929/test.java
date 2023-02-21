//6480929 Phakkhapon Kaewmanee
package Ex4_6480929;

import java.io.*;
import java.util.*;

public class test {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    private static void sort_airport(ArrayList<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int passen1 = Integer.parseInt(list.get(i).split(",")[2].trim());
            int passen2 = Integer.parseInt(list.get(i + 1).split(",")[2].trim());
            int run1 = Integer.parseInt(list.get(i).split(",")[3].trim());
            int run2 = Integer.parseInt(list.get(i + 1).split(",")[3].trim());
            int ter1 = Integer.parseInt(list.get(i).split(",")[4].trim());
            int ter2 = Integer.parseInt(list.get(i + 1).split(",")[4].trim());
            char name1 = list.get(i).split(",")[0].trim().charAt(0);
            char name2 = list.get(i + 1).split(",")[0].trim().charAt(0);
            String key2 = list.get(i);
            String key3 = list.get(i + 1);

            if (passen1 == passen2) {
                if (run1 < run2) {
                    list.set(i, key3);
                    list.set(i + 1, key2);
                } else if (run1 == run2) {
                    if (ter1 < ter2) {
                        list.set(i + 1, key2);
                        list.set(i, key3);
                    } else if (ter1 == ter2) {
                        if (name2 < name1) {
                            list.set(i + 1, key2);
                            list.set(i, key3);
                        }
                    }
                }
            }

        }
    }

    private static void sort_descend(ArrayList<String> list1, ArrayList<Integer> list2) {
        for (int i = 1; i < list2.size(); i++) {
            int key = list2.get(i);
            String key_s = list1.get(i);
            int j = i - 1;
            while (j >= 0 && list2.get(j) < key) {
                list2.set(j + 1, list2.get(j));
                list1.set(j + 1, list1.get(j));
                j--;
            }
            list2.set(j + 1, key);
            list1.set(j + 1, key_s);
        }
    }

    private static void print_airport(ArrayList<String> list) {
        //FileWriter fileWriter = new FileWriter("src/main/java/Ex4_6480929/output.txt/");
        //PrintWriter write = new PrintWriter(fileWriter);
        System.out.printf("%-50s %18s %18s %18s\n", "Airport", "Passenger (M)", "Runways", "Terminals");
        System.out.println("============================================================================================================");

        //write.printf("%-50s %18s %18s %18s\n", "Airport", "Passenger (M)", "Runways", "Terminals");
        //write.println("============================================================================================================");

        for (int i = 0; i < list.size(); i++) {
            String buf[] = list.get(i).split(",");
            String air_name = buf[1].trim() + "  " + buf[0].trim();
            String pas = buf[2].trim();
            String run = buf[3].trim();
            String ter = buf[4].trim();
            //write.printf("%-50s %12s %21s %17s\n", air_name, pas, run, ter);
            System.out.printf("%-50s %12s %21s %17s\n", air_name, pas, run, ter);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ArrayList<String> aList = new ArrayList<>();
        ArrayList<Integer> passen = new ArrayList<>();

        String path = "src/main/java/Ex4_6480929/airports.txt/";
        Scanner sc = new Scanner(new File(path));

        while (sc.hasNext()) {
            String line = sc.nextLine();
            aList.add(line);
            String[] buf = line.split(",");
            passen.add(Integer.valueOf(buf[2].trim()));
        }
        sc.close();
        sort_descend(aList, passen);
        sort_airport(aList);
        print_airport(aList);
    }
}
