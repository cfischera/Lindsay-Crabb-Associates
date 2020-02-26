package dataline.mypackage;

import java.math.BigDecimal;
/**
 * This Class creates an Amount Object that stores
 * an initial double or BigDecimal value in a BigDecimal Object.
 * @author cfischera
 * Date Last Modified: 29 July 2017
 */
public class Amount
{
	private BigDecimal value;
	
	/**
	 * Default Constructor for an Amount Object.
	 */
	public Amount()
	{
		this.value = new BigDecimal(0);
	}
	
	/**
	 * Constructor for an Amount Object that passes a double
	 * value into a BigDecimal for storage and manipulation.
	 */
	public Amount(double v)
	{
		this.value = new BigDecimal(v);
	}
	
	/**
	 * Constructor for an Amount Object that passes a
	 * BigDecimal value for storage and manipulation.
	 */
	public Amount(BigDecimal v)
	{
		this.value = v;
	}
	
	/**
	 * Constructor for an Amount Object that passes
	 * an Amount Object for its BigDecimal value
	 * for storage and manipulation.
	 */
	public Amount(Amount v)
	{
		this.value = new BigDecimal(v.getValue());
	}
	
	/**
	 * Returns the double equivalent value
	 * of the Amount object.
	 */
	public double getValue()
	{
		return this.value.doubleValue();
	}
	
	/**
	 * Converts the Amount Object into a readable String
	 * and in the same format that appears in QuickBooks.
	 */
	public String toString()
	{
		BigDecimal myAmount = this.value;
		myAmount = myAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		String sAmount = String.valueOf(myAmount);
		int indDcmlPnt = -1;
		for(int i=0;i<sAmount.length();i++)
		{
			if(sAmount.charAt(i)=='.')
				indDcmlPnt = i;
		}
		int indFront    = 0;
		int frontLength = sAmount.substring(
				0, indDcmlPnt).length();
		int backLength  = sAmount.substring(
				indDcmlPnt+1).length();
		while(frontLength>3)
		{
			String tempAmount = sAmount.substring(0, indFront);
			int indComma = frontLength%3;
			if(frontLength%3==0)
				indComma = indFront+3;
			sAmount = tempAmount+
					sAmount.substring(indFront, indComma)+
					","+sAmount.substring(indComma);
			indFront = indComma+1;
			indDcmlPnt++;
			frontLength = sAmount.substring(
					indFront, indDcmlPnt).length();
		}
		if(backLength<2)
		{
			sAmount += "0";
		}
		return sAmount;
	}
}