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
