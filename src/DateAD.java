/**
 *
 * @author 
 * Date: Feb 27th, 2014
 */

import java.util.GregorianCalendar;

public class DateAD {
	
	//declare constants
	public static final short LOWEST_YR = 1760;
	public static final short MONTHS_IN_A_YEAR = 12;
	public static final short DAY_OF_THE_WEEK = 2;
	public static final short DAYS_IN_A_WEEK = 7;
	public static final String MONTH_NAMES [] = {"January", "February", "March", "April", "May", 
											"June", "July", "August","September", "October", 
											"November", "December"};
	
	public static final String DAY_OF_WEEK_NAMES [] = {"Sunday", "Monday", "Tuesday", "Wednesday", 
												"Thursday", "Friday", "Saturday"};
	//declare variables
	private short year;
	private short month;
	private short dayOfMonth;
	private short dayOfYear;
	private short dayOfWeek;
	
	//constructor default
	public DateAD(){
		this.setCurrentDate();
	}
	
	//constructor with 1 argument (month)
	public DateAD(short mon){
		setCurrentDate(mon);

	}
	
	//constructor with 2 argument (month, year)
	public DateAD(short mon, short yr){
		setCurrentDate(mon, yr);
	}
	
	//constructor with 3 argument (dayOfMonth, month, year)
	public DateAD(short dom, short mon, short yr){
		setCurrentDate(dom, mon, yr);
	}
	
	//Check if the year is a leap year
	public static boolean isLeapYear(short yr){
		if (yr % 400 == 0)
			return true;
		if (yr % 100 == 0)
			return false;
		return (yr % 4 ==0);
	}
	
	//Calculate days in month
	public static short daysInMonth(short mon, short yr){
		switch(mon){
		case 1: return 31; //Jan
		case 2: return (short) ((isLeapYear(yr))? 29 : 28); //Feb
		case 3: return 31; //Mar
		case 4: return 30; //Apr
		case 5: return 31; //May
		case 6: return 30; //Jun
		case 7: return 31; //July
		case 8: return 31; //Aug
		case 9: return 30; //Sept
		case 10: return 31; //Oct
		case 11: return 30; //Nov
		case 12: return 31; //Dec
		default: return 1; //default
		}		
	}
	
	//Count leap years from 1760 to given year
	private static short countLeaps(short y){
		short leapCount = 0;
		for (short i = LOWEST_YR; i <= y; i++){
			if (isLeapYear(i))
				leapCount++;
		}
		return leapCount;
		
	}
	
	//Return a date one day before the entered date
	public DateAD getYesterday(){
		short doy = this.getDayOfYear();
		short mon = this.getMonth();
		short dom = this.getDayOfMonth();
		short yr = this.getYear();
		
		//Check if yesterday falls in the previous year
		if ((doy -1 !=0)){
			if (dom -1 != 0){ //Check if yesterday falls in previous month
				dom = (short) (dom - 1);
				return new DateAD(dom, mon, yr);
			}
			else{
				mon = (short) (mon - 1);
				dom = DateAD.daysInMonth(mon, yr);
				return new DateAD(dom,mon,yr);
			}
		}
		else{
			yr = (short) (yr - 1);
			mon = 12;
			dom = DateAD.daysInMonth(mon, yr);
			return new DateAD(dom, mon, yr);
		}
		
	}
	
	//Return a date one day after the entered date
	public DateAD getTomorrow(){
		short doy = this.getDayOfYear();
		short mon = this.getMonth();
		short dom = this.getDayOfMonth();
		short yr = this.getYear();
		
		//Check if year is a leap year
		if(isLeapYear(yr)){
			if((doy + 1) <= 366){ //Check if tomorrow falls within the current year
				if (dom + 1 <= DateAD.daysInMonth(mon, yr)){ //Check if tomorrow falls within the month
					dom = (short) (dom + 1);
					return new DateAD(dom, mon, yr);
				}
				else{
					mon = (short) (mon + 1);
					dom = 1;
					return new DateAD(dom, mon, yr);
				}
			}
			else{
				yr = (short) (yr + 1);
				mon = 1;
				dom = 1;
				return new DateAD(dom, mon, yr);
			}
		}
		else{
			if((doy + 1) <= 365){ //Check if tomorrow falls within the year
				if (dom + 1 <= DateAD.daysInMonth(mon, yr)){ //Check if tomorrow falls within the month
					dom = (short) (dom + 1);
					return new DateAD(dom, mon, yr);
				}
				else{
					mon = (short) (mon + 1);
					dom = 1;
					return new DateAD(dom, mon, yr);
				}
			}
			else{
				yr = (short) (yr + 1);
				mon = 1;
				dom = 1;
				return new DateAD(dom, mon, yr);
			}
		}		
	}
	
