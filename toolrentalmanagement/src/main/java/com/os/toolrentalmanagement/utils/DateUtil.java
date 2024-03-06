package com.os.toolrentalmanagement.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.os.toolrentalmanagement.constant.AppConstant;

public class DateUtil {

	public static final String STANDARD_PATTERN = "MM/dd/yy";

	public static LocalDate IndependenceDayObserved (LocalDate labourDate) {
		int nYear=labourDate.getYear();
		
		// The 4th in July
		int nMonth = 7; //July
		LocalDate dtD = LocalDate.of(nYear, nMonth, 4);
		int nX = dtD.getDayOfWeek().getValue();
		
		switch(nX)
		{
			case 0 : // Sunday
				return LocalDate.of(nYear, nMonth, 5);
			case 1 : // Monday
			case 2 : // Tuesday
			case 3 : // Wednesday
			case 4 : // Thursday
			case 5 : // Friday
				return LocalDate.of(nYear, nMonth, 4);
			default :// Saturday
				return LocalDate.of(nYear, nMonth, 3);
		}
	}

	public static LocalDate findLabourDate(LocalDate labourDate) {
		int nYear=labourDate.getYear();
		
		// The first Monday in September
		int nMonth = 9; // September
		LocalDate dtD =  LocalDate.of(nYear, nMonth, 1);
		int nX = dtD.getDayOfWeek().getValue();
		switch(nX)
		{
			
			case 1 : // Monday
				return LocalDate.of(nYear, nMonth, 1);
			case 2 : // Tuesday
				return LocalDate.of(nYear, nMonth, 7);
			case 3 : // Wednesday
				return LocalDate.of(nYear, nMonth, 6);
			case 4 : // Thursday
				return LocalDate.of(nYear, nMonth, 5);
			case 5 : // Friday
				return LocalDate.of(nYear, nMonth, 4);
			case 6 : // Saturday
				return LocalDate.of(nYear, nMonth, 3);
			default : // Sunday
				return LocalDate.of(nYear, nMonth, 2);
		}
	}
	
	public static Integer calculateWeekendDays(LocalDate checkoutDate, Integer rentalDay) {
		LocalDate dueDate = checkoutDate.plusDays(rentalDay);
	
		final int startW = checkoutDate.getDayOfWeek().getValue();
//		final int endW = dueDate.getDayOfWeek().getValue();

		final long days = ChronoUnit.DAYS.between(checkoutDate, dueDate);
		final Integer weekEndDays = (int) (AppConstant.WEED_END_DAYS * ((days + startW)/7));
		return weekEndDays;
	}
	public static Integer calculateHolidays(LocalDate checkoutDate, Integer rentalDay) {
		Integer holidayCount = 0;
		LocalDate dueDate = checkoutDate.plusDays(rentalDay);
		LocalDate independenceDate = DateUtil.IndependenceDayObserved(checkoutDate);
		LocalDate labourDate = DateUtil.findLabourDate(checkoutDate);
		if(independenceDate.isBefore(dueDate) && independenceDate.isAfter(checkoutDate)) {
			++holidayCount;
		} else if(labourDate.isBefore(dueDate) && labourDate.isAfter(checkoutDate)) {
			++holidayCount;
		}
		return holidayCount;
	}
}
