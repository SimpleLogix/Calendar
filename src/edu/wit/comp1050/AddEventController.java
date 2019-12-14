package edu.wit.comp1050;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddEventController {

	@FXML
	public TextField nameLabel,locationLabel;
	@FXML
	public TextArea descriptionLabel;
	@FXML
	public Button saveButton, deleteButton, exitButton;
	@FXML
	public MenuItem red,orange,yellow,green,blue,purple,none;
	@FXML
	public MenuButton colorButton;
	@FXML
	public DatePicker dateLabel;
	@FXML
	public Label savedText;
	
	MonthBuilder date = new MonthBuilder();
	EventBuilder event = new EventBuilder();
	int nextID=0;
	
	//the save button which checks for valid options then creates 
	//a EventBuilder object that will store the event
	@FXML
	public void saveBtn() {
		if (!nameLabel.getText().isEmpty()) {
			
			LocalDate selectedDate = this.dateLabel.getValue();
			date.setMonth(selectedDate.getMonthValue());
			date.setYear(selectedDate.getYear());
			date.setDay(selectedDate.getDayOfMonth());
			
			event.setName(nameLabel.getText());
			event.setDate(date);
			event.setID(getLastID()+1);
			if(colorButton.getText().equals("Color")){
					event.setColor("None");
			} else {
					event.setColor(colorButton.getText());
			}
			if (!descriptionLabel.getText().isBlank()) {
				event.setDescritption(descriptionLabel.getText());
			} else
				event.setDescritption(" ");
			if (!locationLabel.getText().isBlank()) {
				event.setLocation(locationLabel.getText());
			} else
				event.setLocation(" ");
			
			String eventCSV = new String(event.eventCSV());
			
			File eventFile = new File("src/edu/wit/comp1050/resources/EventList");
			
			try {
				PrintWriter out = new PrintWriter(new FileWriter(eventFile, true));
				out.append(eventCSV);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			savedText.setVisible(true);
		}
	}
	@FXML
	public void displayAllEvents() {
			//reading from eventList file
		File eventFile = new File("src/edu/wit/comp1050/resources/EventList");
		ArrayList<EventBuilder> allEvents = new ArrayList<>();
			//creating EventBuilder objects from CSV values
		try {
			Scanner fin = new Scanner(eventFile);
			while (fin.hasNextLine()) {
				allEvents.add(convertCSVtoEvent(fin.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	@FXML
	public void exitBtn(ActionEvent e) {
		 //close the window
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}
	@FXML
	public void deleteBtn() {
		//resets fields and deletes from memory
		nameLabel.clear();
		descriptionLabel.clear();
		locationLabel.clear();
		setDatePickerToday();
	}
	@FXML
	public void setRed() {
		colorButton.setText("Red");
	}
	@FXML
	public void setOrange() {
		colorButton.setText("Orange");
	}
	@FXML
	public void setYellow() {
		colorButton.setText("Yellow");
	}
	@FXML
	public void setGreen() {
		colorButton.setText("Green");
	}
	@FXML
	public void setBlue() {
		colorButton.setText("Blue");
	}
	@FXML
	public void setPurple() {
		colorButton.setText("Purple");
	}
	@FXML
	public void setNone() {
		colorButton.setText("None");
	}
	
	public void setDatePickerToday() {
		dateLabel.setValue(LocalDate.now());
	}
	
		//method to convert
	public EventBuilder convertCSVtoEvent(String str) {
		
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
	public int getLastID() {
		
		File eventFile = new File("src/edu/wit/comp1050/resources/EventList");
		String lastIDstr = new String(getLastLine(eventFile).split(",")[5]);
		if (lastIDstr==null || lastIDstr=="") {
			lastIDstr="0";
		}
		int lastID = Integer.valueOf(lastIDstr);

		return lastID;
	}
	//method used to obtain the last line from a file which will determine the ID # of next event
	public String getLastLine( File file) {
		    RandomAccessFile fileHandler = null;
		    try {
		        fileHandler = new RandomAccessFile( file, "r" );
		        long fileLength = fileHandler.length() - 1;
		        StringBuilder sb = new StringBuilder();

		        for(long filePointer = fileLength; filePointer != -1; filePointer--){
		            fileHandler.seek( filePointer );
		            int readByte = fileHandler.readByte();

		            if( readByte == 0xA ) {
		                if( filePointer == fileLength ) {
		                    continue;
		                }
		                break;

		            } else if( readByte == 0xD ) {
		                if( filePointer == fileLength - 1 ) {
		                    continue;
		                }
		                break;
		            }

		            sb.append( ( char ) readByte );
		        }

		        String lastLine = sb.reverse().toString();
		        return lastLine;
		    } catch( java.io.FileNotFoundException e ) {
		        e.printStackTrace();
		        return null;
		    } catch( java.io.IOException e ) {
		        e.printStackTrace();
		        return null;
		    } finally {
		        if (fileHandler != null )
		            try {
		                fileHandler.close();
		            } catch (IOException e) {
		              e.printStackTrace();
		            }
		    }
		}
	}





