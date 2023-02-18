//6480929 Phakkhapon Kaewmanee
package Ex3_6480929;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

class FilmBase {

    protected String name;
    protected int age, currentYear = 2023;

    public FilmBase(String n, int a) {
        name = n;
        age = a;
    }

    public void print() {
        String year = "(" + (currentYear - age) + ")";
        System.out.printf("%-20s %6s", name, year);
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
        super.print();
        String dir = "Director = " + director.trim();
        int thb = (gross * 35);
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        String th_gross = "opening gross = " + formatter.format(thb) + " million THB";
        System.out.printf("%-35s %-20s\n", dir, th_gross);
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
        super.print();
        double hrs = min / 60;
        int minute = min % 60;
        String hm = ((int) hrs + " hrs, " + minute + " mins");
        System.out.printf("%18s\n", hm);
    }
};

public class Ex3_6480929 {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "src/main/java/Ex3_6480929/allFilms.txt/";
        try (Scanner sc = new Scanner(new File(path))) {
            ArrayList<FilmBase> films = new ArrayList<>();

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] buf = line.split(",");
                String name = buf[1].trim();
                int age = Integer.parseInt(buf[2].trim());

                if (buf.length == 5 && buf[0].equals("L")) {
                    int gross = Integer.parseInt(buf[4].trim());
                    LiveAction la = new LiveAction(name, age, buf[3], gross);
                    films.add(la);
                } else {
                    int min = Integer.parseInt(buf[3].trim());
                    Animation a = new Animation(name, age, min);
                    films.add(a);
                }
            }

            System.out.println("=== Both LiveAction and Animation ===\"");

            for (FilmBase film : films) {
                film.print();
            }

            System.out.println("\n=== LiveAction only ===");

            for (FilmBase film : films) {
                if (film instanceof LiveAction) {
                    film.print();
                }
            }

            System.out.println("\n=== Animation only ===");

            for (FilmBase film : films) {
                if (film instanceof Animation) {
                    film.print();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
