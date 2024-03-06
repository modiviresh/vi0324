package com.os.toolrentalmanagement.dto;

import java.time.LocalDate;

import com.os.toolrentalmanagement.model.ToolDetail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CheckoutDTO {

	private ToolDetail toolDetail;

	private LocalDate checkoutDate;
	
	private Float dailyCharge;
	
	private Integer rentalDay;

	private Integer discountPer;
	//TODO: Need to calculate
	private LocalDate dueDate;
	//TODO: Need to calculate
	private Integer chargeDay;
	
	private Float preDiscountCharge;

	private Float discountAmount;

	private Float finalCharge;
	
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
