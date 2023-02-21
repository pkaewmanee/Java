
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Proj2190101_3_7 {

	private static final String FILE_NAME = "/Users/fill/Desktop/sketch_oct28b/COMPUTERTHINGY/PRO/src/time_series_covid19_confirmed_global.csv";

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		/*
		 * TRAIN MODEL USING DATA FRM START_DATE TO END_DATE AND PROJECT FROM NEXT
		 * END_DATE
		 */
		
		String start_proj_date = "01/02/2021";
		// for some reason, at end proj date in some computer can be 21/02/2021, but in some can't
		String end_proj_date = "20/02/2021"; //you can change it to 21/02/2021
		
		// for some reason, at start predict date in some computer can start at 22/02/2021 to 22/05/2021, but some of my group member can't. So we are sending the one that can open for all.
		String start_pred_date = "21/02/2021"; //you can change it to 22/02/2021
		String end_pred_date = "21/05/2021"; //you can change it to 22/05/2021
		CountryInfected[] cinf = getInfected();

		/* MODEL CONSTRAINTS */
		double[] lower_bounds = new double[] { 0, 0, 0, 0 };
		double[] upper_bounds = new double[] { Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
				Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY };
		int numFutureDays = 90;

		PrintWriter pw = new PrintWriter("3_7.csv");
		String[] fgn = new String[] { "France", "Germany", "Netherlands" };

		/* WRITE PREDICTIONS */
		for (int i = 0; i < fgn.length; i++) {
			int[] data = CountryInfected.slice_infected(cinf, fgn[i], start_proj_date, end_proj_date);
			double[] nothing = getNothingCurve(data, numFutureDays);
			double[] scurve = getSCurve(data, numFutureDays, lower_bounds, upper_bounds);
			int[] test_data = CountryInfected.slice_infected(cinf, fgn[i], start_pred_date, end_pred_date);
			System.out.println("Nothing Model MSE of 90 days projection: " + mse(nothing, test_data));
			System.out.println("Scurve Model MSE of 90 days projection: " + mse(scurve, cum_sum(test_data)));
			pw.println(Arrays.toString(scurve).substring(1, Arrays.toString(scurve).length() - 1));
			pw.println(Arrays.toString(nothing).substring(1, Arrays.toString(nothing).length() - 1));
			/*pw.println(Arrays.toString(test_data).substring(1, Arrays.toString(test_data).length() - 1));
			pw.println(
					Arrays.toString(cum_sum(test_data)).substring(1, Arrays.toString(cum_sum(test_data)).length() - 1));
			System.out.println();*/

		}

		pw.close();
	}

	public static double mse(double[] pred, int[] target) {
		if (pred.length != target.length)
			throw new IllegalArgumentException(
					"Prediction and Target must be the same length." + pred.length + ":" + target.length);
		int n = pred.length;
		double se = 0;
		for (int i = 0; i < n; i++) {
			se += Math.pow(pred[i] - target[i], 2);
		}

		return se / n;
	}

	public static CountryInfected[] getInfected() throws FileNotFoundException {
		ArrayList<CountryInfected> all_countries = new ArrayList<>();
		File f = new File(FILE_NAME);
		Scanner sc = new Scanner(f);

		/* dump header */
		sc.nextLine();

		int current = -1;
		final int start_date_index = 4;

		while (sc.hasNextLine()) {
			String line = CountryInfected.comma_issue(sc.nextLine(), ";");
			String[] data = line.split(";");
			String country_name = CountryInfected.change_country_name(data[1]);

			/* assumed that all same country names are stored consecutively */
			if (all_countries.size() > 0 && all_countries.get(current).get_country_name().equals(country_name)) {
				for (int i = start_date_index; i < data.length; i++) {
					int existed_infected = all_countries.get(current).get_infected(i - start_date_index);
					int confirm_infected = Integer.parseInt(data[i]);
					all_countries.get(current).set_infected(i - start_date_index, existed_infected + confirm_infected);
				}
			} else {
				current++;
				CountryInfected cinf = new CountryInfected(country_name);
				for (int i = start_date_index; i < data.length; i++) {
					int confirm_infected = Integer.parseInt(data[i]);
					cinf.set_infected(i - start_date_index, confirm_infected);
				}
				all_countries.add(cinf);
			}
		}

		all_countries = CountryInfected.remove_low_infected(all_countries, 100);

		/* CONVERT FROM CUM CONFIRMED TO NEW CONFIRMED */
		all_countries = CountryInfected.confirmed_per_day(all_countries);

		// System.out.println("#countries: "+all_countries.size());

		/* ArrayList to Array conversion */
		CountryInfected[] country_infected = new CountryInfected[all_countries.size()];
		for (int i = 0; i < all_countries.size(); i++) {
			country_infected[i] = all_countries.get(i);
		}

		sc.close();
		return country_infected;
	}

	/****************************************
	 * part 2
	 *****************************************************/
	public static double[] getNothingCurve(int[] pastData, int numFutureDays) {
		int n = pastData.length;
		double[] predictions = new double[4 + numFutureDays];

		/* COPY LAST 4 DAYS */
		int i = 0;
		for (; i < 4; i++)
			predictions[i] = pastData[n - 4 + i];

		/* REMAINING FUTURE DAYS */
		for (; i < predictions.length; i++) {
			double nom1 = predictions[i - 1] / predictions[i - 2];
			double nom2 = predictions[i - 2] / predictions[i - 3];
			double nom3 = predictions[i - 3] / predictions[i - 4];
			predictions[i] = predictions[i - 1] * (nom1 + nom2 + nom3) / 3;
		}
		return Arrays.copyOfRange(predictions, 4, predictions.length);
	}

	public static int[] cum_sum(int[] data) {
		int[] cum_data = new int[data.length];
		cum_data[0] = data[0];
		for (int i = 1; i < data.length; i++) {
			cum_data[i] = data[i] + cum_data[i - 1];
		}
		return cum_data;
	}

	public static double[] getSCurve(int[] pastData, int numFutureDays, double[] paramLowerBounds,
			double[] paramUpperBounds) {
		/* MAKE CUM CONFIRMED */
		pastData = cum_sum(pastData);
		int n = pastData.length;
		;

		/* SPLIT DATA */
		int train_size = (int) (0.7 * n);
		int[] test_data = Arrays.copyOfRange(pastData, train_size, pastData.length);
		pastData = Arrays.copyOfRange(pastData, 0, train_size);

		/* INITIAL PARAMETER */

		double S = pastData[0];
		double D = CountryInfected.median(train_size);
		double M = S + pastData[(int) D];
		double L = 1;

		final double[] learning_rate = new double[] { 1e-2, 1e-4, 1e-6, 1e-8 };
		final double ITER = 10000;

		Model[] best_model = new Model[4];
		for (int i = 0; i < 4; i++)
			best_model[i] = new Model();

		/* TRAIN MODEL WITH DIFFERENT LEARNING RATE */
		for (int lr = 0; lr < 4; lr++) {

			/* GRADIENT DESCENT CURVE FITTING THROUGH ITERATIONS */
			for (int iter = 0; iter < ITER; iter++) {

				double loss = 0;
				double s_gradient = 0;
				double m_gradient = 0;
				double l_gradient = 0;
				double d_gradient = 0;

				for (int x = 0; x < train_size; x++) {

					double prediction = S + M / (1 + Math.exp(-L * (x - D)));
					loss += Math.pow(prediction - pastData[x], 2);

					/* PROPAGATE ERROR BY PARTIAL DERIVATIVES */
					s_gradient += (pastData[x] - prediction);
					d_gradient += (pastData[x] - prediction)
							* ((Math.exp(-L * (x - D))) / Math.pow(1 + Math.exp(-L * (x - D)), 2));
					l_gradient += (pastData[x] - prediction)
							* (((D - x) * Math.exp(-L * (x - D))) / Math.pow(1 + Math.exp(-L * (x - D)), 2));
					m_gradient += (pastData[x] - prediction) / (1 + Math.exp(-L * (x - D)));

				}
				// System.out.printf("iter:%d s_gradient: %.2f d_gradient: %.2f l_gradient: %.2f
				// m_gradient: %.2f \n",iter,s_gradient,d_gradient,l_gradient,m_gradient);
				loss /= train_size;

				/* MSE OF TEST DATA */
				double[] preds = new double[test_data.length];
				for (int i = 0; i < test_data.length; i++) {
					preds[i] = S + M / (1 + Math.exp(-L * (i + train_size + 1 - D)));
				}
				double mse = Proj2190101_3_7.mse(preds, test_data);

				/* STORE LOWEST TEST-MSE MODEL EACH DIFFERENT LEARNING RATE */
				if (mse < best_model[lr].mse) {
					best_model[lr].mse = mse;
					best_model[lr].S = S;
					best_model[lr].D = D;
					best_model[lr].L = L;
					best_model[lr].M = M;
				}

				/* UPDATE PARAMETERS */
				double S_new = S - learning_rate[lr] * (-2.0 / train_size) * s_gradient;
				double D_new = D - learning_rate[lr] * (2.0 * M * L / train_size) * d_gradient;
				double L_new = L - learning_rate[lr] * (2.0 * M / train_size) * l_gradient;
				double M_new = M - learning_rate[lr] * (-2.0 / train_size) * m_gradient;

				if (S_new >= paramLowerBounds[0] && S_new <= paramUpperBounds[0])
					S = S_new;
				if (D_new >= paramLowerBounds[1] && D_new <= paramUpperBounds[1])
					D = D_new;
				if (L_new >= paramLowerBounds[2] && L_new <= paramUpperBounds[2])
					L = L_new;
				if (M_new >= paramLowerBounds[3] && M_new <= paramUpperBounds[3])
					M = M_new;
			}
		}

		/* SELECT THE LOWEST MSE */
		Model opt = new Model();

		for (int i = 0; i < 4; i++) {
			if (best_model[i].mse < opt.mse) {
				opt = best_model[i];
			}
		}

		/* PREDICTION */
		System.out.printf(
				"The fitted S-curve model has S=%.2f, D=%.2f, L=%.2f, M=%.2f, with the first projected day being d=%d.\n",
				S, D, L, M, pastData.length + 1);
		System.out.println("MSE: " + opt.mse);
		double predictions[] = new double[numFutureDays];
		for (int x = 0; x < numFutureDays; x++) {
			predictions[x] = opt.S + opt.M / (1 + Math.pow(Math.E, -opt.L * (n + 1 + x - opt.D)));
		}

		return predictions;
	}

}
