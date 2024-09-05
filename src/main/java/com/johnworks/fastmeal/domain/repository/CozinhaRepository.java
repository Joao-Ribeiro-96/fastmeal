package com.johnworks.fastmeal.domain.repository;


import com.johnworks.fastmeal.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

    List<Cozinha> findCozinhaByNome(String nome);

    List<Cozinha> findAllByNomeContains(String nome);

    boolean existsByNome(String nome);
}
