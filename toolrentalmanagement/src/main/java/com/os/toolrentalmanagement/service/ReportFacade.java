package com.os.toolrentalmanagement.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.os.toolrentalmanagement.component.MessageTranslator;
import com.os.toolrentalmanagement.dto.CheckoutDTO;

@Service
public class ReportFacade {

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
		fieldMap.put(MessageTranslator.getMessage("label.tool_code"), checkoutDTO.getToolDetail().getToolCode());
		fieldMap.put(MessageTranslator.getMessage("label.tool_type"), checkoutDTO.getToolDetail().getToolType().getTypeName());
		fieldMap.put(MessageTranslator.getMessage("label.tool_brand"), checkoutDTO.getToolDetail().getBrandType().getBrandName());
		fieldMap.put(MessageTranslator.getMessage("label.rental_days"), checkoutDTO.getRentalDay());
		
		fieldMap.put(MessageTranslator.getMessage("label.checkout.date"), checkoutDTO.getCheckoutDate());
		fieldMap.put(MessageTranslator.getMessage("label.due_date"), checkoutDTO.getDueDate());
		fieldMap.put(MessageTranslator.getMessage("label.daily.rental.charge"), "$"+checkoutDTO.getDailyCharge());
		
		fieldMap.put(MessageTranslator.getMessage("label.charge_days"), checkoutDTO.getChargeDay());
		fieldMap.put(MessageTranslator.getMessage("label.prediscount.charge"), checkoutDTO.getPreDiscountCharge());
		fieldMap.put(MessageTranslator.getMessage("label.discount.percent"), checkoutDTO.getDiscountPer()+"%");
		fieldMap.put(MessageTranslator.getMessage("label.discount.amount"), checkoutDTO.getDiscountAmount());
		fieldMap.put(MessageTranslator.getMessage("label.final.charge"), checkoutDTO.getFinalCharge());
		
		return fieldMap;
	}
}
