package com.os.toolrentalmanagement.dto;

import com.os.toolrentalmanagement.model.ToolDetail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CheckoutDTO {

	private ToolDetail toolDetail;

	private Integer rentalDay;
	
	private Integer discountPer;
}
