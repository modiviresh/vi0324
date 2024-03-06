package com.os.toolrentalmanagement.service;

import java.time.LocalDate;

public interface NoChargeDay {
	boolean isFallInHoliday(LocalDate checkoutDate, Integer rentalDay);
	boolean isFallInWeekend(LocalDate checkoutDate, Integer rentalDay);
}
