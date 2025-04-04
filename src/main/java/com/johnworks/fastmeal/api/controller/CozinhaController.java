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
	public Cozinha buscar(@PathVariable Long cozinhaId) {

			return cadastroCozinha.buscarOuFalhar(cozinhaId);

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@PathVariable Long cozinhaId,
			@RequestBody Cozinha cozinha) {

			Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			return cadastroCozinha.salvar(cozinhaAtual);


	}
	
	@DeleteMapping("/{cozinhaId}")
	public void remover(@PathVariable Long cozinhaId) {
			cadastroCozinha.excluir(cozinhaRepository.getReferenceById(cozinhaId).getId());

	}

	@GetMapping("/cozinha/buscar-primeirp")
	public Optional<Cozinha> cozinhasBuscarPrimeiro(){
		return cozinhaRepository.buscarPrimeiro();
	}
	
}
