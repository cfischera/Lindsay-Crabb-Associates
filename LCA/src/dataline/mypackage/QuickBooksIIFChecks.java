package dataline.mypackage;

import java.util.*;
import java.io.*;
/**
 * This Class creates a ListOfChecks from an IIF to text File.
 * @author cfischera
 * Date Last Modified: 31 July 2017
 */
public class QuickBooksIIFChecks extends ListOfChecks
{
	private static final long
		serialVersionUID = 5816517591540464130L;

	/**
	 * Reads Check Objects from QB text file.
	 */
	public void readChecks()
	{
		System.out.println("Reading IIF Checks...");
		try
		{
			Scanner sc = new Scanner(new File("QB.txt"));
			boolean first = true;
			while(sc.hasNext())
			{
				String sAm = sc.next();
				if(sAm.charAt(0)=='-')
				{
					String sNum = sc.next();
					if(sNum.equals("N")||sNum.equals("0.00"))
						first = false;
					if(first)
					{
						sAm = sAm.substring(1);
						this.add(new Check(Integer.valueOf(sNum),
								Double.valueOf(sAm)));
						first = false;
					}
					else
						first = true;
				}
				
			}
			sc.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
	}
}