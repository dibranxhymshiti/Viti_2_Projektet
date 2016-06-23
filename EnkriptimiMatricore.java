package numerike2;

import javax.swing.JOptionPane;

public class EnkriptimiMatricore
{
	public static int alfabeti[] = new int[26];
	public static int mbetjet[] = new int[26];
	
	public static void main(String[] args)
	{
		String hyrja = JOptionPane.showInputDialog("Shtyp: 1 per enkriptim, 2 per dekriptim");
		int a = Integer.parseInt(hyrja);
		
		int numrat[][] = new int[3][3];
		
		for( int i = 0; i < 26; i++)
		{
			alfabeti[i]=(65+i)%26;
			mbetjet[i]=(65+i)/26;
		}
		
		if(a == 1)
		{
			String fjala = JOptionPane.showInputDialog("Shkruani fjalen me 9 shkronja").toUpperCase();
			
			char shkronjat[][] = new char[3][3];
			
			int k = 0;
			for(int i = 0; i<3; i++)
			{
				for(int j = 0; j<3; j++)
				{
					shkronjat[i][j] = fjala.charAt(k);
					k++;
				}			
				
			}

			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j<3; j++)
				{
					int vlera = shkronjat[i][j]%26;
					numrat[i][j] = vlera;
					System.out.print(numrat[i][j]+" ");
				}
				System.out.println();
			}
			
		}
		else if(a == 2)
		{
			int matrica[][] = new int[3][3];
			
			int k = 1;
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j<3; j++)
				{
					int nr = new Integer(JOptionPane.showInputDialog("Jepni numrin "+k+" Ne rangun [0-26]"));
					matrica[i][j] = nr;
					k++;
				}
			}
			
			char fjalia[][] = new char[3][3];
			for(int i = 0; i<3; i++)
			{
				for(int j = 0; j<3; j++)
				{
					int shifrat = matrica[i][j];
					int mbetja = mbetjet[GjejMbetjen(shifrat)];
					int rezultati = mbetja * 26 + shifrat;
					fjalia[i][j] = (char)rezultati;
					System.out.print(fjalia[i][j]+" ");
				}
				System.out.println();
			}
			
		}
	
	}
	public static int GjejMbetjen(int a)
	{
		int rez =0;
		for(int i = 0; i< 26; i++)
		{
			if(alfabeti[i] == a)
			{
				rez = i;
			}
		}
		
		return mbetjet[rez];	
	}
}
