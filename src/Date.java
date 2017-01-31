
	public class Date {

		private String dateString = "";
		
		private String[][] x = {{"Jan", "January", "31"}, 
								{"Feb", "February", "28"}, 
								{"Mar", "March", "31"}, 
								{"Apr", "April", "30"},
								{"Jun", "June", "30"},
								{"Jul", "July", "31"},
								{"Aug", "August", "31"},
								{"Sept", "September", "30"},
								{"Oct", "October", "31"},
								{"Nov", "November", "30"},
								{"Dec", "December", "31"}
								};
		
		private String[] splitString;
		private String printThisDate = "";
		
		public Date() {
		};

		public Date(String input) {
			this.dateString = input;
			
			splitTheString();
			fixMonth();
			reattachStringToPrint();
		}
		
		public String[] getSplitString(){
			if(printThisDate != "Invalid Date"){
				return splitString;
			}
			String[] invalidDate = {"X", "X", "X"} ;
			return invalidDate;
		}

		public String getDay(){
			return splitString[1];
		}
		
		public String getMonth(){
			return splitString[0];
		}
		
		public String getYear(){
			return splitString[2];
		}
		
		public void splitTheString(){
			dateString = dateString.replace(".", "");
			dateString = dateString.replace(",", "");
			splitString = dateString.split("\\s+");
		}
		
		public void fixMonth(){
			for(int i = 0; i < x.length; i++){
				if(splitString[0].equals(x[i][0])){
					splitString[0] = x[i][1];
				}
				if(Integer.parseInt(splitString[1]) > Integer.parseInt(x[i][2])){
					printThisDate = "Invalid Date";
				}
			}
		}
		
		public void reattachStringToPrint(){
			if(printThisDate != "Invalid Date"){
				splitString[1] = splitString[1] + ",";
				for(String index : splitString){
					printThisDate += index + " ";
				}
			}
		}
		
		public String toString(){
			return printThisDate;
		}
		
	}



