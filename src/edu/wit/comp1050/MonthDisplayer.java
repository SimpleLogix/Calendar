package edu.wit.comp1050;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MonthDisplayer extends CalendarController {


	int firstDay, month, year;
	MonthBuilder displayedCalendarMonth = new MonthBuilder(month,year);
	
	//constructor
	public MonthDisplayer(int firstDay, int month, int year) {
		this.firstDay=firstDay;
		this.month=month;
		this.year=year;
	}
	
	public void setMonth() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CalendarFace1.fxml"));
		CalendarController controller = loader.getController();
		
		
		
		controller.wk2sunDay.setText("02");
  
		
		
		
		
		
		
	}
	public static void main(String[] args) {
		
	}
		}
	
	
	
	
	

