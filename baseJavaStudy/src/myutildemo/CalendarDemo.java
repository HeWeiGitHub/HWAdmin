package myutildemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarDemo {
	
	public static void main(String[] args) throws ParseException{
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss.SSS");
//		SimpleDateFormat sdf1=new SimpleDateFormat();
		Date date=new Date();
		System.out.println(sdf.format(date));
		System.out.println("**********************");
		String timestr="2018��5��9�� 11:15:30.905";
		Date date1=sdf.parse(timestr);
		System.out.println(date1);
		
//		Calendar calendar=Calendar.getInstance();
		Calendar calendar=Calendar.getInstance(new Locale("zh", "CN"));
		System.out.println(calendar);
		Date date2=calendar.getTime();
		System.out.println(sdf.format(date2));
		int year=calendar.get(Calendar.YEAR);
		System.out.println(year);
		
		
	}

}
