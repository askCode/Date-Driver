/**
 *
 * @author  
 * Date: Feb 27th, 2014
 */

import java.util.Date;
import java.util.GregorianCalendar;

public class DateDriver {
	public static void main(String [] args){
		
//		if (args.length == 0) {
//			System.out.println("");
//		}
//		else if(args.length == 1){
//			System.out.println("");
//		}
//		else if(args.length == 2){
//			System.out.println("");
//		}
//		else if(args.length == 3){
//			System.out.println("");
//		}
		
		
		//DateAD myDate = new DateAD();
		//DateAD myDate = new DateAD((short) 29);
		DateAD myDate = new DateAD((short) 12, (short) 2011);
		//DateAD myDate = new DateAD((short) 3, (short) 2, (short) 1761);
		
		DateAD currDate = new DateAD(); //Set current date
		
		//If the entered date passed is not valid reset the myDate object
		if (!myDate.isValid()){
			myDate = new DateAD();
		}
		
//		System.out.println("Day of year: " + myDate.getDayOfYear());
//		System.out.println("Day of month: " + myDate.getDayOfMonth());
//		System.out.println("Day of week: " + myDate.getDayOfWeek());
		System.out.println("Yesterday's date is : \t" + myDate.getYesterday().toString());
		System.out.println("Entered date is: \t" + myDate.toString());
		System.out.println("Tomorrow's date is : \t" + myDate.getTomorrow().toString());
		System.out.println("Current Date: \t\t" + currDate.toString());
		
		
		if (myDate.equals(currDate)) 
			System.out.println("The entered date is current date");
		else if (myDate.lessThan(currDate))
			System.out.println("The entered date is in the past");
		else
			System.out.println("The entered date is in the future");
		
		
	}
}
