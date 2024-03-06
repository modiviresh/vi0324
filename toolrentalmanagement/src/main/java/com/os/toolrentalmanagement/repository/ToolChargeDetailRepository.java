package com.os.toolrentalmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.os.toolrentalmanagement.model.ToolChargeDetail;
import com.os.toolrentalmanagement.model.ToolType;

public interface ToolChargeDetailRepository extends JpaRepository<ToolChargeDetail, Long> {

	Optional<ToolChargeDetail> findChargeByToolType(ToolType toolType);
}
