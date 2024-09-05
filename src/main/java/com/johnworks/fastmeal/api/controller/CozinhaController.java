package com.johnworks.fastmeal.api.controller;

import java.util.List;
import java.util.Optional;

import com.johnworks.fastmeal.domain.exception.EntidadeNaoEncontradaException;
import com.johnworks.fastmeal.domain.exception.EntidadeEmUsoException;
import com.johnworks.fastmeal.domain.model.Cozinha;
import com.johnworks.fastmeal.domain.model.Restaurante;
import com.johnworks.fastmeal.domain.repository.CozinhaRepository;
import com.johnworks.fastmeal.domain.service.CadastroCozinhaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}
	
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		try{
			Cozinha cozinha = cozinhaRepository.getReferenceById(cozinhaId);
			return ResponseEntity.ok(cozinha);
		}catch( EntityNotFoundException ex){
			   return ResponseEntity.notFound().build();
		}	

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
			@RequestBody Cozinha cozinha) {
		try{
			Cozinha cozinhaAtual = cozinhaRepository.getReferenceById(cozinhaId);
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
			return ResponseEntity.ok(cozinhaAtual);

		}
		catch( EntityNotFoundException ex){
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
		try {

			cadastroCozinha.excluir(cozinhaRepository.getReferenceById(cozinhaId).getId());
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}

	@GetMapping("/cozinha/buscar-primeirp")
	public Optional<Cozinha> cozinhasBuscarPrimeiro(){
		return cozinhaRepository.buscarPrimeiro();
	}
	
}
