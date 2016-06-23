package BasicBayesian;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		String type = "breast";
		String training_dataset_path = "";
		String test_dataset_location = "";
		String output_location = "";
		int number_of_attributes = 0;
		boolean test_data = false;
		int counter = 0 ;

		switch (type)
		{
		case "breast":
			training_dataset_path = "Datasets/breast_cancer, training.txt";
			test_dataset_location = "Datasets/breast_cancer, test.txt";
			output_location = "Output/breast_cancer_prediction.txt";
			test_data = true;
			number_of_attributes = 9;
			break;
		case "breast_real":
			training_dataset_path = "Datasets/breast_cancer, training.txt";
			test_dataset_location = "C:\\Users\\dibran\\Desktop\\generated_data.txt";
			output_location = "Output/breast_cancer_real_data.txt";
			number_of_attributes = 9;
			test_data = false;
			break;
		case "led":
			training_dataset_path = "Datasets/led, training.txt";
			test_dataset_location = "Datasets/led, test.txt";
			output_location = "Output/led_prediction.txt";
			number_of_attributes = 7;
			break;
		case "poker":
			training_dataset_path = "Datasets/poker, training.txt";
			test_dataset_location = "Datasets/poker, test.txt";
			output_location = "Output/poker_prediction.txt";
			number_of_attributes = 11;
			break;
		default:
			System.out.println("Nuk keni specifikuar: brest, led apo poker!");
			System.exit(0);
		}

		ParseDataSet parseDataSet = new ParseDataSet();
		int[][] training_data = parseDataSet.parseDataset(
				training_dataset_path, number_of_attributes);
		int[][] testing_data = parseDataSet.parseDataset(test_dataset_location,
				number_of_attributes);
		Training trainingStep = new Training(training_data, testing_data,
				number_of_attributes);
		Testing testingStep = new Testing();
		DecimalFormat formater = new DecimalFormat("0.00");

		ArrayList<ArrayList<String>> decision = new ArrayList<ArrayList<String>>();
		
		
		int total_instances = testing_data.length -9;
		int true_predictions = 0;
		int false_predictions = 0;
		int P = trainingStep.getPositiveClassCount();
		int N = trainingStep.getNegativeClassCount();
		double error_rate;
		double sensitivity;
		double specificity;
		double precision;
		double F_1_score;
		double F_B_score05;
		double F_B_score2;
		int TP = 0;
		int FP = 0;
		int TN = 0;
		int FN = 0;

		for (int i = 0; i < testing_data.length; i++)
		{
			decision.add(new ArrayList<String>());

			int[] tuple = new int[number_of_attributes];
			if (testing_data[i][0] > 0)
			{
				decision.get(i).add("+" + testing_data[i][0] + " ");
			} else
			{
				decision.get(i).add("" + testing_data[i][0] + " ");
			}

			for (int j = 1; j < testing_data[0].length; j++)
			{
				tuple[j - 1] = testing_data[i][j];
				decision.get(i).add(j + ":" + tuple[j - 1] + " ");
			}
			
			if (testing_data[i][0] == 0)
			{
				int prediction = testingStep.classifyTuple(
						trainingStep.getPositiveProbabilities(),
						trainingStep.getNegativeProbabilities(), tuple);
				if(prediction == -1)
					counter++;
				decision.get(i).add("-> " + prediction + " ");
			} else if (testing_data[i][0] == testingStep.classifyTuple(
						trainingStep.getPositiveProbabilities(),
						trainingStep.getNegativeProbabilities(), tuple))
				{
					decision.get(i).add("-> TRUE ");
					true_predictions++;

					if (testing_data[i][0] == 1)
					{
						TP++;
					} else
					{
						TN++;
					}
				} else
				{
					decision.get(i).add("-> FALSE ");
					false_predictions++;

					if (testing_data[i][0] == 1)
					{
						FN++;
					} else
					{
						FP++;
					}
				}
		}

			if(test_data == true)
			{
			// System.out.println(decision);
			double accuracy = (((double) true_predictions) / total_instances) * 100;
			error_rate = (100 - accuracy);
			sensitivity = (double) TP / P;
			specificity = (double) TN / N;
			precision = (double) TP / (TP + FP);
			F_1_score = (double) (2 * precision * sensitivity)
					/ (precision + sensitivity);
			F_B_score05 = (double) ((1 + Math.pow(0.5, 2) * precision
					* sensitivity))
					/ (Math.pow(0.5, 2) * precision + sensitivity);
			F_B_score2 = (double) ((1 + Math.pow(2, 2) * precision
					* sensitivity))
					/ (Math.pow(2, 2) * precision + sensitivity);

			System.out.println("Total instances: " + total_instances);
			System.out.println("Number of 1's: " + P);
			System.out.println("Number of -1's: " + N);
			System.out.println("True predicsiont: " + true_predictions);
			System.out.println("False predictions: " + false_predictions);
			System.out.println("TRUE positives: " + TP);
			System.out.println("TRUE negatives: " + TN);
			System.out.println("FALSE positives: " + FP);
			System.out.println("FALSE negatives: " + FN);

			System.out.println("Accuracy: " + formater.format(accuracy) + "%");
			System.out.println("Error rate: " + formater.format(error_rate)
					+ "%");
			System.out.println("Sensitivity: " + formater.format(sensitivity));
			System.out.println("Specificity: " + formater.format(specificity));
			System.out.println("Precision: " + formater.format(precision));
			System.out.println("F_1_score: " + formater.format(F_1_score));
			System.out.println("F_B_score05: " + formater.format(F_B_score05));
			System.out.println("F_B_score2: " + formater.format(F_B_score2));
			}
		
		
		generateFile(decision, total_instances, true_predictions,
				false_predictions, 0, output_location, test_data);
				
		int [] aa = trainingStep.maxValuePerEachAttribute(9, training_data);
		
		for (int i = 0; i < aa.length; i++)
		{
			System.out.print(aa[i]+ ", ");
		}
		
	}

	/*
	 * ==========================================================================
	 * =* generateFile
	 * ============================================================
	 * ===============
	 */
	private static void generateFile(ArrayList<ArrayList<String>> decisions,
			int total_instances, int true_predictions, int false_predictions,
			double accuracy, String output_location, boolean test_data)
	{
		DecimalFormat formater = new DecimalFormat("0.00");

		File file = new File(output_location);
		try
		{
			if (!file.exists())
				file.createNewFile();

			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("Total instances: " + total_instances);
			bw.newLine();
			if (test_data)
			{
				bw.write("True predictions: " + true_predictions);
				bw.newLine();
				bw.write("False predictions: " + false_predictions);
				bw.newLine();
				bw.write("Accuracy: " + formater.format(accuracy) + "%");
				bw.newLine();
				bw.newLine();
				bw.write("***********************************");
				bw.newLine();
			}
			for (int i = 0; i < decisions.size(); i++)
			{
				for (int j = 0; j < decisions.get(i).size(); j++)
				{
					bw.write(decisions.get(i).get(j));
				}
				bw.newLine();
			}

			bw.close();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}

	}

}
