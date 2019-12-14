package edu.wit.comp1050;

import java.io.File;

public class EventBuilder {

	
	String name,location,description,color;
	int ID=0;
	MonthBuilder date;
	
	//File that will store events
	File eventFile = new File("/resources/EventList.txt");
	
	//MonthBuilder displayedCalendarMonth = new MonthBuilder(super.getDisplayedMonth(),super.getDisplayedYear());
	
	//constructor
	public EventBuilder(String name, String location, String description, MonthBuilder date, String color, int ID) {
		this.name=name;
		this.location=location;
		this.description=description;
		this.color=color;
		this.date=date;
		this.ID=ID;
	}
	public EventBuilder() {
		this.name=null;
		this.location=null;
		this.description=null;
		this.color=null;
		this.date=new MonthBuilder();
	}
	
	
	//get + set methods
	public String getName() {
		return this.name;
	}
	public String getLocation() {
		return this.location;
	}
	public String getDescription() {
		return this.description;
	}
	public String getColor() {
		return this.color;
	}
	public MonthBuilder getDate() {
		return this.date;
	}
	public int getID() {
		return this.ID;
	}
	public void setName (String _name) {
		this.name=_name;
	}
	public void setLocation(String _location) {
		this.location=_location;
	}
	public void setDescritption(String _description) {
		this.description=_description;
	}
	public void setColor(String _color) {
		this.color=_color;
	}
	public void setDate(MonthBuilder _date) {
		this.date=_date;
	}
	public void setID(int _ID) {
		this.ID=_ID;
	}
	
	public String eventCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name + ","+this.location+","+this.description+","+this.date.toString()
				+","+this.color+","+this.ID+"\n");
		
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Event Name: "+ this.name + "\n");
		sb.append("Date: " + this.date.toString() + "\n");
		sb.append("Description: " + this.description + "\n");
		sb.append("Location: " + this.location + "\n");
		sb.append("Category: " + this.color + "\n");
		
		return sb.toString();
	}
	
	

}
	
	
	
	
	

