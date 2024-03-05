package com.os.toolrentalmanagement.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.os.toolrentalmanagement.dto.CheckoutDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportFacade {

	private final MessageSource messageSource; 

	public void generateReport(ReportTypes reportType, CheckoutDTO checkoutDTO) {
		switch (reportType) {
		case CONSOLE: {
			ConsoleReportHelper reportHelper = new ConsoleReportHelper();
			reportHelper.generateRentalAgreementReport(getAllFieldName(checkoutDTO));
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + reportType);
		}
	}

	public enum ReportTypes{
		CONSOLE;
	}
	
	private Map<String, Object> getAllFieldName(CheckoutDTO checkoutDTO) {
		Map<String, Object> fieldMap = new LinkedHashMap<>();
		fieldMap.put(messageSource.getMessage("label.tool_code", null, null), checkoutDTO.getToolDetail().getToolCode());
		fieldMap.put(messageSource.getMessage("label.tool_type", null, null), checkoutDTO.getToolDetail().getToolType());
		fieldMap.put(messageSource.getMessage("label.tool_brand", null, null), checkoutDTO.getToolDetail().getBrandType());
		fieldMap.put(messageSource.getMessage("label.rental_days", null, null), checkoutDTO.getRentalDay());
		
		fieldMap.put(messageSource.getMessage("label.checkout.date", null, null), checkoutDTO.getRentalDay());
		fieldMap.put(messageSource.getMessage("label.due_date", null, null), checkoutDTO.getRentalDay());
		fieldMap.put(messageSource.getMessage("label.daily.rental.charge", null, null), checkoutDTO.getRentalDay());
		
		fieldMap.put(messageSource.getMessage("label.charge_days", null, null), checkoutDTO.getRentalDay());
		fieldMap.put(messageSource.getMessage("label.prediscount.charge", null, null), checkoutDTO.getRentalDay());
		fieldMap.put(messageSource.getMessage("label.discount.percent", null, null), checkoutDTO.getDiscountPer()+"%");
		fieldMap.put(messageSource.getMessage("label.discount.amount", null, null), checkoutDTO.getRentalDay());
		fieldMap.put(messageSource.getMessage("label.final.charge", null, null), checkoutDTO.getRentalDay());
		
		return fieldMap;
	}
}
