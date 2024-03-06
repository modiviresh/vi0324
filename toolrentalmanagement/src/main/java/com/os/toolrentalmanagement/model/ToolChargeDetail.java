package com.os.toolrentalmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tool_charge_detail")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToolChargeDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	private ToolType toolType;

	private Float dailyCharge;
	
	private Integer weekdayCharge;
	
	private Integer weekendCharge;
	
	private Integer holidayCharge;
}
