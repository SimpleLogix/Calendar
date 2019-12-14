package edu.wit.comp1050;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;

public class Temp extends CalendarController {

	//method to convert
public static EventBuilder convertCSVtoEvent(String str) {
	
	String name = str.split(",")[0];
	String location = str.split(",")[1];
	String description = str.split(",")[2];
	String dateStr = str.split(",")[3];
	int day=Integer.parseInt(dateStr.split("/")[0]), month=Integer.parseInt(dateStr.split("/")[1]), 
			year=Integer.parseInt(dateStr.split("/")[2]);
	MonthBuilder date = new MonthBuilder(day,month,year);
	String color = str.split(",")[4];
	int ID = Integer.parseInt(str.split(",")[5]);
	
	EventBuilder event = new EventBuilder(name, location, description, date, color, ID);

return event;
}
	
	public static void main(String[] args) {
		
		ArrayList<String> eventCSV = new ArrayList<>();
		ArrayList<EventBuilder> eventList = new ArrayList<>();
		File eventFile = new File("src/edu/wit/comp1050/resources/EventList");
		
		
		ArrayList<EventBuilder> allEvents = new ArrayList<>();
		try {
			Scanner fin = new Scanner(eventFile);
			while (fin.hasNextLine()) {
				allEvents.add(convertCSVtoEvent(fin.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.print(allEvents.size());

	}

		

}
