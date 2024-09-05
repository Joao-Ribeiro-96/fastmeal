package com.johnworks.fastmeal.domain.repository;

import com.johnworks.fastmeal.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
}
