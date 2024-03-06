package com.os.toolrentalmanagement.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.os.toolrentalmanagement.model.ToolDetail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CheckoutDTO {

	private ToolDetail toolDetail;

	@JsonFormat(pattern = "MM/dd/yy")
	private LocalDate checkoutDate;
	
	private Float dailyCharge;
	
	private Integer rentalDay;

	private Integer weekendDay;

	private Integer holidayCount;

	private Integer discountPer;

	@JsonFormat(pattern = "MM/dd/yy")
	private LocalDate dueDate;
	
	private Integer chargeDay;
	
	private Float preDiscountCharge;

	private Float discountAmount;

	private Float finalCharge;
	
	public LocalDate getDueDate() {
		dueDate = checkoutDate.plusDays(rentalDay);
		return dueDate;
	}
	
	public Integer getChargeDay() {
		chargeDay = rentalDay-weekendDay-holidayCount;
		if(chargeDay<0) {
			chargeDay=0;
		}
		return chargeDay;
	}

	public Float getPreDiscountCharge() {
		this.preDiscountCharge = chargeDay * dailyCharge;
		return preDiscountCharge;
	}

	public Float getDiscountAmount() {
		this.discountAmount = (preDiscountCharge*discountPer)/100;
		return this.discountAmount;
	}

	public Float getFinalCharge() {
		this.finalCharge = preDiscountCharge-discountAmount;
		return this.finalCharge;
	}

}
