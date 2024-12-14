package com.johnworks.fastmeal.domain.repository;

import com.johnworks.fastmeal.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

    List<Restaurante> findComFreteGratis(String nome);
}
