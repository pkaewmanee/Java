
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Model {
	public double mse = Double.POSITIVE_INFINITY;
	public double S;
	public double L;
	public double D;
	public double M;

}

public class CountryInfected {
	String country;
	private static final int DAY_RANGE = 487; // 22-jan to 22-may
	int[] infected = new int[DAY_RANGE];

	public static int[] slice_infected(CountryInfected[] c, String country, String start, String end)
			throws ParseException {
		Date first_date = new SimpleDateFormat("dd/MM/yyyy").parse("22/01/2020");
		Date start_date = new SimpleDateFormat("dd/MM/yyyy").parse(start); // END OF PROJECTION
		Date end_date = new SimpleDateFormat("dd/MM/yyyy").parse(end); // START OF PROJECTION

		long diffInMillies = Math.abs(first_date.getTime() - start_date.getTime());
		int first_to_start = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;
		diffInMillies = Math.abs(start_date.getTime() - end_date.getTime());
		int start_to_end = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;

		if (start_to_end == 0)
			return null;
		int i = 0;
		while (i < c.length && !c[i].get_country_name().equalsIgnoreCase(country))
			i++;

		if (i == c.length) return null;

		int[] sliced = new int[start_to_end];
		for (int j = 0; j < start_to_end; j++) {
			sliced[j] = c[i].get_infected(first_to_start + j);
		}
		System.out.println("Slice " + sliced.length + " days from " + country + " between " + start + " to " + end);
		return sliced;
	}

	public static String comma_issue(String s, String new_delimiter) {
		StringBuffer sb = new StringBuffer(s);
		boolean in_double_quote = false;
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '"') {
				sb.delete(i, i + 1);
				in_double_quote = !in_double_quote;
			}
			if (!in_double_quote && sb.charAt(i) == ',') {
				sb = sb.replace(i, i + 1, new_delimiter);
			}
		}
		return sb.toString();
	}

	public static String change_country_name(String country_name) {
		if (country_name.equalsIgnoreCase("Taiwan*")) {
			return "Taiwan";
		}
		if (country_name.equalsIgnoreCase("korea, south")) {
			return "South Korea";
		}
		if (country_name.equalsIgnoreCase("Holy See")) {
			return "Vatican City";
		}
		return country_name;
	}

	public static ArrayList<CountryInfected> confirmed_per_day(ArrayList<CountryInfected> c) {
		for (int i = 0; i < c.size(); i++) {
			for (int j = DAY_RANGE - 1; j > 0; j--) {
				int prev = c.get(i).get_infected(j - 1);
				int curr = c.get(i).get_infected(j);
				c.get(i).set_infected(j, curr - prev);
			}
		}
		return c;
	}

	public static ArrayList<CountryInfected> remove_low_infected(ArrayList<CountryInfected> c_inf, int threshold) {
		ArrayList<CountryInfected> c = new ArrayList<>();
		for (int i = 0; i < c_inf.size(); i++) {
			if (c_inf.get(i).get_infected(DAY_RANGE - 1) > threshold)
				c.add(c_inf.get(i));
		}
		return c;
	}

	/*******************************
	 * UTILITIES FUNCTIONS
	 *************************************************************/
	public CountryInfected(String country) {
		this.country = country;
	}

	public void set_infected(int index, int value) {
		infected[index] = value;
	}

	public int get_infected(int index) {
		return infected[index];
	}

	public String get_country_name() {
		return country;
	}

	private static double[] sigmoid(double S, double D, double L, double M, int n) {
		double[] result = new double[n];
		for (int i = 0; i < n; i++) {
			result[i] = S + M / (1 + Math.exp(-L * (i - D)));
		}
		return result;
	}

	public static double median(int n) {
		if (n % 2 == 0) {
			return ((n - 1) / 2 + n / 2) / 2;
		} else {
			return n / 2;
		}
	}
	/******************************************************************************************************************/
}
