package BasicBayesian;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class Testing 
{
	private DecimalFormat formater;
	
	/*===========================================================================*
	 *				classifyTuple				      
	 *===========================================================================*/
	public int classifyTuple(HashMap<Integer, ArrayList<Double>> positive, HashMap<Integer, ArrayList<Double>> negative, int[] tuple)
	{
		double positive_product = 1;
		double negative_product = 1;

		for(int i = 0; i <tuple.length; i++)
		{
			positive_product = positive_product * (positive.get(i+1).get(tuple[i]));
		}
		for(int i = 0; i <tuple.length; i++)
		{
		    negative_product = negative_product * (negative.get(i+1).get(tuple[i]));
		}
						
		return (positive_product >= negative_product)? 1 : -1;
	}
}
