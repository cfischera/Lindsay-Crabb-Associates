package dataline.mypackage;

import java.util.*;
import java.io.*;
/**
 * This Class creates a specialized ArrayList of Check Objects
 * for comparison and printing.
 * @author cfischera
 * Date Last Modified: 31 July 2017
 */
public class ListOfChecks extends ArrayList<Check>
{
	private static final long
		serialVersionUID = -477012912866944268L;
	
	/**
	 * Returns a ListOfChecks of the Check Objects included in
	 * this ListOfChecks and in a passed ListOfChecks.
	 */
	public ListOfChecks compareWith(ListOfChecks l)
	{
		System.out.println("Comparing Lists...");
		ListOfChecks myList = new ListOfChecks();
		for(int i=0;i<this.size();i++)
		{
			for(int j=0;j<l.size();j++)
			{
				if(!this.get(i).getMatches()
						&& !l.get(j).getMatches()
						&& this.get(i).equals(l.get(j)))
				{
					this.get(i).setMatches(true);
					l.get(j).setMatches(true);
				}
			}
		}
		for(int i=0;i<this.size();i++)
		{
			if(this.get(i).getMatches())
				myList.add(this.get(i));
		}
		this.resetMatches();
		l.resetMatches();
		myList.sortByNumber();
		return myList;
	}
	
	/**
	 * Returns a ListOfChecks of the Check Objects included in
	 * this ListOfChecks but not in a passed ListOfChecks.
	 */
	public ListOfChecks compareWithout(ListOfChecks l)
	{
		System.out.println("Comparing Lists...");
		ListOfChecks myList = new ListOfChecks();
		for(int i=0;i<this.size();i++)
		{
			for(int j=0;j<l.size();j++)
			{
				if(!this.get(i).getMatches()
						&& !l.get(j).getMatches()
						&& this.get(i).equals(l.get(j)))
				{
					this.get(i).setMatches(true);
					l.get(j).setMatches(true);
				}
			}
		}
		for(int i=0;i<this.size();i++)
		{
			if(!this.get(i).getMatches())
				myList.add(this.get(i));
		}
		this.resetMatches();
		l.resetMatches();
		myList.sortByNumber();
		return myList;
	}
	
	/**
	 * Resets all Check Objects in ListOfChecks to
	 * a matches value of false.
	 */
	public void resetMatches()
	{
		for(int i=0;i<this.size();i++)
		{
			this.get(i).setMatches(false);
		}
	}
	
	/**
	 * Sorts the Check Objects by number
	 * with a bubble sort.
	 */
	public void sortByNumber()
	{
		for(int i=0;i<this.size();i++)
		{
			for(int j=0;j<this.size()-1;j++)
			{
				if(this.get(j).getNumber()>
					this.get(j+1).getNumber())
				{
					Check temp = this.get(j);
					this.set(j, this.get(j+1));
					this.set(j+1, temp);
				}
			}
		}
	}
	
	/**
	 * Sorts the Check Objects by amount
	 * with a bubble sort.
	 */
	public void sortByAmount()
	{
		for(int i=0;i<this.size();i++)
		{
			for(int j=0;j<this.size()-1;j++)
			{
				if(this.get(j).getAmount()>
					this.get(j+1).getAmount())
				{
					Check temp = this.get(j);
					this.set(j, this.get(j+1));
					this.set(j+1, temp);
				}
			}
		}
	}
	
	/**
	 * Prints a ListOfChecks into a text file.
	 */
	public void printChecks(String outFile)
	{
		System.out.println("Printing Checks to "+outFile+"...");
		this.sortByNumber();
		this.resetMatches();
		if(outFile.equals("ToBeReconciled.txt"))
			printRec(outFile);
		else if(outFile.equals("Discrepancies.txt"))
			printDiscs(outFile);
	}
	
	/**
	 * Prints a text file of Check Objects to be reconciled.
	 */
	private void printRec(String outFile)
	{
		try
		{
			FileWriter fw = new FileWriter(new File(outFile));
			fw.write("Check Number           ",0,15);
			fw.write("Amount                 ",0,15);
			fw.write("Running Total\n\n");
			double runningTotal = 0;
			for(int i=0;i<this.size();i++)
			{
				runningTotal+=this.get(i).getAmount();
				fw.write(this.get(i).getNumber()+
						"               ",0,15);
				fw.write(this.get(i).getAmount()+
						"               ",0,15);
				fw.write(new Amount(runningTotal)+"\n");
			}
			fw.close();
		}
		catch(IOException e)
		{
			System.out.println("Error.");
		}
	}
	
	/**
	 * Prints a text file of Check Object discrepancies.
	 */
	private void printDiscs(String outFile)
	{
		try
		{
			FileWriter fw = new FileWriter(new File(outFile));
			fw.write("Check Number               ",0,20);
			fw.write("Amount                     ",0,20);
			fw.write("Date\n\n");
			for(int i=0;i<this.size();i++)
			{
				fw.write(this.get(i).getNumber()+
						"                    ",0,20);
				fw.write(this.get(i).getAmount()+
						"                    ",0,20);
				fw.write(this.get(i).getDate()+"\n");
				
			}
			fw.close();
		}
		catch(IOException e)
		{
			System.out.println("Error.");
		}
	}
}