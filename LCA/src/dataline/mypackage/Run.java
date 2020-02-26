package dataline.mypackage;

import java.util.Scanner;

public class Run
{
	public static void main(String[] args)
	{
		ExistingQuickBooksChecks eQBC =
				new ExistingQuickBooksChecks();
		eQBC.readChecks();
		QuickBooksIIFChecks qBIC =
				new QuickBooksIIFChecks();
		qBIC.readChecks();
		eQBC.updateChecks(qBIC);
		BankStatementChecks bSC =
				new BankStatementChecks();
		bSC.readChecks();
		ListOfChecks tBR = bSC.compareWith(eQBC);
		tBR.printChecks("ToBeReconciled.txt");
		ListOfChecks discs = bSC.compareWithout(eQBC);
		discs.printChecks("Discrepancies.txt");
		Scanner sc = new Scanner(System.in);
		System.out.println(""
				+ "\nWould you like to save IIF "
				+ "Checks permanently?");
		System.out.println("(You must import next months Checks "
				+ "immediately after. "
				+ "This should only be done once per month)");
		System.out.println("\nPlease enter Y/N\n");
		boolean done = false;
		while(!done)
		{
			String s = sc.nextLine();
			if(s.equals("Y"))
			{
				tBR.resetMatches();
				eQBC.resetMatches();
				eQBC.setMatches(tBR);
				eQBC.saveChecks();
				done = true;
			}
			else if(s.equals("N"))
				done = true;
			else
				System.out.println("Please enter Y or N");
		}
		sc.close();
		System.out.println("\nDone.");
	}
}