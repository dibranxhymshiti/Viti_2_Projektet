package numerike2;

public class SOR
{

public static void algorithm(double[][] A, double[] B, double[] XO,
		double w, double TOL, int N)
{
	double shuma1 = 0;
	double shuma2 = 0;

	double[] x = new double[A.length];

	int k = 1;
	while (k <= N)
	{
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < i; j++)
			{
				shuma1 = shuma1 + A[i][j] * x[j];
			}
			
			for (int j = 1; j <= i; j++)
			{
				shuma2 = shuma2 + A[i][j] * XO[j] + B[i];
			}
			
			
			x[i] = (1 - w) * XO[i] + (w * ( - shuma1 - shuma2)) / A[i][i];
			if (Normat.distanca_Linfinit(x, XO) < TOL)
			{
				for (int j = 1; j < x.length; j++)
				{
					System.out.print(x[j] + ", ");
				}
				System.exit(0);
			}
			else
			{
				k++;
				for (int j = 0; j < A.length; j++)
				{
					XO[j] = x[j];
				}
			}
			
		}
		
	}
}
	public static void main(String[] args)
	{
		double[][] A =
		{
		{ 2, 3, 0 },
		{ 2, 1, -1 },
		{ 1, -1, 3 } };
		double[] B =
		{ 4, 0, -2 };
		double[] XO =
		{ 1, 1, 1 };
		algorithm(A, B, XO, 1.2, 0.0001, 8);
	}
}
