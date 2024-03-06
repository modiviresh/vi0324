package com.os.toolrentalmanagement.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BusinessErrorCodes {

	TOOL_DETAILS_NOT_FOUND(900, "error.tooldetail.not.found"),
	TOOL_CHARGE_NOT_FOUND(901, "error.toolcharge.not.found");
	
	private Integer status;
	private String errorMsg;

    public String getCode() {
        return name();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Integer getStatus() {
        return status;
    }

}
