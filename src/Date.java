/**
 * @author Hector R Herrera
 *  The Date object will take in string of dates. The dates will 
 *  be parsed to remove abbreviations and replace with correct full
 *  length spelling. The days are checked to ensure that they are
 *  within the range of each individual month. It is assumed that
 *  every year is a leap year thus February need only go up to 28 days.
 *  After days are stored the process of fixing the format begins.
 *  
 *  First the punctuation are removed and replaced with spaces. 
 *  Then the dates a broken down into String arrays. The month,
 *  day and year are checked for correctness. If a date is invalid 
 *  because an abbreviation does not match a month1 or the day 
 *  is greater that what a month should have, the original string 
 *  of the date is replaced with "Invalid Date". This will be outputed
 *  later when the dates are to be outputed.
 *  
 *  Once the date is correctly formated and is not invalid, the 
 *  string date container is checked for a prior input of a date. 
 *  if there is a date that was inputed before the present date string
 *  then they are compared. If the current date String is later than the 
 *  prior inputed string, a date range string is created and sent to 
 *  the dateRange object for storage and output. 
 *  
 *  @param dateContainer holds string of dates inputed and correctly formatted string
 *  @param dateString holds string to be formatted
 *  @param dateFixList contains array list of full spelling months and days in that month
 *  @param splitString contains the string to be formated in array type
 *  @param printThisDate holds correctly formatted string
 *  @param dateRangeObject object to store the date range strings
 *  @param dateRangeFlag flags whether or not a date range was found
 *  @param forLoopI counter for loop to process the strings and access individual strings in dateContainer
 *  @param incorrectSpellingAndInvalidDateFlag  flags whether a date string contains an invalid date 
 */

import java.util.*;

public class Date {

	private String[][] dateFixList = { { "Jan", "January", "31" }, { "Feb", "February", "28" },
			{ "Mar", "March", "31" }, { "Apr", "April", "30" }, {"May", "May", "31"}, { "Jun", "June", "30" }, { "Jul", "July", "31" },
			{ "Aug", "August", "31" }, { "Sept", "September", "30" }, { "Oct", "October", "31" },
			{ "Nov", "November", "30" }, { "Dec", "December", "31" } };

	Vector<String> dateContainer = new Vector<String>(); 
	private String dateString = "";
	private String[] splitString;
	private String printThisDate = "";
	private DateRange dateRangeObject = new DateRange();
	private boolean dateRangeFlag = false;
	private int forLoopI = 0;
	private boolean incorrectSpellingAndInvalidDateFlag = true;
	
	/**
	 * Constructor default constructor all variables are already initialized 
	 */
	public Date() {
	};
	
	/**
	 * Processes the inputed strings stored in dateContainer. Calls methods in specific 
	 * order to remove abbreviations and check if days are within bounds. Prints date 
	 * screen. Also checks if the dateRange flag is set so that it can print out the 
	 * date range string from the dateRange object.
	 */
	public void processStringPrintDates() {
		for (forLoopI = 0; forLoopI < dateContainer.size(); forLoopI++) {				
			this.dateString = dateContainer.get(forLoopI);								//string to be manipulated will be stored here

			splitTheString();											
			fixMonth();
			reattachStringToPrint();
			createDateRange(checkIfDateRange());
			System.out.println("Date: " + dateContainer.get(forLoopI));					//print out the date string after formatted correctly
			if (dateRangeFlag) {
				System.out.println(dateRangeObject.toString());							//print out date range if dateRangeFlag is set true
				dateRangeFlag = false;
			}
			incorrectSpellingAndInvalidDateFlag = true;									//reset flag for next string run thru
		}
	}
	/**
	 * Adds inputed string to the dateContainer
	 * @param inputDate added to dateContainer
	 */
	public void add(String inputDate) {
		dateContainer.add(inputDate);
	}

	/**
	 * Removes commas and periods, and splits string into an array of strings
	 */
	public void splitTheString() {
		dateString = dateString.replace(".", "");
		dateString = dateString.replace(",", "");
		splitString = dateString.split("\\s+");
	}
	
