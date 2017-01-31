import java.util.Vector;

public class Date {

	private String[][] dateFixList = { { "Jan", "January", "31" }, { "Feb", "February", "28" },
			{ "Mar", "March", "31" }, { "Apr", "April", "30" }, { "Jun", "June", "30" }, { "Jul", "July", "31" },
			{ "Aug", "August", "31" }, { "Sept", "September", "30" }, { "Oct", "October", "31" },
			{ "Nov", "November", "30" }, { "Dec", "December", "31" } };

	Vector<String> dateContainer = new Vector<String>();
	private String dateString = "";
	private String[] splitString;
	private String printThisDate = "";
	private int counter = 0;
	private DateRange dateRangeObject = new DateRange();
	private boolean dateRangeFlag = false;

	public Date() {
	};

	public void processStringPrintDates() {
		this.dateString = dateContainer.get(getCounter()-1);

		splitTheString();
		fixMonth();
		reattachStringToPrint();
		createDateRange(checkIfDateRange());
		System.out.println(dateContainer.get(getCounter()-1));
		if(dateRangeFlag){
			System.out.println(dateRangeObject.toString());
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
			if (splitString[0].equals(dateFixList[i][0])) {
				splitString[0] = dateFixList[i][1];
			}
			if (Integer.parseInt(splitString[1]) > Integer.parseInt(dateFixList[i][2])) {
				printThisDate = "Invalid Date";
			}
		}
	}

	public void reattachStringToPrint() {
		if (printThisDate != "Invalid Date") {
			splitString[1] = splitString[1] + ",";
			for (String index : splitString) {
				printThisDate += index + " ";
			}
		}
		dateContainer.add(getCounter()-1, printThisDate);
	}

	public boolean checkIfDateRange() {
		if (getCounter() >= 2) {
			if (dateContainer.get(getCounter()-1) == "Invalid Date"
					|| dateContainer.get(getCounter() - 2) == "Invalid Date") {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	private void createDateRange(boolean checkIfDateRange) {
		if (checkIfDateRange) {
			dateString = dateContainer.get(getCounter()-1);
			String[] thisDate = splitString;

			dateString = dateContainer.get(getCounter() - 2);
			String[] lastDate = splitString;

			if (compareTo(thisDate, lastDate) == 1) {
				String dateRangeString = "DateRange: " + dateContainer.get(getCounter() - 2) + " - "
						+ dateContainer.get(getCounter()-1) + "\n";
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
			if (thisDate[0] == months[i]) {
				thisMonth = i;
			}
			if (lastDate[0] == months[i]) {
				lastMonth = i;
			}
		}

		if (Integer.parseInt(lastDate[3]) == Integer.parseInt(thisDate[3])) {
			if (lastMonth == thisMonth) {
				if (Integer.parseInt(lastDate[2]) == Integer.parseInt(thisDate[2])) {
					return 0;
				}
			} else if (lastMonth < thisMonth) {
				return 1;
			} else {
				return -1;
			}
		} else if (Integer.parseInt(lastDate[3]) < Integer.parseInt(thisDate[3])) {
			return 1;
		} else {
			return -1;
		}
		return 7;
	}

	public String toString() {
		return "Date: " + dateContainer.get(getCounter()-1);
	}

}
