package com.os.toolrentalmanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.os.toolrentalmanagement.dto.CheckoutDTO;
import com.os.toolrentalmanagement.payload.request.CheckoutRequest;
import com.os.toolrentalmanagement.service.ToolRentalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tool/rent")
@RequiredArgsConstructor
public class ToolRentalController {

	private final ToolRentalService toolRentalService;
	
	@PostMapping("/calculatecheckout")
	public CheckoutDTO calculateCheckoutAmount(@Valid @RequestBody CheckoutRequest checkoutRequest) {
		return toolRentalService.calculateCheckoutAmount(checkoutRequest);
	}
}
