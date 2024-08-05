package com.as0824.rentaltool.repository;


import com.as0824.rentaltool.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool, Long> {
    Optional<Tool> findById(String id);
}