	//Check if given date is less than passed date
	public boolean lessThan(DateAD date){
		if (this.year < date.getYear())
			return true;
		else if (this.year == date.getYear()){
			if(this.dayOfYear < date.getDayOfYear())
				return true;
		}
		return false;
		
	}
	
	//Check if given date is equal to passed date
	public boolean equals(DateAD date){
		if(this.year == date.year && this.dayOfYear == date.getDayOfYear())
			return true;
		return false;
		
	}
	
	//Check if the date is valid
	public boolean isValid(){
		if ((this.getMonth() > 12 || this.getMonth() < 1 ) && this.getDayOfMonth() > DateAD.daysInMonth(this.getMonth(), this.getYear()))
			return false;
		if (this.getYear() < 1760)
			return false;
		if (this.getDayOfMonth() >= 1 && this.getDayOfMonth() <= DateAD.daysInMonth(this.getMonth(), this.getYear()))
			return true;
		return false;
	}
	
	//-- returns the day-of-week, day-of-month, month, year (as text), 
	//	e.g., "Tuesday, 2 January, 2007"; 
	//overrides Object.toString()
	public String toString(){
		if (this.isValid())
			return DAY_OF_WEEK_NAMES[(isLeapYear(year)) ? this.getDayOfWeek()-1 :this.getDayOfWeek()] + ", " + this.getDayOfMonth()  + " " + MONTH_NAMES[this.getMonth() - 1] + ", " + this.getYear();
		return "";
		}
	
	//Set current date
	public void setCurrentDate(){
		GregorianCalendar cal = new GregorianCalendar();

		month = (short) (cal.get(GregorianCalendar.MONTH) + 1);

		year = (short)cal.get(GregorianCalendar.YEAR);

		dayOfMonth = (short)cal.get(GregorianCalendar.DAY_OF_MONTH);

		// add your own additional code to properly set dayOfYear and dayOfWeek

		// DO NOT use the GregorianCalendar class to do this--do it yourself
		
		this.setDayOfYear();
		this.setDayOfWeek();
	}
	
	//Set the month in current date to passed in month
	public void setCurrentDate(short dom){
		GregorianCalendar cal = new GregorianCalendar();

		month = (short) (cal.get(GregorianCalendar.MONTH) + 1);
		year = (short)cal.get(GregorianCalendar.YEAR);
		this.setDayOfMonth(dom);
		
		this.setDayOfYear();
		this.setDayOfWeek();
	}
	
	//Set current date to passed in month and year
	public void setCurrentDate(short mon, short yr){
		GregorianCalendar cal = new GregorianCalendar();

		this.setMonth(mon);
		this.setYear(yr);
		dayOfMonth = (short)cal.get(GregorianCalendar.DAY_OF_MONTH);
		
		this.setDayOfYear();
		this.setDayOfWeek();
	}
	
	//Set current date to passed in dayOfMonth, mon and yr
	public void setCurrentDate(short dom, short mon, short yr){

		this.setMonth(mon);
		this.setYear(yr);
		this.setDayOfMonth(dom);
		
		this.setDayOfYear();
		this.setDayOfWeek();
	}
	
	//properly sets dayOfYear. (January 1 is day 1).
	private void setDayOfYear(){
		short doy = 0 ;
		
		for (short i = 1; i < this.getMonth(); i++){
			doy = (short) (doy + DateAD.daysInMonth(i, year));
		}
		doy = (short) (doy + this.getDayOfMonth());
		
		dayOfYear = doy;
	}
	
	//properly sets dayOfWeek.
	private void setDayOfWeek(){
		int count = 0;
		
		for (short i = LOWEST_YR; i <= this.getYear(); i ++ ){
			count++;
		}
		
		count = count + countLeaps(this.getYear());
		
		dayOfWeek = (short) ((count + this.getDayOfYear()) % DAYS_IN_A_WEEK);;
	}
	
	//set year
	private void setYear(short yr){
		year = yr;
	}
	
	//set month
	private void setMonth(short mon){
		month = mon;
	}
	
	//set dayOfMonth
	private void setDayOfMonth(short dom){
		dayOfMonth = dom;
	}

	//getters
	public short getYear(){
		return year;
	}
	
	public short getMonth(){
		return month;
	}
	
	public short getDayOfMonth(){
		return dayOfMonth;
	}
	
	public short getDayOfYear(){
		return dayOfYear;
	}
	
	public short getDayOfWeek(){
		return dayOfWeek;
	}
}

