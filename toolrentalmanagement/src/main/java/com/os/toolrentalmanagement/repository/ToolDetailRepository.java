package com.os.toolrentalmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.os.toolrentalmanagement.model.ToolDetail;

public interface ToolDetailRepository extends JpaRepository<ToolDetail, Long> {

	Optional<ToolDetail> findByToolCode(String toolCode);
}
