package com.os.toolrentalmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tool_detail")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToolDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique=true)
	private String toolCode;
	
	@ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	private ToolType toolType;

	@OneToOne
	@JoinColumn(name = "brand_id", referencedColumnName = "id")
	private BrandType brandType;
	
}
