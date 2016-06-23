package BasicBayesian;

import java.io.*;

public class ParseDataSet
{

	/*
	 * ==========================================================================
	 * =* paseDataset
	 * ============================================================
	 * ===============
	 */
	public int[][] parseDataset(String training_dataset_path, int number_of_attributes ) throws IOException
	{
		int [][] training_data = new int [countRows(training_dataset_path)][number_of_attributes+1];
		String read_transaction = "";	
		
		try {
            FileReader fileReader = new FileReader(training_dataset_path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int ID_ItemSet = 0;
      
            while((read_transaction = bufferedReader.readLine()) != null)
            {    if(validate(read_transaction.charAt(0)+""))
            	{
            	String[] transaction_box = read_transaction.split(" ");
            	  training_data[ID_ItemSet][0] = Integer.parseInt(transaction_box[0]); // gets class label value
            	  
            	  for(int i = 1; i<transaction_box.length; i++)
	              {
            		  String[] index_value = transaction_box[i].split(":");
            		  training_data[ID_ItemSet][Integer.parseInt(index_value[0])] = Integer.parseInt(index_value[1]);
            	  }
	              ID_ItemSet++;
            	}
            	}   
            bufferedReader.close();         
      }
	catch (IOException ex)
	{
		ex.printStackTrace();
	}
		return training_data;
}

	/*
	 * ==========================================================================
	 * =* countRows
	 * ==============================================================
	 * =============
	 */
	public static int countRows(String filename) throws IOException
	{
		LineNumberReader reader = new LineNumberReader(new FileReader(filename));
		int count = 0;
		String lineRead = "";
		while ((lineRead = reader.readLine()) != null)
		{
		}
		count = reader.getLineNumber();
		reader.close();
		return count;
	}
	
	public boolean validate(String s)
	{
		boolean answer = false; 
		if(s.equals("0") || s.equals("-")|| s.equals("+"))
			answer = true;
		return answer;
	}

	public static void main(String[] args) throws IOException
	{
		String path = "C:\\Users\\dibran\\Desktop\\generated_data.txt";
		ParseDataSet p = new ParseDataSet();
		int[][] dataset = p.parseDataset(path, 10);
		for (int i = 0; i < dataset.length; i++)
		{
			for (int j = 0; j < dataset[0].length; j++)
			{
				System.out.print(dataset[i][j] + " ");
			}
			System.out.println();
		}
	}
}
