package com.johnworks.fastmeal.domain.service;

import com.johnworks.fastmeal.domain.exception.EntidadeEmUsoException;
import com.johnworks.fastmeal.domain.exception.EntidadeNaoEncontradaException;
import com.johnworks.fastmeal.domain.model.Cidade;
import com.johnworks.fastmeal.domain.model.Estado;
import com.johnworks.fastmeal.domain.repository.CidadeRepository;
import com.johnworks.fastmeal.domain.repository.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {

			Long estadoId = cidade.getEstado().getId();
		try{
			Estado estado = estadoRepository.getReferenceById(estadoId);
			cidade.setEstado(estado);
		}

		catch(EntityNotFoundException ex){
		throw new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de estado com código %d", estadoId));
	}

		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de cidade com código %d", cidadeId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
		}
	}
	
}
