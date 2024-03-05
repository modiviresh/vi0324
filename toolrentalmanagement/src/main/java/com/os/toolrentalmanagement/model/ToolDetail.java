package com.os.toolrentalmanagement.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ToolDetail {
	private String toolCode;
	private Integer toolType;
	private Integer brandType;
	
}
