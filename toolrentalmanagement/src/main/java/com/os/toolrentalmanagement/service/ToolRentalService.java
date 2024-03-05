package com.os.toolrentalmanagement.service;

import com.os.toolrentalmanagement.dto.CheckoutDTO;
import com.os.toolrentalmanagement.model.ToolDetail;
import com.os.toolrentalmanagement.payload.request.CheckoutRequest;
import com.os.toolrentalmanagement.service.ReportFacade.ReportTypes;

public interface ToolRentalService {

	ToolDetail getToolDetails(String toolCode);

	void calculateCheckoutAmount(CheckoutRequest checkoutRequest);

	void generateReport(ReportTypes reportType, CheckoutDTO checkoutDTO);
}
