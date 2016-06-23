package numerike2;

import javax.swing.JOptionPane;

public class Jacobi
{
	private static double[][] A;
	private static double[] XO;
	public Jacobi(double[][] A, double[] b, double[] XO, double TOL, int N)
	{
		A = this.A;
		XO =this.XO;
		int k = 1;
		
		double[] x = new double[A.length];
		while(k <= N)
		{
			for(int i = 0; i < A.length; i++)
			{
				x[i] = (shuma()[i] + b[i])/A[i][i];
			}
			if(distanca_Linfinit(x,XO) <TOL)
			{
				for(int i = 0; i<x.length; i++)
				{
					System.out.print(x[i]+" ");
				}
			}
			k++;
		}
		System.out.println("nr i iteracioneve perfundoi");
	}
	
	public static double[] shuma()
	{
		double[] shuma = new double[4];
		for(int i = 0; i < A.length; i++)
		{
			for(int j = 0; j < A.length; j++)
			{
				if(i == j)
				{
					j++;
				}
				shuma[i] = shuma[i] + A[i][j];
			}
		}
		return shuma;
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

	public static void main(String[] args)
	{
		double[][] A = {{1,1},
						{1,-1}};
		double[] b = {18,12};
		double[] XO = {0,0};
		new Jacobi(A, b, XO, 0.0001, 30);
	}
}

