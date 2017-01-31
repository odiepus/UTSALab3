
import java.io.File;

import java.io.FileNotFoundException;
import java.util.*;

public class Lab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date Dates;
		 Scanner input = null;
		
		 try {
		 input = new Scanner(new File("C:/Users/odiep/workspace/kec296-Lab3/src/dates.txt"));
		 } catch (FileNotFoundException e) {
		 System.err.println("File failed to open");
		 System.exit(1);
		 }
		
		 Dates = new Date();
		 DateRange dateRangeObject = new DateRange();
		 
		 while (input.hasNext()) {
		 Dates.add(input.nextLine());
		 System.out.println();
		 dateRangeObject.add();
		 System.out.println();
		 System.out.println(dateRangeObject.toString());
		 }
		 
		 
	}

}