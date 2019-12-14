package edu.wit.comp1050;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class CalendarController {

	@FXML
	public Text monthSB;
	@FXML
	public Text yearSB;
	// Date #s corresponding to each box in the calendar
	@FXML
	public Text wk1sunDay, wk1monDay, wk1tueDay, wk1wedDay, wk1thuDay, wk1friDay, wk1satDay, wk2sunDay, wk2monDay,
			wk2tueDay, wk2wedDay, wk2thuDay, wk2friDay, wk2satDay, wk3sunDay, wk3monDay, wk3tueDay, wk3wedDay,
			wk3thuDay, wk3friDay, wk3satDay, wk4sunDay, wk4monDay, wk4tueDay, wk4wedDay, wk4thuDay, wk4friDay,
			wk4satDay, wk5sunDay, wk5monDay, wk5tueDay, wk5wedDay, wk5thuDay, wk5friDay, wk5satDay, wk6sunDay,
			wk6monDay;
	// the last and next month days (boxes) displayed in the current month
	@FXML
	public VBox lastDaySunBox, lastDayMonBox, lastDayTueBox, lastDayWedBox, lastDayThuBox, lastDayFriBox;
	@FXML
	public VBox nextDaySunBox, nextDayMonBox, nextDayTueBox, nextDayWedBox, nextDayThuBox, nextDayFriBox, nextDaySatBox;
	@FXML
	public Circle wk1sunCircle, wk1monCircle, wk1tueCircle, wk1wedCircle, wk1thuCircle, wk1friCircle, wk1satCircle, wk2sunCircle, wk2monCircle,
	wk2tueCircle, wk2wedCircle, wk2thuCircle, wk2friCircle, wk2satCircle, wk3sunCircle, wk3monCircle, wk3tueCircle, wk3wedCircle,
	wk3thuCircle, wk3friCircle, wk3satCircle, wk4sunCircle, wk4monCircle, wk4tueCircle, wk4wedCircle, wk4thuCircle, wk4friCircle,
	wk4satCircle, wk5sunCircle, wk5monCircle, wk5tueCircle, wk5wedCircle, wk5thuCircle, wk5friCircle, wk5satCircle, wk6sunCircle, wk6monCircle;
	@FXML
	public AnchorPane sundaySplitAnchor, mondaySplitAnchor;
	@FXML
	public DatePicker datePicker;
	

	MonthBuilder today = new MonthBuilder();
	
	String todayStr = String.valueOf(today.getDay());

	final String[] MONTHS = { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	public int getMonth(String s) {

		for (int i = 0; i < 12; i++) {
			if (MONTHS[i].equals(s))
				return i;
		}
		return 0;
	}

	public int getNextMonth(String currentMonth) {

		int nextMonth = getMonth(currentMonth) + 1;
		if (nextMonth > 11) {
			nextMonth = nextMonth - 12;
		}

		return nextMonth;
	}

	public int getPreviousMonth(String currentMonth) {

		int previousMonth = getMonth(currentMonth) - 1;
		if (previousMonth < 0) {
			previousMonth = previousMonth + 12;
		}
		return previousMonth;
	}

	public int getPreviousMonth(int month) {
		return month - 1;
	}

	public int getDisplayedYear() {
		MonthBuilder displayedDate = new MonthBuilder(getMonth(monthSB.getText()) + 1,
				Integer.valueOf(yearSB.getText()));
		return displayedDate.getYear();
	}

	public int getDisplayedMonth() {
		MonthBuilder displayedDate = new MonthBuilder(getMonth(monthSB.getText()) + 1,
				Integer.valueOf(yearSB.getText()));
		return displayedDate.getMonth();

	}

	public int getNextYear() {
		return getDisplayedYear() + 1;
	}

	public int getLastYear() {
		return getDisplayedYear() - 1;
	}

	// ===========================================//
	// METHODS TO HELP BUILD THE CALENDAR DISPLAY //
	// ===========================================//

	// method to display calendar face with correct dates...
	// it first calculates which day of the week the first falls on
	// and using that and how many days of the month there are will construct
	// the right calendar for the month
	// has extra methods to set the shades of the boxes and such...
	public void displayCalendarFace() {

		// creating local variables for keeping track of current, previous, and next
		// month/year
		MonthBuilder displayedCalendarFace = new MonthBuilder(getDisplayedMonth(), getDisplayedYear());
		int firstDay = displayedCalendarFace.getFirstDay();
		int lastYear;
		if (displayedCalendarFace.getMonth() == 1) {
			lastYear = displayedCalendarFace.getYear() - 1;
		} else {
			lastYear = displayedCalendarFace.getYear();
		}
		MonthBuilder lastCalendarFace = new MonthBuilder(displayedCalendarFace.getMonth() - 1, lastYear);
		int lastDayLastMonth = lastCalendarFace.getNumberOfDays();
		
		//clearing the last month
		clearLines();
		
		
		switch (firstDay) {
		case 0: // first day on a Sunday:
			// setting the BG colors
			makeLightBox(lastDaySunBox);
			makeLightBox(lastDayMonBox);
			makeLightBox(lastDayTueBox);
			makeLightBox(lastDayWedBox);
			makeLightBox(lastDayThuBox);
			makeLightBox(lastDayFriBox);
			this.wk1sunDay.setText("01");
			this.wk1monDay.setText("02");
			this.wk1tueDay.setText("03");
			this.wk1wedDay.setText("04");
			this.wk1thuDay.setText("05");
			this.wk1friDay.setText("06");
			this.wk1satDay.setText("07");
			this.wk2sunDay.setText("08");
			this.wk2monDay.setText("09");
			this.wk2tueDay.setText("10");
			this.wk2wedDay.setText("11");
			this.wk2thuDay.setText("12");
			this.wk2friDay.setText("13");
			this.wk2satDay.setText("14");
			this.wk3sunDay.setText("15");
			this.wk3monDay.setText("16");
			this.wk3tueDay.setText("17");
			this.wk3wedDay.setText("18");
			this.wk3thuDay.setText("19");
			this.wk3friDay.setText("20");
			this.wk3satDay.setText("21");
			this.wk4sunDay.setText("22");
			this.wk4monDay.setText("23");
			this.wk4tueDay.setText("24");
			this.wk4wedDay.setText("25");
			this.wk4thuDay.setText("26");
			this.wk4friDay.setText("27");
			this.wk4satDay.setText("28");
			// if Feburary on a leap year
			if ((displayedCalendarFace.getMonth() == 2 && displayedCalendarFace.isLeapYear())) {
				this.wk5monDay.setText("29");
				this.wk5tueDay.setText("01");
				this.wk5wedDay.setText("02");
				this.wk5friDay.setText("03");
				this.wk5satDay.setText("04");
				// if Feb not on a leap year
			} else if (getDisplayedMonth() == 2 && !displayedCalendarFace.isLeapYear()) {
				this.wk5sunDay.setText("05");
				this.wk5monDay.setText("01");
				this.wk5tueDay.setText("02");
				this.wk5wedDay.setText("03");
				this.wk5friDay.setText("04");
				this.wk5satDay.setText("05");
			} else {
				this.wk5sunDay.setText("29");
				this.wk5monDay.setText("30");
				// if Month has 31 days
				if (displayedCalendarFace.getNumberOfDays() == 31) {
					this.wk5tueDay.setText("31");
					this.wk5wedDay.setText("01");
					this.wk5thuDay.setText("02");
					this.wk5friDay.setText("03");
					this.wk5satDay.setText("04");
				} else {
					// if Month has 30 days(next month)
					this.wk5tueDay.setText("01");
					this.wk5wedDay.setText("02");
					this.wk5thuDay.setText("03");
					this.wk5friDay.setText("04");
					this.wk5satDay.setText("05");
				}
			}
			setBoxColor(wk5sunDay, nextDaySunBox);
			setBoxColor(wk5monDay, nextDayMonBox);
			setBoxColor(wk5tueDay, nextDayTueBox);
			setBoxColor(wk5wedDay, nextDayWedBox);
			setBoxColor(wk5thuDay, nextDayThuBox);
			setBoxColor(wk5friDay, nextDayFriBox);
			setBoxColor(wk5satDay, nextDaySatBox);
			break;
		case 1: // first day on a Monday
			// setting the BG colors
			makeDarkBox(lastDaySunBox);
			makeLightBox(lastDayMonBox);
			makeLightBox(lastDayTueBox);
			makeLightBox(lastDayWedBox);
			makeLightBox(lastDayThuBox);
			makeLightBox(lastDayFriBox);
			this.wk1sunDay.setText(String.valueOf(lastDayLastMonth));
			this.wk1monDay.setText("01");
			this.wk1tueDay.setText("02");
			this.wk1wedDay.setText("03");
			this.wk1thuDay.setText("04");
			this.wk1friDay.setText("05");
			this.wk1satDay.setText("06");
			this.wk2sunDay.setText("07");
			this.wk2monDay.setText("08");
			this.wk2tueDay.setText("09");
			this.wk2wedDay.setText("10");
			this.wk2thuDay.setText("11");
			this.wk2friDay.setText("12");
			this.wk2satDay.setText("13");
			this.wk3sunDay.setText("14");
			this.wk3monDay.setText("15");
			this.wk3tueDay.setText("16");
			this.wk3wedDay.setText("17");
			this.wk3thuDay.setText("18");
			this.wk3friDay.setText("19");
			this.wk3satDay.setText("20");
			this.wk4sunDay.setText("21");
			this.wk4monDay.setText("22");
			this.wk4tueDay.setText("23");
			this.wk4wedDay.setText("24");
			this.wk4thuDay.setText("25");
			this.wk4friDay.setText("26");
			this.wk4satDay.setText("27");
			this.wk5sunDay.setText("28");
			// if Feburary and on a leap year
			if ((displayedCalendarFace.getMonth() == 2 && displayedCalendarFace.isLeapYear())) {
				this.wk5monDay.setText("29");
				this.wk5tueDay.setText("01");
				this.wk5thuDay.setText("02");
				this.wk5friDay.setText("03");
				this.wk5satDay.setText("04");
				// if Feburary and not on a leap year
			} else if (getDisplayedMonth() == 2 && !displayedCalendarFace.isLeapYear()) {
				this.wk5monDay.setText("01");
				this.wk5tueDay.setText("02");
				this.wk5wedDay.setText("03");
				this.wk5thuDay.setText("04");
				this.wk5friDay.setText("05");
				this.wk5satDay.setText("06");
			} else {
				this.wk5monDay.setText("29");
				this.wk5tueDay.setText("30");
				// if Month has 31 days
				if (displayedCalendarFace.getNumberOfDays() == 31) {
					this.wk5wedDay.setText("31");
					this.wk5thuDay.setText("01");
					this.wk5friDay.setText("02");
					this.wk5satDay.setText("03");
				} else {
					// if Month has 30 days (next month)
					this.wk5wedDay.setText("01");
					this.wk5thuDay.setText("02");
					this.wk5friDay.setText("03");
					this.wk5satDay.setText("04");
				}
			}
			setBoxColor(wk5sunDay, nextDaySunBox);
			setBoxColor(wk5monDay, nextDayMonBox);
			setBoxColor(wk5tueDay, nextDayTueBox);
			setBoxColor(wk5wedDay, nextDayWedBox);
			setBoxColor(wk5thuDay, nextDayThuBox);
			setBoxColor(wk5friDay, nextDayFriBox);
			setBoxColor(wk5satDay, nextDaySatBox);
			break;
		case 2: // first day on a Tuesday
			// setting the BG colors
			makeDarkBox(lastDaySunBox);
			makeDarkBox(lastDayMonBox);
			makeLightBox(lastDayTueBox);
			makeLightBox(lastDayWedBox);
			makeLightBox(lastDayThuBox);
			makeLightBox(lastDayFriBox);
			this.wk1sunDay.setText(String.valueOf(lastDayLastMonth - 1));
			this.wk1monDay.setText(String.valueOf(lastDayLastMonth));
			this.wk1tueDay.setText("01");
			this.wk1wedDay.setText("02");
			this.wk1thuDay.setText("03");
			this.wk1friDay.setText("04");
			this.wk1satDay.setText("05");
			this.wk2sunDay.setText("06");
			this.wk2monDay.setText("07");
			this.wk2tueDay.setText("08");
			this.wk2wedDay.setText("09");
			this.wk2thuDay.setText("10");
			this.wk2friDay.setText("11");
			this.wk2satDay.setText("12");
			this.wk3sunDay.setText("13");
			this.wk3monDay.setText("14");
			this.wk3tueDay.setText("15");
			this.wk3wedDay.setText("16");
			this.wk3thuDay.setText("17");
			this.wk3friDay.setText("18");
			this.wk3satDay.setText("19");
			this.wk4sunDay.setText("20");
			this.wk4monDay.setText("21");
			this.wk4tueDay.setText("22");
			this.wk4wedDay.setText("23");
			this.wk4thuDay.setText("24");
			this.wk4friDay.setText("25");
			this.wk4satDay.setText("26");
			this.wk5sunDay.setText("27");
			this.wk5monDay.setText("28");
			// if Feburary on a leap year
			if ((displayedCalendarFace.getMonth() == 2 && displayedCalendarFace.isLeapYear())) {
				this.wk5tueDay.setText("29");
				this.wk5wedDay.setText("01");
				this.wk5friDay.setText("02");
				this.wk5satDay.setText("03");
				// if Feb not on a leap year
			} else if (getDisplayedMonth() == 2 && !displayedCalendarFace.isLeapYear()) {
				this.wk5tueDay.setText("01");
				this.wk5wedDay.setText("02");
				this.wk5thuDay.setText("03");
				this.wk5friDay.setText("04");
				this.wk5satDay.setText("05");
			} else {
				this.wk5tueDay.setText("29");
				this.wk5wedDay.setText("30");
				// if Month has 31 days
				if (displayedCalendarFace.getNumberOfDays() == 31) {
					this.wk5thuDay.setText("31");
					this.wk5friDay.setText("01");
					this.wk5satDay.setText("02");
				} else {
					// if Month has 30 days(next month)
					this.wk5thuDay.setText("01");
					this.wk5friDay.setText("02");
					this.wk5satDay.setText("03");
				}
			}
			setBoxColor(wk5sunDay, nextDaySunBox);
			setBoxColor(wk5monDay, nextDayMonBox);
			setBoxColor(wk5tueDay, nextDayTueBox);
			setBoxColor(wk5wedDay, nextDayWedBox);
			setBoxColor(wk5thuDay, nextDayThuBox);
			setBoxColor(wk5friDay, nextDayFriBox);
			setBoxColor(wk5satDay, nextDaySatBox);
			break;
		case 3: // first day on a Wednesday
			// setting the BG colors
			makeDarkBox(lastDaySunBox);
			makeDarkBox(lastDayMonBox);
			makeDarkBox(lastDayTueBox);
			makeLightBox(lastDayWedBox);
			makeLightBox(lastDayThuBox);
			makeLightBox(lastDayFriBox);
			this.wk1sunDay.setText(String.valueOf(lastDayLastMonth - 2));
			this.wk1monDay.setText(String.valueOf(lastDayLastMonth - 1));
			this.wk1tueDay.setText(String.valueOf(lastDayLastMonth));
			this.wk1wedDay.setText("01");
			this.wk1thuDay.setText("02");
			this.wk1friDay.setText("03");
			this.wk1satDay.setText("04");
			this.wk2sunDay.setText("05");
			this.wk2monDay.setText("06");
			this.wk2tueDay.setText("07");
			this.wk2wedDay.setText("08");
			this.wk2thuDay.setText("09");
			this.wk2friDay.setText("10");
			this.wk2satDay.setText("11");
			this.wk3sunDay.setText("12");
			this.wk3monDay.setText("13");
			this.wk3tueDay.setText("14");
			this.wk3wedDay.setText("15");
			this.wk3thuDay.setText("16");
			this.wk3friDay.setText("17");
			this.wk3satDay.setText("18");
			this.wk4sunDay.setText("19");
			this.wk4monDay.setText("20");
			this.wk4tueDay.setText("21");
			this.wk4wedDay.setText("22");
			this.wk4thuDay.setText("23");
			this.wk4friDay.setText("24");
			this.wk4satDay.setText("25");
			this.wk5sunDay.setText("26");
			this.wk5monDay.setText("27");
			this.wk5tueDay.setText("28");
			// if Feburary on a leap year
			if ((displayedCalendarFace.getMonth() == 2 && displayedCalendarFace.isLeapYear())) {
				this.wk5wedDay.setText("29");
				this.wk5thuDay.setText("01");
				this.wk5friDay.setText("02");
				this.wk5satDay.setText("03");
				// if Feb not on a leap year
			} else if (getDisplayedMonth() == 2 && !displayedCalendarFace.isLeapYear()) {
				this.wk5tueDay.setText("29");
				this.wk5wedDay.setText("02");
				this.wk5thuDay.setText("03");
				this.wk5friDay.setText("04");
				this.wk5satDay.setText("05");
			} else {
				// if Month has 31 days
				if (displayedCalendarFace.getNumberOfDays() == 31) {
					this.wk5wedDay.setText("29");
					this.wk5thuDay.setText("30");
					this.wk5friDay.setText("31");
					this.wk5satDay.setText("01");
				} else {
					// if Month has 30 days(next month)
					this.wk5wedDay.setText("29");
					this.wk5thuDay.setText("30");
					this.wk5friDay.setText("01");
					this.wk5satDay.setText("02");
				}
			}
			setBoxColor(wk5sunDay, nextDaySunBox);
			setBoxColor(wk5monDay, nextDayMonBox);
			setBoxColor(wk5tueDay, nextDayTueBox);
			setBoxColor(wk5wedDay, nextDayWedBox);
			setBoxColor(wk5thuDay, nextDayThuBox);
			setBoxColor(wk5friDay, nextDayFriBox);
			setBoxColor(wk5satDay, nextDaySatBox);
			break;
		case 4:
			// setting the BG colors
			makeDarkBox(lastDaySunBox);
			makeDarkBox(lastDayMonBox);
			makeDarkBox(lastDayTueBox);
			makeDarkBox(lastDayWedBox);
			makeLightBox(lastDayThuBox);
			makeLightBox(lastDayFriBox);
			this.wk1sunDay.setText(String.valueOf(lastDayLastMonth - 3));
			this.wk1monDay.setText(String.valueOf(lastDayLastMonth - 2));
			this.wk1tueDay.setText(String.valueOf(lastDayLastMonth - 1));
			this.wk1wedDay.setText(String.valueOf(lastDayLastMonth));
			this.wk1thuDay.setText("01");
			this.wk1friDay.setText("02");
			this.wk1satDay.setText("03");
			this.wk2sunDay.setText("04");
			this.wk2monDay.setText("05");
			this.wk2tueDay.setText("06");
			this.wk2wedDay.setText("07");
			this.wk2thuDay.setText("08");
			this.wk2friDay.setText("09");
			this.wk2satDay.setText("10");
			this.wk3sunDay.setText("11");
			this.wk3monDay.setText("12");
			this.wk3tueDay.setText("13");
			this.wk3wedDay.setText("14");
			this.wk3thuDay.setText("15");
			this.wk3friDay.setText("16");
			this.wk3satDay.setText("17");
			this.wk4sunDay.setText("18");
			this.wk4monDay.setText("19");
			this.wk4tueDay.setText("20");
			this.wk4wedDay.setText("21");
			this.wk4thuDay.setText("22");
			this.wk4friDay.setText("23");
			this.wk4satDay.setText("24");
			this.wk5sunDay.setText("25");
			this.wk5monDay.setText("26");
			this.wk5tueDay.setText("27");
			this.wk5wedDay.setText("28");
			// if Feburary on a leap year
			if ((displayedCalendarFace.getMonth() == 2 && displayedCalendarFace.isLeapYear())) {
				this.wk5thuDay.setText("29");
				this.wk5friDay.setText("01");
				this.wk5satDay.setText("02");
				// if Feb not on a leap year
			} else if (getDisplayedMonth() == 2 && !displayedCalendarFace.isLeapYear()) {
				this.wk5thuDay.setText("01");
				this.wk5friDay.setText("02");
				this.wk5satDay.setText("03");
			} else {
				this.wk5thuDay.setText("29");
				this.wk5friDay.setText("30");
				// if Month has 31 days
				if (displayedCalendarFace.getNumberOfDays() == 31) {
					this.wk5satDay.setText("31");
				} else {
					// if Month has 30 days(next month)
					this.wk5satDay.setText("01");
				}
			}
			setBoxColor(wk5sunDay, nextDaySunBox);
			setBoxColor(wk5monDay, nextDayMonBox);
			setBoxColor(wk5tueDay, nextDayTueBox);
			setBoxColor(wk5wedDay, nextDayWedBox);
			setBoxColor(wk5thuDay, nextDayThuBox);
			setBoxColor(wk5friDay, nextDayFriBox);
			setBoxColor(wk5satDay, nextDaySatBox);
			break;
		case 5: // first day is on a Friday
			// setting the BG colors
			makeDarkBox(lastDaySunBox);
			makeDarkBox(lastDayMonBox);
			makeDarkBox(lastDayTueBox);
			makeDarkBox(lastDayWedBox);
			makeDarkBox(lastDayThuBox);
			makeLightBox(lastDayFriBox);
			this.wk1sunDay.setText(String.valueOf(lastDayLastMonth - 4));
			this.wk1monDay.setText(String.valueOf(lastDayLastMonth - 3));
			this.wk1tueDay.setText(String.valueOf(lastDayLastMonth - 2));
			this.wk1wedDay.setText(String.valueOf(lastDayLastMonth - 1));
			this.wk1thuDay.setText(String.valueOf(lastDayLastMonth));
			this.wk1friDay.setText("01");
			this.wk1satDay.setText("02");
			this.wk2sunDay.setText("03");
			this.wk2monDay.setText("04");
			this.wk2tueDay.setText("05");
			this.wk2wedDay.setText("06");
			this.wk2thuDay.setText("07");
			this.wk2friDay.setText("08");
			this.wk2satDay.setText("09");
			this.wk3sunDay.setText("10");
			this.wk3monDay.setText("11");
			this.wk3tueDay.setText("12");
			this.wk3wedDay.setText("13");
			this.wk3thuDay.setText("14");
			this.wk3friDay.setText("15");
			this.wk3satDay.setText("16");
			this.wk4sunDay.setText("17");
			this.wk4monDay.setText("18");
			this.wk4tueDay.setText("19");
			this.wk4wedDay.setText("20");
			this.wk4thuDay.setText("21");
			this.wk4friDay.setText("22");
			this.wk4satDay.setText("23");
			this.wk5sunDay.setText("24");
			this.wk5monDay.setText("25");
			this.wk5tueDay.setText("26");
			this.wk5wedDay.setText("27");
			this.wk5thuDay.setText("28");
			// if Feburary on a leap year
			if ((displayedCalendarFace.getMonth() == 2 && displayedCalendarFace.isLeapYear())) {
				this.wk5friDay.setText("29");
				this.wk5satDay.setText("01");
				// if Feb not on a leap year
			} else if (getDisplayedMonth() == 2 && !displayedCalendarFace.isLeapYear()) {
				this.wk5friDay.setText("01");
				this.wk5satDay.setText("02");
			} else {
				this.wk5friDay.setText("29");
				this.wk5satDay.setText("30");
			} if (displayedCalendarFace.getNumberOfDays()==31) {
				this.wk6sunDay.setText("31");
			}
			setBoxColor(wk5sunDay, nextDaySunBox);
			setBoxColor(wk5monDay, nextDayMonBox);
			setBoxColor(wk5tueDay, nextDayTueBox);
			setBoxColor(wk5wedDay, nextDayWedBox);
			setBoxColor(wk5thuDay, nextDayThuBox);
			setBoxColor(wk5friDay, nextDayFriBox);
			setBoxColor(wk5satDay, nextDaySatBox);
			break;
		case 6: // first day is on a Sunday
			// setting the BG colors
			makeDarkBox(lastDaySunBox);
			makeDarkBox(lastDayMonBox);
			makeDarkBox(lastDayTueBox);
			makeDarkBox(lastDayWedBox);
			makeDarkBox(lastDayThuBox);
			makeDarkBox(lastDayFriBox);
			this.wk1sunDay.setText(String.valueOf(lastDayLastMonth - 5));
			this.wk1monDay.setText(String.valueOf(lastDayLastMonth - 4));
			this.wk1tueDay.setText(String.valueOf(lastDayLastMonth - 3));
			this.wk1wedDay.setText(String.valueOf(lastDayLastMonth - 2));
			this.wk1thuDay.setText(String.valueOf(lastDayLastMonth - 1));
			this.wk1friDay.setText(String.valueOf(lastDayLastMonth));
			this.wk1satDay.setText("01");
			this.wk2sunDay.setText("02");
			this.wk2monDay.setText("03");
			this.wk2tueDay.setText("04");
			this.wk2wedDay.setText("05");
			this.wk2thuDay.setText("06");
			this.wk2friDay.setText("07");
			this.wk2satDay.setText("08");
			this.wk3sunDay.setText("09");
			this.wk3monDay.setText("10");
			this.wk3tueDay.setText("11");
			this.wk3wedDay.setText("12");
			this.wk3thuDay.setText("13");
			this.wk3friDay.setText("14");
			this.wk3satDay.setText("15");
			this.wk4sunDay.setText("16");
			this.wk4monDay.setText("17");
			this.wk4tueDay.setText("18");
			this.wk4wedDay.setText("19");
			this.wk4thuDay.setText("20");
			this.wk4friDay.setText("21");
			this.wk4satDay.setText("22");
			this.wk5sunDay.setText("23");
			this.wk5monDay.setText("24");
			this.wk5tueDay.setText("25");
			this.wk5wedDay.setText("26");
			this.wk5thuDay.setText("27");
			this.wk5friDay.setText("28");
			// if Feburary on a leap year
			if ((displayedCalendarFace.getMonth() == 2 && displayedCalendarFace.isLeapYear())) {
				this.wk5satDay.setText("29");
				// if Feb not on a leap year
			} else if (getDisplayedMonth() == 2 && !displayedCalendarFace.isLeapYear()) {
				this.wk5satDay.setText("01");
			} else {
				this.wk5satDay.setText("29");
				this.wk6sunDay.setText("30");
			} if (displayedCalendarFace.getNumberOfDays()==31) {
				this.wk6monDay.setText("31");
			}
			setBoxColor(wk5sunDay, nextDaySunBox);
			setBoxColor(wk5monDay, nextDayMonBox);
			setBoxColor(wk5tueDay, nextDayTueBox);
			setBoxColor(wk5wedDay, nextDayWedBox);
			setBoxColor(wk5thuDay, nextDayThuBox);
			setBoxColor(wk5friDay, nextDayFriBox);
			setBoxColor(wk5satDay, nextDaySatBox);
			break;
		}
		
		//here I am dealing with when months have 30/31 days and go beyond the calendar range
		showLines(displayedCalendarFace.needSplitLines());
		//setting today's date a different color (red)
		setToday();
	}

	// method to initialize the calendar on launch
	public void initData(String currentMonth) {
		this.monthSB.setText(currentMonth);
		this.yearSB.setText(Integer.toString(today.getYear()));
		this.datePicker.setValue(LocalDate.now());
	}
	


	// method to make last month's boxes dark
	public void makeDarkBox(VBox box) {
		box.setBackground(new Background(new BackgroundFill(Color.web("#a4a4a4"), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	// method to make current month's boxes back to normal
	public void makeLightBox(VBox box) {
		box.setBackground(new Background(new BackgroundFill(Color.web("#ededed"), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	// method to set end month Box colors
	public void setBoxColor(Text label, VBox box) {
		if (Integer.valueOf(label.getText()) < 23)
			makeDarkBox(box);
		else
			makeLightBox(box);
	}
	
	//method to create the 'invisible' lines for days outside of the calendar range
	//creating a partial border around one of the nodes in the boxes to make it seem like a line (Line class was acting up there)
	public void showLines(int numLines) {
		if (numLines==1) {
			this.sundaySplitAnchor.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
		            BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
		            CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
			this.wk6sunCircle.setVisible(true);
			this.wk6sunDay.setVisible(true);
		} else if (numLines==2) {
			this.sundaySplitAnchor.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
		            BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
		            CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
			this.mondaySplitAnchor.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
		            BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
		            CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
			this.wk6sunCircle.setVisible(true);
			this.wk6monCircle.setVisible(true);
			this.wk6sunDay.setVisible(true);
			this.wk6monDay.setVisible(true);
		}
	}
	//method to make the lines 'invisible' again
	public void clearLines() {
		this.sundaySplitAnchor.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	            BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
	            CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
		this.mondaySplitAnchor.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	            BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
	            CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));	
		this.wk6sunCircle.setVisible(false);
		this.wk6monCircle.setVisible(false);
		this.wk6sunDay.setVisible(false);
		this.wk6monDay.setVisible(false);
	}
	//this method is used to set the color of today's circle
	public void setToday() {
		
		MonthBuilder displayedCalendarFace = new MonthBuilder(getDisplayedMonth(), getDisplayedYear());
		
		//going through each circle + text and checking if it matches today
		
		if (displayedCalendarFace.getMonth()==today.getMonth() && displayedCalendarFace.getYear() == today.getYear()) {
			setColor(wk1sunDay,wk1sunCircle);
			setColor(wk1monDay,wk1monCircle);
			setColor(wk1tueDay,wk1tueCircle);
			setColor(wk1wedDay,wk1wedCircle);
			setColor(wk1thuDay,wk1thuCircle);
			setColor(wk1friDay,wk1friCircle);
			setColor(wk1satDay,wk1satCircle);
			setColor(wk2sunDay,wk2sunCircle);
			setColor(wk2monDay,wk2monCircle);
			setColor(wk2tueDay,wk2tueCircle);
			setColor(wk2wedDay,wk2wedCircle);
			setColor(wk2thuDay,wk2thuCircle);
			setColor(wk2friDay,wk2friCircle);
			setColor(wk2satDay,wk2satCircle);
			setColor(wk3sunDay,wk3sunCircle);
			setColor(wk3monDay,wk3monCircle);
			setColor(wk3tueDay,wk3tueCircle);
			setColor(wk3wedDay,wk3wedCircle);
			setColor(wk3thuDay,wk3thuCircle);
			setColor(wk3friDay,wk3friCircle);
			setColor(wk4satDay,wk3satCircle);
			setColor(wk4sunDay,wk4sunCircle);
			setColor(wk4monDay,wk4monCircle);
			setColor(wk4tueDay,wk4tueCircle);
			setColor(wk4wedDay,wk4wedCircle);
			setColor(wk4thuDay,wk4thuCircle);
			setColor(wk4friDay,wk4friCircle);
			setColor(wk4satDay,wk4satCircle);
			setColor(wk5sunDay,wk5sunCircle);
			setColor(wk5monDay,wk5monCircle);
			setColor(wk5tueDay,wk5tueCircle);
			setColor(wk5wedDay,wk5wedCircle);
			setColor(wk5thuDay,wk5thuCircle);
			setColor(wk5friDay,wk5friCircle);
			setColor(wk5satDay,wk5satCircle);
			setColor(wk6sunDay,wk6sunCircle);
			setColor(wk6monDay,wk6monCircle);
			
			
		}  
		else 
			resetColor();
			
	}
	
	public void setColor(Text text, Circle circle) {
		if (text.getText().equals(todayStr))
				circle.setFill(Paint.valueOf("#84e184"));
		else
			circle.setFill(Paint.valueOf("#BDBDBD"));
	}
	public void resetColor() {
		wk1sunCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk1monCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk1tueCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk1wedCircle.setFill(Paint.valueOf("#BDBDBD")); 
		wk1thuCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk1friCircle.setFill(Paint.valueOf("#BDBDBD")); 
		wk1satCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk2sunCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk2monCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk2tueCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk2wedCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk2thuCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk2friCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk2satCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk3sunCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk3monCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk3tueCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk3wedCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk3thuCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk3friCircle.setFill(Paint.valueOf("#BDBDBD")); 
		wk3satCircle.setFill(Paint.valueOf("#BDBDBD")); 
		wk4sunCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk4monCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk4tueCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk4wedCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk4thuCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk4friCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk4satCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk5sunCircle.setFill(Paint.valueOf("#BDBDBD")); 
		wk5monCircle.setFill(Paint.valueOf("#BDBDBD")); 
		wk5tueCircle.setFill(Paint.valueOf("#BDBDBD")); 
		wk5wedCircle.setFill(Paint.valueOf("#BDBDBD")); 
		wk5thuCircle.setFill(Paint.valueOf("#BDBDBD"));
		wk5friCircle.setFill(Paint.valueOf("#BDBDBD")); 
		wk5satCircle.setFill(Paint.valueOf("#BDBDBD")); 
		wk6sunCircle.setFill(Paint.valueOf("#BDBDBD")); 
		wk6monCircle.setFill(Paint.valueOf("#BDBDBD"));
	}

	// BUTTON FUNCTIONS:

	// launches the main calendar and initializes with today's date
	@FXML
	public void launchProgram(ActionEvent e) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CalendarFace1.fxml"));
		Parent calendarParent = loader.load();

		Scene scene = new Scene(calendarParent);

		// accessing the controller to initialize with today's date
		CalendarController controller = loader.getController();
		controller.initData(MONTHS[today.getMonth() - 1]);
		controller.displayCalendarFace();

		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();

	}
	
	@FXML
	public void launchAddEvent(ActionEvent e) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		AddEventController controller = fxmlLoader.getController();
		controller.setDatePickerToday();
		controller.savedText.setVisible(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Add Event");
		stage.setScene(new Scene(root));
		stage.showAndWait();

	}

	@FXML
	public void nextMonthButton(ActionEvent e) {

		// setting the month and year
		monthSB.setText(MONTHS[getNextMonth(monthSB.getText())]);
		if (monthSB.getText().equals("January")) {
			yearSB.setText(String.valueOf(getNextYear()));
		}
		displayCalendarFace();
	}

	@FXML
	public void previousMonthButton(ActionEvent e) {

		// setting the month and year
		monthSB.setText(MONTHS[getPreviousMonth(monthSB.getText())]);
		if (monthSB.getText().equals("December")) {
			yearSB.setText(String.valueOf(getLastYear()));
		}
		displayCalendarFace();
	}
	
	@FXML
	public void pickDate(ActionEvent e) {
		LocalDate selectedDate = this.datePicker.getValue();
		this.monthSB.setText(MONTHS[selectedDate.getMonthValue()-1]);
		this.yearSB.setText(String.valueOf(selectedDate.getYear()));
		displayCalendarFace();
	}
	


}