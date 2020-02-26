package dataline.mypackage;
/**
 * This Class creates a Check Object with the following
 * attributes: number, amount, date, and matches.
 * @author cfischera
 * Date Last Modified: 29 July 2017
 */
public class Check
{
	private int number;
	private Amount amount;
	private String date;
	private boolean matches;
	
	/**
	 * Default constructor for a Check Object.
	 */
	public Check()
	{
		this.number  = 0;
		this.amount  = new Amount();
		this.date    = null;
		this.matches = false;
	}
	
	/**
	 * Constructor for a Check Object with a predetermined
	 * number and amount.
	 */
	public Check(int n, double a)
	{
		this.number  = n;
		this.amount  = new Amount(a);
		this.date    = null;
		this.matches = false;
	}
	
	/**
	 * Constructor for a Check Object with a predetermined
	 * number, amount, and date.
	 */
	public Check(int n, double a, String d)
	{
		this.number  = n;
		this.amount  = new Amount(a);
		this.date    = d;
		this.matches = false;
	}
	
	/**
	 * Returns Check Object number attribute.
	 */
	public int getNumber()
	{
		return this.number;
	}
	
	/**
	 * Returns Check Object amount attribute.
	 */
	public double getAmount()
	{
		return this.amount.getValue();
	}
	
	/**
	 * Returns Check Object date attribute.
	 */
	public String getDate()
	{
		return this.date;
	}
	
	/**
	 * Returns Check Object matches attribute.
	 */
	public boolean getMatches()
	{
		return this.matches;
	}
	
	/**
	 * Sets Check Object matches attribute.
	 */
	public void setMatches(boolean m)
	{
		this.matches = m;
	}
	
	/**
	 * Returns if another Check Object has equivalent
	 * number and amount values.
	 */
	public boolean equals(Check c)
	{
		return(this.number==c.getNumber()
				&&this.amount.getValue()==c.getAmount());
	}
	
	/**
	 * Converts to and returns Check Object in the
	 * format of a readable String.
	 */
	public String toString()
	{
		return this.number+"    "+this.amount;
	}
}