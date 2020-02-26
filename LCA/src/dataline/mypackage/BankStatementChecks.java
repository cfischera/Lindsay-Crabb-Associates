package dataline.mypackage;

import java.util.*;
import java.io.*;
/**
 * This Class creates a ListOfChecks from a PDF to text file.
 * @author cfischera
 * Date Last Modified: 31 July 2017
 */
public class BankStatementChecks extends ListOfChecks
{
	private static final long
		serialVersionUID = -1865711869362080155L;

	/**
	 * Reads Check Objects from Statement text file.
	 */
	public void readChecks()
	{
		System.out.println("Reading bank statement Checks...");
		try
		{
			Scanner sc = new Scanner(new File("Statement.txt"));
			String sNum=sc.next(), sAm=sc.next(), sDate=sc.next();
			int dCounter = 0;
			while(sc.hasNext())
			{
				while(dCounter<8)
				{
					if(sDate.equals("Date"))
						dCounter++;
					sDate = sc.next();
				}
				while(!sAm.equals("Gap"))
				{
					if(sDate.equals("*"))
					{
						sNum  = sAm;
						sAm   = sc.next();
						sDate = sc.next();
					}
					if(sDate.length()>2 && sDate.charAt(2)=='/')
					{
						String myAm = sAm;
						for(int i=0;i<myAm.length();i++)
						{
							if(myAm.charAt(i)==',')
								myAm = myAm.substring(0, i)+
									myAm.substring(i+1);
						}
						this.add(new Check(Integer.valueOf(sNum),
								Double.valueOf(myAm), sDate));
					}
					if(!sAm.equals("Gap"))
					{
						sNum  = sAm;
						sAm   = sDate;
						sDate = sc.next();
					}
				}
				sDate = sc.next();
			}
			sc.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not Found");
		}
	}
}