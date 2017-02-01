/**
 * @author Hector R Herrera
 * 
 */


import java.util.Vector;
/**
 * DateRange will take string input of date ranges that were put together by the Date object
 * @param dateHolder Stores string of date ranges
 * @param counter tracks date range strings in dateHolder
 *
 */
public class DateRange {

	private Vector<String> dateHolder = new Vector<String>();	//holds date range strings
	private int counter = -1 ;									//counts date range strings in dateHolder
	
	/**
	 * Constructor  the default constructor; the variables are initialized automatically
	 */
	public DateRange() {										
	};

	/**
	 * Method that returns the value of counter
	 * @return counter the value of counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Method that sets the counter whenever a new string is added
	 * @param counter the value of counter
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * Method that adds a string to dateHolder
	 * @param dateRangeString the string that contains the date range
	 */
	public void add(String dateRangeString) {
		dateHolder.add(dateRangeString);
		setCounter(getCounter() + 1);
	}

	/**
	 * Method that returns the date range String
	 * @return date_range string of the date range
	 */
	public String toString() {
		return dateHolder.get(getCounter());
	}

}
