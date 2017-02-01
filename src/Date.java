/**
 * @author Hector R Herrera
 *  The Date object will take in string of dates. The dates will 
 *  be parsed to remove abbreviations and replace with correct full
 *  length spelling. The days are checked to ensure that they are
 *  within the range of each individual month. It is assumed that
 *  every year is a leap year thus February need only go up to 28 days.
 *  After days are stored the process if fixing the format beings.
 *  
 *  First the punctuation are removed and replaced with spaces. 
 *  Then the dates a broken down into String arrays. The month,
 *  day and year are checked for correctness. If a date is invalid 
 *  because an abbreviation does not match a correct date or the day 
 *  is greater that what a month should have, the original string 
 *  of the date is replaced with "Invalid Date". This will be outputed
 *  later when the dates are to be outputed.
 *  
 *  Once the date is correctly formated and is not invalid, the 
 *  string date container is checked for a prior input of a date. 
 *  if there is a date that was inputed before the present date string
 *  then they are compared. If the current date String is later than the 
 *  prior inputed string a date range string is created and sent to 
 *  the dateRange object for storage and output. 
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
	private int counter = 0;
	private DateRange dateRangeObject = new DateRange();
	private boolean dateRangeFlag = false;
	private int forLoopI = 0;
	private boolean incorrectSpellingAndInvalidDateFlag = true;

	public Date() {
	};

	public void processStringPrintDates() {
		for (forLoopI = 0; forLoopI < dateContainer.size(); forLoopI++) {
			this.dateString = dateContainer.get(forLoopI);

			splitTheString();
			fixMonth();
			reattachStringToPrint();
			createDateRange(checkIfDateRange());
			System.out.println("Date: " + dateContainer.get(forLoopI));
			if (dateRangeFlag) {
				System.out.println(dateRangeObject.toString());
				dateRangeFlag = false;
			}
			incorrectSpellingAndInvalidDateFlag = true;
		}
	}

	public String getDay() {
		return splitString[1];
	}

	public String getMonth() {
		return splitString[0];
	}

	public String getYear() {
		return splitString[2];
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void add(String inputDate) {
		dateContainer.add(inputDate);
		setCounter(getCounter() + 1);
	}

	public void splitTheString() {
		dateString = dateString.replace(".", "");
		dateString = dateString.replace(",", "");
		splitString = dateString.split("\\s+");
	}
	
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

	public String toString() {
		return "Date: " + dateContainer.get(forLoopI);
	}

}
