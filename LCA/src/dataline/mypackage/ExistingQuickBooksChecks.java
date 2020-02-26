package dataline.mypackage;

import java.util.*;
import java.io.*;
/**
 * This Class keeps a record of pre-existing and new Check Objects
 * from QuickBooks.
 * @author cfischera
 * Date Last Modified: 31 July 2017
 */
public class ExistingQuickBooksChecks extends ListOfChecks
{
	private static final long
		serialVersionUID = -5481325927106071656L;

	/**
	 * Reads Check Objects from ExistingChecks text file.
	 */
	public void readChecks()
	{
		System.out.println("Reading existing Checks...");
		try
		{
			Scanner sc = new Scanner(
					new File("ExistingChecks.txt"));
			while(sc.hasNext())
				this.add(new Check(sc.nextInt(), sc.nextDouble()));
			sc.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
	}
	
	/**
	 * Adds Check Objects from a QuickBooksIIFChecks List.
	 */
	public void updateChecks(QuickBooksIIFChecks l)
	{
		System.out.println("Updating existing Checks...");
		this.resetMatches();
		l.resetMatches();
		for(int i=0;i<l.size();i++)
		{
			for(int j=0;j<size();j++)
			{
				if(l.get(i).equals(this.get(j)))
					l.get(i).setMatches(true);
			}
			if(!l.get(i).getMatches())
				this.add(l.get(i));
		}
		this.sortByNumber();
	}
	
	/**
	 * Writes unmatched Check Objects to ExistingChecks text file.
	 */
	public void saveChecks()
	{
		System.out.println("Saving unreconciled Checks...");
		this.sortByNumber();
		try
		{
			FileWriter fw = new FileWriter("ExistingChecks.txt");
			for(int i=0;i<this.size();i++)
			{
				if(!this.get(i).getMatches())
					fw.write(this.get(i).toString()+"\n");
			}
			fw.close();
		}
		catch(IOException e)
		{
			System.out.println("Error.");
		}
	}
	
	/**
	 * Sets Check matches attribute compared to
	 * a passed ListOfChecks.
	 */
	public void setMatches(ListOfChecks l)
	{
		for(int i=0;i<this.size();i++)
		{
			for(int j=0;j<l.size();j++)
			{
				if(this.get(i).equals(l.get(j)))
					this.get(i).setMatches(true);
			}
		}
	}
}