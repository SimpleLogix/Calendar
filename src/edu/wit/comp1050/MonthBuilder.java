package edu.wit.comp1050;
import java.time.LocalDate;
import java.time.Month;
import java.time.Month;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar; 

public class MonthBuilder {
	 
	int W,day,month,year;
	boolean leapYear;
	
	// Date date = Calendar.getInstance().getTime();  
    // DateFormat currentDay = new SimpleDateFormat("dd"); 
   //  DateFormat currentMonth = new SimpleDateFormat("MMMM");
   //DateFormat currentYear = new SimpleDateFormat("YYYY");
    		 		
   //  String strDate = formatter.format(date);  
     
    LocalDate currentdate = LocalDate.now();
   
    //Getting the current day
    int currentDay = currentdate.getDayOfMonth();
 
    //Getting the current month
    Month currentMonth1 = currentdate.getMonth();
    int currentMonth = getCurrentMonth();
    
    //getting the current year
    int currentYear = currentdate.getYear();
    
 
	//default constructor constructs current date
	public MonthBuilder() {
		this.day=currentDay;
		this.month=getCurrentMonth();
		this.year=currentYear;
		this.W=getFirstDay(month,year);
	}
	public MonthBuilder( int _month, int year) {
		this.month=_month;
		this.year=year;
		this.W=getFirstDay(month,year);
	}
	public MonthBuilder(int day, int month, int year) {
		this.month=month;
		this.year=year;
		this.day=day;
	}
	
	//get+set methods
	public boolean isLeapYear() {
		
		
		if (year%4==0) {
			leapYear=true;
			if (year%100==0) {
				leapYear=false;
				if (year%400==0){
					leapYear=true;
				}
			}
		} return leapYear;
	}
	public int getW() {
		return W;
	}
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	
	//converts Month class to int representing months
	public int getCurrentMonth() {
		StringBuilder sb = new StringBuilder();
		sb.append(currentMonth1);
	    String strMonth = sb.toString();
	    switch (strMonth) {
	    case "JANUARY": return 1;
	    case "FEBURARY": return 2;
	    case "MARCH": return 3;
	    case "APRIL": return 4;
	    case "MAY": return 5;
	    case "JUNE": return 6;
	    case "JULY": return 7;
	    case "AUGUST": return 8;
	    case "SEPTEMBER": return 9;
	    case "OCTOBER": return 10;
	    case "NOVEMBER": return 11;
	    case "DECEMBER": return 12;
	    default : return 0;
	    }
	}
	
	public  int getCurrentDay() {
		return currentDay;
	}
	public int getCurrentYear() {
		return currentYear;
	}
	public int getNumberOfDays() {
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (isLeapYear())
				return 29;
			 else
				return 28;
		default :
			return 31;
		}
	}
	public void setW(int _W) {
		this.W=_W;
	}
	
	public void setMonth(int _month) {
		this.month=_month;
	}
	public void setDay(int _day) {
		this.day=_day;
	}
	public void setYear(int _year) {
		this.year=_year;
	}
	
	//Algorithm to get the weekday of the first day of the month
	//from the month and year
	public int getFirstDay (int month, int year) {
		
		//getting the century and year last 2 digits
		StringBuilder sb = new StringBuilder();
		int century=0, yearDigit=0;
		sb.append(year);
		if (sb.length()>3) {
		century = Integer.valueOf(sb.substring(0,2));
		yearDigit = Integer.valueOf(sb.substring(2,4));
		}
		//variables for the formula
		int yearCode = (yearDigit + yearDigit/4)%7;
		int monthCode=0;
		switch (month) {
		case 1:
		case 10: 
			monthCode=0;
			break;
		case 5: monthCode = 1;
			break;
		case 8: monthCode = 2;
			break;
		case 2:
		case 3:
		case 11: 
			monthCode = 3;
			break;
		case 6: monthCode = 4;
			break;
		case 9:
		case 12:
			monthCode = 5;
			break;
		case 4:
		case 7:
			monthCode = 6;
			break;
		}
		int centuryCode;
		switch (century) {
		case 19:
		case 23:
			centuryCode = 0;
			break;
		case 18:
		case 22:
			centuryCode = 2;
			break;
		case 17:
		case 21:
			centuryCode = 4;
			break;
		default:
			centuryCode = 6;
			break;
		}
		int leapYearCode = 0;
		//If the date is in a January or February of a leap year, you have to subtract one
		if ((month==2 || month==1) && isLeapYear())
			leapYearCode=1;
		
		//getting the day of the week (0=Sun 1=Mon ... 6=Sat)
		int W =  (yearCode + monthCode + centuryCode + 1 - leapYearCode)%7;
		
		return W;
	}
	public int getFirstDay() {
		return getFirstDay(this.month, this.year);
	}
	
	//method to check if month needs extra lines (for 30th and 31st)
	//returns 1 if only one line is needed, 2 for two lines, 0 for none
	public int needSplitLines() {
		if ((W==5 && getNumberOfDays()==31) || (W==6 && getNumberOfDays()==30)) 
		return 1;
		else if (W==6 && getNumberOfDays()==31) 
			return 2;
			else
				return 0;
		
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(month)+"/"+String.valueOf(day)+"/"+String.valueOf(year));
		
		return sb.toString();
	}
 }
