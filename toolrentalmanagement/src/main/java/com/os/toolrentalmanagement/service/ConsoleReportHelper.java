package com.os.toolrentalmanagement.service;

import java.text.MessageFormat;
import java.util.Map;

public class ConsoleReportHelper {
	public void generateRentalAgreementReport(Map<String, Object> reportDetail){
		reportDetail.forEach((k,v) -> {System.out.println(MessageFormat.format("{0} - {1}", k, v));});
	}

}
