package numerike2;

import javax.swing.*;

public class LinearIterativeSystems
{
	public static double[] vektori(double[][] A, double[] B, double[] XO, double Tol, int N)
	{
		double[] x = new double[XO.length];
		int k = 1;
		boolean found = false;
		while (k <= N && !found)
		{
			for (int i = 0; i != A.length; i++)
			{
				x[i] = (-shuma(A, x, i) - shuma1(A, XO, i) + B[i]) / A[i][i];
				System.out.println(x[i]);
			}

			if (norma_infinit(x, XO) < Tol)
			{
				found = true;
			}

			k = k + 1;
			for (int i = 0; i != x.length; i++)
			{
				XO[i] = x[i];
			}
		}
		if (found == true)
		{
			for (int i = 0; i != x.length; i++)
			{
				JOptionPane.showMessageDialog(null, " " + x[i]);
			}
		} else
		{
			JOptionPane.showMessageDialog(null, "Numri i iterimeve u tejkalua:");
		}
		return x;
	}

	public static double norma_infinit(double[] x, double[] X)
	{
		double[] vektori = new double[x.length];
		for (int i = 0; i != x.length; i++)
		{
			vektori[i] = x[i] - X[i];
		}
		for (int i = 0; i != x.length; i++)
		{
			for (int j = i + 1; j != x.length; j++)
			{
				if (Math.abs(vektori[i]) <= Math.abs(vektori[j]))
				{
					double temp = vektori[i];
					vektori[i] = vektori[j];
					vektori[j] = temp;
				}
			}
		}
		return Math.abs(vektori[0]);
	}

	public static double shuma(double[][] A, double[] X, int i)
	{
		double rez = 0.0;
		for (int j = 0; j != i; j++)
		{
			rez = rez + A[i][j] * X[j];
		}
		return rez;
	}

	public static double shuma1(double[][] A, double[] X, int i)
	{
		double rez = 0.0;
		for (int j = i + 1; j != A.length; j++)
		{
			rez = rez + A[i][j] * X[j];
		}
		return rez;
	}

	public static void main(String[] args)
	{
		int n = new Integer(
				JOptionPane.showInputDialog("Sa eshte numri i rreshtave ?"))
				.intValue();
		double tol = new Double(
				JOptionPane.showInputDialog("Shkruaje tolerancen"))
				.doubleValue();
		int N = new Integer(
				JOptionPane.showInputDialog("Shkruaje numrin e iteracioneve?"))
				.intValue();
		double[][] A = new double[n][n];
		double[] b = new double[n];
		double[] vektori_0 = new double[n];
		for (int i = 0; i != n; i++)
		{
			for (int j = 0; j != n; j++)
			{
				A[i][j] = new Double(
						JOptionPane
								.showInputDialog("Shkruaji elementet e rreshtit te "
										+ i + " -te")).doubleValue();
			}
		}
		for (int i = 0; i != n; i++)
		{
			b[i] = new Double(
					JOptionPane.showInputDialog("Shkruaji elementet e lira"))
					.doubleValue();
		}
		for (int i = 0; i != n; i++)
		{
			vektori_0[i] = new Double(
					JOptionPane
							.showInputDialog("Shkruaji elementet e vektorit 0"))
					.doubleValue();
		}
		vektori(A, b, vektori_0, tol, N);
	}
}