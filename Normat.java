package numerike2;

import javax.swing.JOptionPane;

public class Normat
{
	public static double[] vektori()
	{
		int a = new Integer(JOptionPane.showInputDialog("Sa elemente?"));
		double[] vargu = new double[a];
		for (int i = 0; i < a; i++)
		{
			double x = new Double(JOptionPane.showInputDialog("Jepni elementin e " + (i + 1)));
			vargu[i] = x;
		}
		return vargu;
	}

	public static double[][] matrica()
	{
		int a = new Integer(JOptionPane.showInputDialog("Jepni numrin e rreshtave:"));
		int b = new Integer(JOptionPane.showInputDialog("Jepni numrin e shtyllave:"));
		double[][] matrix = new double[a][b];
		for (int i = 0; i < a; i++)
		{
			for (int j = 0; j < b; j++)
			{
				double x = new Double(JOptionPane.showInputDialog("Jepni elementin a" +(i+1)+""+(j+1)));
				matrix[i][j] = x;
			}
		}
		return matrix;
	}
	
	public static double[] mbledh_vektoret(double[] x1, double[] x2)
	{
		double[] rez = new double[x1.length];
		if(x1.length == x2.length)
		{
			for (int i = 0; i < x1.length; i++)
			{
			rez[i] = x1[i]+x2[i];
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Vektoret nuk kane gjatesi te njejte!");
			System.exit(0);
		}
		return rez;
	}
	
	public static double[] zbrit_vektoret(double[] x1, double[] x2)
	{
		double[] rez = new double[x1.length];
		if(x1.length == x2.length)
		{
			for (int i = 0; i < x1.length; i++)
			{
			rez[i] = x1[i]-x2[i];
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Vektoret nuk kane gjatesi te njejte!");
			System.exit(0);
		}
		return rez;
	}
	
	public static double l2norma()
	{
		double[] x = vektori(); 
		double shuma = 0;
		for (int i = 0; i < x.length; i++)
		{
			shuma = shuma + Math.pow(x[i], 2);	
		}
		return Math.sqrt(shuma);
	}
	
	public static double linfinitnorma()
	{
		double[] x = vektori(); 
		double max = 0;
		for (int i = 0; i < x.length; i++)
		{
			if (Math.abs(x[i]) >= max)
			{
				max = Math.abs(x[i]);
			}
		}
		return max;
	}
	
	public static double distanca_l2norma(double[] x1, double[] x2)
	{
		double[] zbritja = zbrit_vektoret(x1, x2); 
		double shuma = 0;
		for (int i = 0; i < zbritja.length; i++)
		{
			shuma = shuma + Math.pow(zbritja[i], 2);	
		}
		return Math.sqrt(shuma);
	}
	
	public static double distanca_Linfinit(double[] x1, double[] x2)
	{
		double[] zbritja = zbrit_vektoret(x1, x2); 
		double max = 0;
		for (int i = 0; i < zbritja.length; i++)
		{
			if (Math.abs(zbritja[i]) >= max)
			{
				max = Math.abs(zbritja[i]);
			}
		}
		return max;
	}
	
	public static double normaInfinit_Matricore(double[][] x1)
	{
		double[] shuma = new double[x1.length];
		double max = 0;
		for(int i = 0; i < x1.length; i++)
		{
			for(int j = 0; j < x1[0].length; j++)
			{
				shuma[i] = shuma[i] + Math.abs(x1[i][j]);
			}
			
			if(shuma[i] >= max)
			{
				max = shuma[i];
			}
		}
		return max;
	}
	
	public static double frobeniusNorma(double[][] x1)
	{
		double shuma = 0;
		for(int i = 0; i < x1.length; i++)
		{
			for(int j = 0; j < x1[0].length; j++)
			{
				shuma = shuma +Math.pow(x1[i][j], 2);
			}
		}
		return Math.sqrt(shuma);
	}
}
