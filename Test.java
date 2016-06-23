package numerike2;

import javax.swing.JOptionPane;

public class Test
{
	public static void main(String[] args)
	{
		String s = JOptionPane.showInputDialog("Shenoni fjaline me 9 shkrona");
		
		
		for(char i = 0; i<s.length(); i++ )
		{
			char shkronja = s.charAt(i);
			System.out.print(kodo(shkronja)+" ");
		}
		
		
		
	}
	
	public static int kodo(char i)
	{
		int shifra;
		if(i == ' ')
		{
			shifra = 7;
		}
		else
		{
			shifra = (i * 7)%26;
		}
		
		return shifra;
	}
	
	public static int pjestimi(int n)
	{
		int rez = (n * 7)/26;
		return rez;
	}
	
	public static char dekriptimi(int nr)
	{
		char shkronja = (char)(pjestimi(nr));
		return shkronja;
	}
}
