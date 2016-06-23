package numerike2;

import javax.swing.*;
public class MetodaEJakobit
{	public static double[] vektori(double[][] A, double[] B, double[] X, double Tol, int N)
	{	//double[][] A = matrica;
		//double[] B	= b;
		//double[] X = x0;
		double[] x = new double[X.length];
		int k = 1;
		boolean found = false;
		while(k <= N && !found)
				{	for(int i = 0; i != A.length; i++)
						{		
							
								x[i] = (-shuma(A,X,i) + B[i])/A[i][i];
								System.out.println(x[i]);
							

						}
					
					if(norma_infinit(x,X) <  Tol)
						{	
							found = true;
						}
								
					k = k + 1;
					for(int i = 0;i != x.length;i++)
						{
							X[i] = x[i];
						}
				}
		if(found == true)
			{	for(int i = 0;i != x.length;i++)
					{	System.out.print(" " + x[i]);
					}
			}
		else { System.out.println("Numri i iterimeve u tejkalua:");
				}
		return x;
	}
	
		public static double norma_infinit(double[] x,double[] X)
	{	double[] vektori = new double[x.length];
		for(int i = 0;i != x.length;i++)
			{	vektori[i] = x[i] - X[i];
			}
		for(int i = 0;i != x.length; i++)
				{	for(int j = i+1;j != x.length;j++)
						{	if(Math.abs(vektori[i]) <= Math.abs(vektori[j]))
								{	double temp = vektori[i];
									vektori[i] = vektori[j];
									vektori[j] = temp;
								}
						}
					}
		return Math.abs(vektori[0]);
	}
	
	public static double shuma(double[][] A,double[] X,int i)
	{	double rez = 0.0;
		for(int j = 0;j != A.length && j != i-1;j++)
			{	rez = rez + A[i][j]*X[j];
			}
		return rez;
	}
	
	
	public static void main(String[] args)
	{	int n = new Integer(JOptionPane.showInputDialog("Sa eshte numri i rreshtave ?")).intValue();
		double tol = new Double(JOptionPane.showInputDialog("Shkruaje tolerancen")).doubleValue();
		int N = new Integer(JOptionPane.showInputDialog("Shkruaje numrin e iteracioneve?")).intValue();
		double[][] A = new double[n][n];
		double[] b = new double[n];
		double[] vektori_0 = new double[n];
		for(int i = 0;i != n;i++)
			{	for(int j = 0;j != n;j++)
						{	A[i][j] = new Double(JOptionPane.showInputDialog("Shkruaji elementet e rreshtit te " + i + " -te")).doubleValue();
						}
			}
		for(int i = 0;i != n;i++)
			{	b[i] = new Double(JOptionPane.showInputDialog("Shkruaji elementet e lira")).doubleValue();
			}
		for(int i = 0;i != n;i++)
			{	vektori_0[i] = new Double(JOptionPane.showInputDialog("Shkruaji elementet e vektorit 0")).doubleValue();
			}
		vektori(A,b,vektori_0,tol,N);
	}
}