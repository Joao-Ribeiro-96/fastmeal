package com.johnworks.fastmeal.domain.service;

import com.johnworks.fastmeal.domain.exception.EntidadeNaoEncontradaException;
import com.johnworks.fastmeal.domain.model.Cozinha;
import com.johnworks.fastmeal.domain.model.Restaurante;
import com.johnworks.fastmeal.domain.repository.CozinhaRepository;
import com.johnworks.fastmeal.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		try {
			Long cozinhaId = restaurante.getCozinha().getId();
			Cozinha cozinha = cozinhaRepository.getReferenceById(cozinhaId);
			restaurante.setCozinha(cozinha);
		}catch (EntityNotFoundException ex){
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com código %d", restaurante.getCozinha().getId()));
		}

		return restauranteRepository.save(restaurante);
	}
	
}
