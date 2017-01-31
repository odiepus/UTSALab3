import java.util.Vector;

public class DateRange {
	
	private Vector<String[]> dateHolder;
	private String printThisDateRange = "";
	
	
	public DateRange(){};
	
	public DateRange(String[] inputDate){
		this.dateHolder.add(inputDate);
		
		if (dateHolder.size() > 1){
			makeDateRange();
		}
	}
	
	
	public void add(String[] inputDate){
		dateHolder.add(inputDate);
	}
	
	

}
