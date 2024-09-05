package com.johnworks.fastmeal.api.controller;

import com.johnworks.fastmeal.domain.exception.EntidadeEmUsoException;
import com.johnworks.fastmeal.domain.exception.EntidadeNaoEncontradaException;
import com.johnworks.fastmeal.domain.model.Estado;
import com.johnworks.fastmeal.domain.repository.EstadoRepository;
import com.johnworks.fastmeal.domain.service.CadastroEstadoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {

		try{
		Estado estado = estadoRepository.getReferenceById(estadoId);
		return ResponseEntity.ok(estado);

	}catch (EntityNotFoundException ex){
		return ResponseEntity.notFound().build();
	}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstado.salvar(estado);
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId,
			@RequestBody Estado estado) {
		try{

			Estado estadoAtual = estadoRepository.getReferenceById(estadoId);

				BeanUtils.copyProperties(estado, estadoAtual, "id");

				estadoAtual = cadastroEstado.salvar(estadoAtual);
				return ResponseEntity.ok(estadoAtual);

		}
		catch (EntityNotFoundException ex){
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			cadastroEstado.excluir(estadoId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}
	
}
