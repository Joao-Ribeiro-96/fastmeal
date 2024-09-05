package com.johnworks.fastmeal.domain.repository;


import com.johnworks.fastmeal.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
}
