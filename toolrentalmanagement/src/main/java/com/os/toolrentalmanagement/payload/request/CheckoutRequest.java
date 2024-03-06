package com.os.toolrentalmanagement.payload.request;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {

	@NotBlank(message = "{error.toolCode.empty}")
	private String toolCode;

	@NotNull(message = "{error.checkoutDate.empty}")
	@JsonFormat(pattern = "MM/dd/yy")
	private LocalDate checkoutDate;
	
	@NotNull(message = "{error.rentalDay.empty}")
	@Range(min = 1, message = "{error.rentalDay.inrange}")
	private Integer rentalDay;
	
	@NotNull(message = "{error.discountPer.empty}")
	@Range(min = 0, max = 100, message = "{error.discount.percentage.inrange}")
	private Integer discountPer;
}
