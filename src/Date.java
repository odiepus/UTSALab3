import java.util.Vector;

public class Date {

	private String[][] dateFixList = { { "Jan", "January", "31" }, { "Feb", "February", "28" }, { "Mar", "March", "31" },
			{ "Apr", "April", "30" }, { "Jun", "June", "30" }, { "Jul", "July", "31" }, { "Aug", "August", "31" },
			{ "Sept", "September", "30" }, { "Oct", "October", "31" }, { "Nov", "November", "30" },
			{ "Dec", "December", "31" } };
	
	Vector<String> dateContainer;
	private String dateString = "";
	private String[] splitString;
	private String printThisDate = "";
	private int counter = 0;

	public Date() {
	};

	public void processString() {
		this.dateString = dateContainer.get(getCounter());

		splitTheString();
		fixMonth();
		reattachStringToPrint();
	}

	public String[] getSplitString() {
		if (printThisDate != "Invalid Date") {
			return splitString;
		}
		String[] invalidDate = { "X", "X", "X" };
		return invalidDate;
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
		dateContainer.add(getCounter(), printThisDate);
	}

	public String toString() {
		return printThisDate;
	}
	
	public void add(String inputDate){
		dateContainer.add(inputDate);
		setCounter(getCounter() + 1);
	}

	public int compareTo(String[] x) {
		return 0;

	}

	

}
