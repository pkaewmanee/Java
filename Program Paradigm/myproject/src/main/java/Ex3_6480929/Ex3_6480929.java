//6480929 Phakkhapon Kaewmanee
package Ex3_6480929;

import java.io.*;
import java.util.*;
import java.text.NumberFormat;

/**
 *
 * @author fill
 */
class FilmBase {

    protected String name;
    protected int age, currentYear = 2023;

    public FilmBase(String n, int a) {
        name = n;
        age = a;
    }

    public void print() {

    }
}

class LiveAction extends FilmBase {

    protected String director;
    protected int gross;

    public LiveAction(String n, int a, String d, int g) {
        super(n, a);
        director = d;
        gross = g;
    }

    @Override
    public void print() {
        //System.out.print(name);
        String year = "("+ (currentYear - age) + ")";
        //System.out.print("(" + year + ")");
        String dir = "Director = " + director.trim();
        int thb = (gross * 35);
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        String th_gross = "opening gross = " + formatter.format(thb) + " million THB";
        //System.out.print(th_gross + " million THB");
        
        System.out.printf("%-20s %-30s %-35s %-20s\n", name, year, dir, th_gross);
    }

};

class Animation extends FilmBase {

    protected int min;

    public Animation(String n, int a, int m) {
        super(n, a);
        min = m;
    }

    @Override
    public void print() {
        //System.out.print(name);
        String year = "("+ (currentYear - age) + ")";
        //System.out.print("(" + year + ")");
        double hrs = min / 60;
        int minute = min - 60;
        String hm = ((int) hrs + " hrs, " + minute + " mins");
        
        System.out.printf("%-20s %6s %18s\n", name, year, hm );
    }

};

public class Ex3_6480929 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        String path = "src/main/java/Ex3_6480929/allFilms.txt/";
        Scanner sc = new Scanner(new File(path));
        FilmBase fb[] = new FilmBase[10];
        LiveAction la[] = new LiveAction[5];
        Animation a[] = new Animation[5];
        String name[] = new String[10];
        int age[] = new int[10];

        int i = 0;
        int j = 0;
        int k = 0;

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] buf = line.split(",");
            name[i] = buf[1];
            int age_sc = Integer.parseInt(buf[2].trim());
            age[i] = age_sc;
            fb[i] = new FilmBase(name[i], age[i]);

            if (buf.length == 5 && buf[0].equals("L")) {
                int gross = Integer.parseInt(buf[4].trim());
                la[j] = new LiveAction(name[i], age[i], buf[3], gross);
                fb[i] = la[j];
                j++;
            } else {
                int min = Integer.parseInt(buf[3].trim());
                a[k] = new Animation(name[i], age[i], min);
                fb[i] = a[k];
                k++;
            }
            i++;
        }

        System.out.println("=== Both types of films (reverse order) ===\n");

        for (i = 9; i > -1; i--) {
            fb[i].print();
            System.out.println("");
        }

        System.out.println("=== Only live action films ===\n");

        for (i = 0; i < 5; i++) {
            la[i].print();
            System.out.println("");
        }

        System.out.println("=== Only animation films ===\n");
        for (i = 0; i < 5; i++) {
            a[i].print();
            System.out.println("");
        }

    }

}
