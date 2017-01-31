
import java.io.File;

import java.io.FileNotFoundException;
import java.util.*;

public class Lab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Vector<Date> dateContainer;
		
		 Scanner input = null;
		
		 try {
		 input = new Scanner(new File("C:/Users/odiep/workspace/kec296-Lab3/src/dates.txt"));
		 } catch (FileNotFoundException e) {
		 System.err.println("File failed to open");
		 System.exit(1);
		 }
		
		 dateContainer = new Vector<Date>();
		 DateRange dateRangeObject = new DateRange();
		 
		 while (input.hasNext()) {
		 Date holder = new Date(input.nextLine());
		 System.out.println(holder.toString());
		 dateRangeObject.add(holder.getSplitString());
		 System.out.println(holder.toString());
		 System.out.println(dateRangeObject.toString());
		 dateContainer.add(holder);
		 }
		 
		 
	}

}