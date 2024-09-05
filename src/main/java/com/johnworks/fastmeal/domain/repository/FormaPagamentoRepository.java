package com.johnworks.fastmeal.domain.repository;


import com.johnworks.fastmeal.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {

	List<FormaPagamento> listar();
	FormaPagamento buscar(Long id);
	FormaPagamento salvar(FormaPagamento formaPagamento);
	void remover(FormaPagamento formaPagamento);
	
}
