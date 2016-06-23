package BasicBayesian;

import java.net.URL;

public class ushtrmie 
{
	public static void main(String[] args) 
	{
		 URL url = ushtrmie.class.getClassLoader().getResource("breast_cancer, test.txt");
		 System.out.println(url.getPath());
	}
}
