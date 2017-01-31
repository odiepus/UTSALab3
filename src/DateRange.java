import java.util.Vector;

public class DateRange {

	private Vector<String> dateHolder;
	private int counter = 0;

	public DateRange() {
	};

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void add(String dateRangeString) {
		dateHolder.add(dateRangeString);
		setCounter(getCounter() + 1);
	}

	public String toString() {
		return dateHolder.get(getCounter());
	}

}
