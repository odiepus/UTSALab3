/**
 * @author Hector R Herrera
 * CS3443 Project 3
 * Description: Take dates as input from file and output dates
 * in correct format. Output invalid input as invalid and include
 * date ranges if there is a date range
 */
import java.io.File;

import java.io.FileNotFoundException;
import java.util.*;

public class Lab3 {
/**
 * Main method, start of the program.
 * @param Dates creates of Date class 
 * @param input create Scanner for file input
 */
	public static void main(String[] args) {
		
		Date Dates;
		Scanner input = null;

		try {																					//check if file opens successfully
			input = new Scanner(new File("C:/Users/odiep/workspace/UTSALab3/src/dates.txt"));
		} catch (FileNotFoundException e) {														//let user know file open failed
			System.err.println("File failed to open");
			System.exit(1);
		}

		Dates = new Date();																		//initialize Dates object

		while (input.hasNext()) {																//while text has input continue feed 
			Dates.add(input.nextLine());														// to feed strings to Dates object
		}

		Dates.processStringPrintDates();														//Run program to output results
	}
}