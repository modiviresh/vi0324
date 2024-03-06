package com.os.toolrentalmanagement.service.Impl;


import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.os.toolrentalmanagement.constant.AppConstant;
import com.os.toolrentalmanagement.dto.CheckoutDTO;
import com.os.toolrentalmanagement.model.ToolChargeDetail;
import com.os.toolrentalmanagement.model.ToolDetail;
import com.os.toolrentalmanagement.model.ToolType;
import com.os.toolrentalmanagement.payload.request.CheckoutRequest;
import com.os.toolrentalmanagement.repository.ToolChargeDetailRepository;
import com.os.toolrentalmanagement.repository.ToolDetailRepository;
import com.os.toolrentalmanagement.service.NoChargeDay;
import com.os.toolrentalmanagement.service.ReportFacade;
import com.os.toolrentalmanagement.service.ReportFacade.ReportTypes;
import com.os.toolrentalmanagement.service.ToolRentalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolRentalServiceImpl implements ToolRentalService, NoChargeDay{

	private final ReportFacade reportFacade;
	private final ToolDetailRepository toolDetailRepository;
	private final ToolChargeDetailRepository toolChargeDetailRepository;
	
	@Override
	public ToolDetail getToolDetails(String toolCode) {
		return toolDetailRepository.findByToolCode(toolCode).get();
	}

	@Override
	public void generateReport(ReportTypes reportType, CheckoutDTO checkoutDTO) {
		reportFacade.generateReport(reportType, checkoutDTO);
	}

	@Override
	public void calculateCheckoutAmount(CheckoutRequest checkoutRequest) {
		ToolDetail toolDetail = getToolDetails(checkoutRequest.getToolCode());
		ToolChargeDetail toolChargeDetail = getToolRentalDetails(toolDetail.getToolType());
		if(toolChargeDetail.getWeekendCharge()== AppConstant.NUM_ZERO && isFallInWeekend(checkoutRequest.getCheckoutDate(), checkoutRequest.getRentalDay())) {

		}
		if(toolChargeDetail.getHolidayCharge()==AppConstant.NUM_ZERO && isFallInHoliday(checkoutRequest.getCheckoutDate(), checkoutRequest.getRentalDay())) {

		}
		CheckoutDTO checkoutDTO = CheckoutDTO.builder()
												.toolDetail(toolDetail)
												.checkoutDate(checkoutRequest.getCheckoutDate())
												.dailyCharge(toolChargeDetail.getDailyCharge())
												.rentalDay(checkoutRequest.getRentalDay())
												.discountPer(checkoutRequest.getDiscountPer())
												.build();
		generateReport(ReportTypes.CONSOLE, checkoutDTO);
	}

	@Override
	public ToolChargeDetail getToolRentalDetails(ToolType toolType) {
		return toolChargeDetailRepository.findChargeByToolType(toolType).get();
	}

	@Override
	public boolean isFallInHoliday(LocalDate checkoutDate, Integer rentalDay) {
		return false;
	}

	@Override
	public boolean isFallInWeekend(LocalDate checkoutDate, Integer rentalDay) {
		return false;
	}

}
