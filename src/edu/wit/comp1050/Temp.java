package edu.wit.comp1050;

import javafx.fxml.FXMLLoader;

public class Temp extends CalendarController {

	public static void main(String[] args) {
		int year = 1992,month=12;
		
		MonthBuilder today = new MonthBuilder();
		
		MonthBuilder NOV2K19 = new MonthBuilder(11,2019);
		MonthBuilder FEB2K19 = new MonthBuilder(2,1900);
		MonthBuilder FEB2K20 = new MonthBuilder(2,2000);
		MonthBuilder AUG2K19 = new MonthBuilder(8,2019);
		MonthBuilder JAN2K19 = new MonthBuilder(6,2020);
		
		int firstDay = FEB2K19.getFirstDay();
		int x=1,y=2,z=3;
		int[] i = {x,y,z};
		
		System.out.print(i[0]);
		
		int temp = i[0];
		temp = temp+1;
		
		System.out.print(temp);
		

		
	}

}
