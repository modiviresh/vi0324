package com.os.toolrentalmanagement.service.Impl;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.os.toolrentalmanagement.constant.AppConstant;
import com.os.toolrentalmanagement.dto.CheckoutDTO;
import com.os.toolrentalmanagement.exception.BusinessErrorCodes;
import com.os.toolrentalmanagement.exception.Precondition;
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
import com.os.toolrentalmanagement.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolRentalServiceImpl implements ToolRentalService, NoChargeDay{

	private final ReportFacade reportFacade;
	private final ToolDetailRepository toolDetailRepository;
	private final ToolChargeDetailRepository toolChargeDetailRepository;
	
	@Override
	public ToolDetail getToolDetails(String toolCode) {
		Optional<ToolDetail> toolDetailOptional = toolDetailRepository.findByToolCode(toolCode);
		Precondition.check(toolDetailOptional.isPresent(), BusinessErrorCodes.TOOL_DETAILS_NOT_FOUND, null);
		return toolDetailRepository.findByToolCode(toolCode).get();
	}

	@Override
	public void generateReport(ReportTypes reportType, CheckoutDTO checkoutDTO) {
		reportFacade.generateReport(reportType, checkoutDTO);
	}

	@Override
	public CheckoutDTO calculateCheckoutAmount(CheckoutRequest checkoutRequest) {
		ToolDetail toolDetail = getToolDetails(checkoutRequest.getToolCode());
		ToolChargeDetail toolChargeDetail = getToolRentalDetails(toolDetail.getToolType());
		Integer weekendDays = 0;
		Integer holidayCount= 0;
		if(toolChargeDetail.getWeekendCharge()== AppConstant.NUM_ZERO && isFallInWeekend(checkoutRequest.getCheckoutDate(), checkoutRequest.getRentalDay())) {
			weekendDays = DateUtil.calculateWeekendDays(checkoutRequest.getCheckoutDate(), checkoutRequest.getRentalDay());
		}
		if(toolChargeDetail.getHolidayCharge()==AppConstant.NUM_ZERO && isFallInHoliday(checkoutRequest.getCheckoutDate(), checkoutRequest.getRentalDay())) {
			holidayCount = DateUtil.calculateHolidays(checkoutRequest.getCheckoutDate(), checkoutRequest.getRentalDay());
		}
		CheckoutDTO checkoutDTO = CheckoutDTO.builder()
												.toolDetail(toolDetail)
												.checkoutDate(checkoutRequest.getCheckoutDate())
												.dailyCharge(toolChargeDetail.getDailyCharge())
												.rentalDay(checkoutRequest.getRentalDay())
												.weekendDay(weekendDays)
												.holidayCount(holidayCount)
												.discountPer(checkoutRequest.getDiscountPer())
												.build();
		generateReport(ReportTypes.CONSOLE, checkoutDTO);
		return checkoutDTO;
	}

	@Override
	public ToolChargeDetail getToolRentalDetails(ToolType toolType) {
		Optional<ToolChargeDetail> toolChargeOptional = toolChargeDetailRepository.findChargeByToolType(toolType);
		Precondition.check(toolChargeOptional.isPresent(), BusinessErrorCodes.TOOL_CHARGE_NOT_FOUND, null);
		return toolChargeOptional.get();
	}

	@Override
	public boolean isFallInHoliday(final LocalDate checkoutDate, Integer rentalDay) {
		LocalDate dueDate = checkoutDate.plusDays(rentalDay);
		LocalDate independenceDate = DateUtil.IndependenceDayObserved(checkoutDate);
		LocalDate labourDate = DateUtil.findLabourDate(checkoutDate);
		if(independenceDate.isBefore(dueDate) && independenceDate.isAfter(checkoutDate)) {
			return true;
		} else if(labourDate.isBefore(dueDate) && labourDate.isAfter(checkoutDate)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFallInWeekend(LocalDate checkoutDate, Integer rentalDay) {
		final DayOfWeek startW = checkoutDate.getDayOfWeek();
		if((startW.getValue()+rentalDay)>6) {
			return true;
		}
		return false;
	}

}
