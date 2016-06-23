package numerike2;
import java.util.ArrayList;
import java.util.Collections;
public class Random26nr
{
	public static void main(String[] args)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
	    for (int i=0; i<25; i++) 
	    {
	        list.add(new Integer(i));
	    }
	    Collections.shuffle(list);
	    for (int i=0; i<25; i++) 
	    {
	        System.out.println(list.get(i));
	    }
	}

	
}
