package com.johnworks.fastmeal.domain.repository;

import com.johnworks.fastmeal.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

	List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao permissao);
	void remover(Permissao permissao);
	
}
