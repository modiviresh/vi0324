package com.os.toolrentalmanagement.service.Impl;

import org.springframework.stereotype.Service;

import com.os.toolrentalmanagement.dto.CheckoutDTO;
import com.os.toolrentalmanagement.model.ToolDetail;
import com.os.toolrentalmanagement.payload.request.CheckoutRequest;
import com.os.toolrentalmanagement.service.ReportFacade;
import com.os.toolrentalmanagement.service.ReportFacade.ReportTypes;
import com.os.toolrentalmanagement.service.ToolRentalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolRentalServiceImpl implements ToolRentalService{

	private final ReportFacade reportFacade;

	@Override
	public ToolDetail getToolDetails(String toolCode) {
		return ToolDetail.builder().toolCode(toolCode).toolType(2).brandType(2).build();
	}

	@Override
	public void generateReport(ReportTypes reportType, CheckoutDTO checkoutDTO) {
		reportFacade.generateReport(reportType, checkoutDTO);
	}

	@Override
	public void calculateCheckoutAmount(CheckoutRequest checkoutRequest) {
		ToolDetail toolDetail = getToolDetails(checkoutRequest.getToolCode());
		CheckoutDTO checkoutDTO = CheckoutDTO.builder()
												.toolDetail(toolDetail)
												.rentalDay(checkoutRequest.getRentalDay())
												.discountPer(checkoutRequest.getDiscountPer())
												.build();
		generateReport(ReportTypes.CONSOLE, checkoutDTO);
	}

}
