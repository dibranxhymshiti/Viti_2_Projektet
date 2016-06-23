package BasicBayesian;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GenerateData 
{
	public void generateData(int[] max_value_per_attribute, String output_location, int records)
	{
		
		ArrayList<Random> rnd = new ArrayList<Random>();
	
		for(int i=0; i<max_value_per_attribute.length; i++)
		{
			rnd.add(new Random());
		}
	
		File file = new File(output_location);
		try{
				if (!file.exists()) 
					file.createNewFile();
				
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				
				bw.write("[1] Headache");
				bw.newLine();
				bw.write("[2] Size of lump");
				bw.newLine();
				bw.write("[3] Skin changes");
				bw.newLine();
				bw.write("[4] Nausea");
				bw.newLine();
				bw.write("[5] Weight loss");
				bw.newLine();
				bw.write("[6] Bone pain");
				bw.newLine();
				bw.write("[7] Double Vision");
				bw.newLine();
				bw.write("[8] Temperature");
				bw.newLine();
				bw.write("[9] Muscle weakness");
				bw.newLine();
				int i=0; 
				while(i < records)
				{
					bw.write("0 ");
					for(int j=0; j<rnd.size(); j++)
					{
						bw.write(j+1+ ":" + (rnd.get(j).nextInt(max_value_per_attribute[j])+1) + " ");
					}
					bw.newLine();
					i++;
				}
			bw.close();
		}
		
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		GenerateData g = new GenerateData();
		String output_location = "C:\\Users\\dibran\\Desktop\\generated_data.txt";
		int [] breast_cancer = {6, 3, 11, 6, 3, 3, 2, 5, 2};
		
		g.generateData(breast_cancer, output_location, 10000);
		System.out.println("Data generation is finished");
	}
}
