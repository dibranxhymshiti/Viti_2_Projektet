package BasicBayesian;
import java.util.ArrayList;
import java.util.HashMap;


public class Training 
{
	private int[][] dataset;
	private int number_of_attributes;
	private int[] maxValuePerEachAttribute;
	private int dataset_rows_number;
	private int negative_class_testing, positive_class_testing;
	private HashMap<Integer, ArrayList<Double>> count_values_in_negative_class;
	private HashMap<Integer, ArrayList<Double>> count_values_in_positive_class;
	
	public Training(int [][] train_data, int[][] test_data, int number_of_attributes)
	{
		this.dataset = train_data;
		this.number_of_attributes = number_of_attributes;
		dataset_rows_number = train_data.length;
		int [] maxAttributeValueTestData = maxValuePerEachAttribute(number_of_attributes, test_data);
		int [] maxAttributeValueTrainingData = maxValuePerEachAttribute(number_of_attributes, train_data);
		maxValuePerEachAttribute = maxValuesPerEachAttribute(maxAttributeValueTestData, maxAttributeValueTrainingData);//

		int negative_class = countClassLabels(train_data, -1); //class label which has value -1
		int positive_class = countClassLabels(train_data, 1);  //class label which has value +1
		
		negative_class_testing = countClassLabels(test_data, -1);
		positive_class_testing = countClassLabels(test_data, 1);
		
		double probability_negative_class = (double)negative_class/dataset_rows_number;
		double probability_positive_class = (double)positive_class/dataset_rows_number;
			
		//************************CLASS NEGATIVE********************************* -1
		count_values_in_negative_class  = new HashMap<Integer, ArrayList<Double>>();
		
		for(int attr = 1; attr <=number_of_attributes; attr++) //1-9
		{
			ArrayList<Double> freq = new ArrayList<Double>();
			
			double zeroval = (double)countAttributeValueAndClassLabel(train_data, attr,0, -1)/negative_class; 
			freq.add(zeroval);
					
			for(int i=1; i<=maxValuePerEachAttribute[attr-1]; i++)
			{
				double val = (double)countAttributeValueAndClassLabel(train_data, attr, i, -1)/negative_class;
				freq.add(val);
			}
			count_values_in_negative_class.put(attr, freq);
		}
		//System.out.println("Negative class: " + count_values_in_negative_class);
		
		
		//************************CLASS POSITIVE********************************** +1
		count_values_in_positive_class  = new HashMap<Integer, ArrayList<Double>>();
		
		for(int attr = 1; attr <=number_of_attributes; attr++)
		{
			ArrayList<Double> freq = new ArrayList<Double>();
			double zeroval = (double)countAttributeValueAndClassLabel(train_data, attr,0, +1)/positive_class;
			freq.add(zeroval);
			for(int i=1; i<=maxValuePerEachAttribute[attr-1]; i++)
			{	
				double val = (double)countAttributeValueAndClassLabel(train_data, attr, i, +1)/positive_class;
				freq.add(val);
			}
									
			count_values_in_positive_class.put(attr, freq);
		}
		//System.out.println("Positive class: " +count_values_in_positive_class);
		
		
	}
	
	/*===========================================================================*
	 *				maxValuesPerEachAttribute: krahason nese vlera maksimale e secilit atribut te train_data eshte e njejte me ate te test_data			      
	 *===========================================================================*/
	public static int[] maxValuesPerEachAttribute(int[] test_data, int[] training_data)
	{
		int[] values = new int[test_data.length];
		for(int i=0; i<test_data.length; i++)
		{
			values[i] = Math.max(test_data[i], training_data[i]);
		}
		return values;
	}
	
	/*===========================================================================*
	 *				showMaxValuePerEachAttribue
	 *===========================================================================*/
	public void showMaxValuePerEachAttribut()
	{
		System.out.print("MAX VALUES: ");
		for(int i=0; i<maxValuePerEachAttribute.length; i++)
			System.out.print( maxValuePerEachAttribute[i]+", ");
		System.out.println();
	}
	
	/*===========================================================================*
	 *				maxValuePerEachAttribute
	 *===========================================================================*/
	public int[] maxValuePerEachAttribute(int number_of_attributes, int [][] dataset)
	{
		int[] max_values = new int[number_of_attributes];
		int max = 1;
		for(int i=1; i<dataset[0].length; i++)
			{
				for(int j=0; j<dataset.length; j++)
				{
					if(dataset[j][i] > max)
					{
						max = dataset[j][i];
					}
				}
			max_values[i-1] = max;
			max = 0;
			}
		return max_values;
	}
	
		/*===========================================================================*
	 *				countClassLabels
	 *===========================================================================*/
		public int countClassLabels(int [][] dataset, int nr)
	{
		int count = 0;
		
		for(int row = 0; row < dataset.length; row++)
		{
			if(dataset[row][0] == nr)
				count++;
		}
		return count;
	}
	
	/*===========================================================================*
	 *				countAttributeValueAndClassLabel
	 *===========================================================================*/	
	public int countAttributeValueAndClassLabel(int [][] dataset, int column, int value, int class_label)
	{
		int count = 0;
		for(int row = 0; row < dataset.length; row++)
		 {
			if(dataset[row][0] == class_label && dataset[row][column] == value)
			{
				count++;
			}
		 }
		return count;
	}
	
	/*===========================================================================*
	 *				getPositiveProbabilities
	 *===========================================================================*/	
	public HashMap<Integer, ArrayList<Double>> getPositiveProbabilities()
	{
		return count_values_in_positive_class;
	}
	
	/*===========================================================================*
	 *				getNegativeProbabilities
	 *===========================================================================*/	
	public HashMap<Integer, ArrayList<Double>> getNegativeProbabilities()
	{
		return count_values_in_negative_class;
	}
	
	/*===========================================================================*
	 *				getNegativeClassCount
	 *===========================================================================*/	
	public int getNegativeClassCount()
	{
		return negative_class_testing;
	}
	
	/*===========================================================================*
	 *				getPositiveClassCount
	 *===========================================================================*/
	public int getPositiveClassCount()
	{
		return positive_class_testing;
	}
	
}