	/**
	 * Goes thru string array and checks months abbreviation. Changes month abbrv with full spelling of month.
	 * Check day to make sure it is within bounds for the month. (ex. Jan has 31 days, if day is 34, then invalid date was inputed).
	 * 
	 */
	public void fixMonth() {
		for (int i = 0; i < dateFixList.length; i++) {
			if (splitString[0].matches(dateFixList[i][0])) {
				splitString[0] = dateFixList[i][1];
				incorrectSpellingAndInvalidDateFlag = false;

				if (Integer.parseInt(splitString[1]) > Integer.parseInt(dateFixList[i][2])) {
					printThisDate = "Invalid Date";
					incorrectSpellingAndInvalidDateFlag = true;
				}
			} else if (splitString[0].matches(dateFixList[i][1])) {
				incorrectSpellingAndInvalidDateFlag = false;
				if (Integer.parseInt(splitString[1]) > Integer.parseInt(dateFixList[i][2])) {
					printThisDate = "Invalid Date";
					incorrectSpellingAndInvalidDateFlag = true;
				}
			}
		}
	}
	
	/**
	 * Re-attaches string array and inserts comma into proper place.
	 * Then puts the correctly formatted string back into dateContainer
	 */
	public void reattachStringToPrint() {
		if (!incorrectSpellingAndInvalidDateFlag) {
			printThisDate = "";
			splitString[1] = splitString[1] + ",";
			for (String index : splitString) {
				printThisDate += index + " ";
			}
		}
		dateContainer.set(forLoopI, printThisDate);
	}

	/**
	 * Checks if there is more than one entry in dateContaner. If there is more than one
	 * then the method returns true else false
	 * @return true if there is a date range else false
	 */
	public boolean checkIfDateRange() {
		if (forLoopI >= 1) {
			if (dateContainer.get(forLoopI) == "Invalid Date" || dateContainer.get(forLoopI - 1) == "Invalid Date") {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * Grabs current date in dateContainer index forLoopI and forLoopI -1 and compares for date range.
	 * If forLoopI -1 date is earlier than forLoopI date then it is possible to compare and dates are 
	 * placed in string and sent to date range object for printout
	 * @param checkIfDateRange if true then dates are compared for a date range
	 */
	private void createDateRange(boolean checkIfDateRange) {
		if (checkIfDateRange) {
			dateString = dateContainer.get(forLoopI);
			splitTheString();
			String[] thisDate = splitString;

			dateString = dateContainer.get(forLoopI - 1);
			splitTheString();
			String[] lastDate = splitString;

			int compareFlag = compareTo(thisDate, lastDate);

			if (compareFlag == 1) {
				String dateRangeString = "DateRange: " + dateContainer.get(forLoopI - 1) + " - Date: "
						+ dateContainer.get(forLoopI);
				dateRangeObject.add(dateRangeString);
				dateRangeFlag = true;
			}
		}
	}

	/**
	 * Takes inputs and splits the strings into string array. Each element is compared to 
	 * a string array of months and the index is used to compare the months numerically. First the years 
	 * are compared to. If years are equal then months are checked if months are equal then days 
	 * are checked. if days equal then return 0.
	 * If the years are equal and then the months are checked, if lastDate month is greater than
	 * thisDate month then return -1.
	 * If lastDate year param is earlier than thisDate param then return 1 else return -1.
	 * @param thisDate string array of recent forLoopI from dateContainer
	 * @param lastDate string array of forLoopI -1 from dateContainer
	 * @return 1 if lastDate earlier than thisDate, 0 if dates are equal, -1 if lastDate earlier than thisDate
	 */
	public int compareTo(String[] thisDate, String[] lastDate) {
		String[] months = { "offset", "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };

		int thisMonth = 0;
		int lastMonth = 0;

		for (int i = 0; i < months.length; i++) {
			if (thisDate[0].matches(months[i])) {
				thisMonth = i;
			}
			if (lastDate[0].matches(months[i])) {
				lastMonth = i;
			}
		}

		if (Integer.parseInt(lastDate[2]) == Integer.parseInt(thisDate[2])) {
			if (lastMonth == thisMonth) {
				String lastDay = lastDate[1];
				String thisDay = thisDate[1];

				int lDay = Integer.parseInt(lastDay);
				int tDay = Integer.parseInt(thisDay);
				if (lDay == tDay) {
					return 0;
				}
			} else if (lastMonth < thisMonth) {
				return 1;
			} else {
				return -1;
			}
		} else if (Integer.parseInt(lastDate[2]) < Integer.parseInt(thisDate[2])) {
			return 1;
		} else {
			return -1;
		}
		return 7;
	}

	/**
	 * Print out forLoopI indexed element in dateContainer
	 * @return Date in string format
	 */
	public String toString() {
		return "Date: " + dateContainer.get(forLoopI);
	}

}
