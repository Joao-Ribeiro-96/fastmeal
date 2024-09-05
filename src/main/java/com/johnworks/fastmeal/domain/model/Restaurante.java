package com.johnworks.fastmeal.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;


	@JsonIgnoreProperties("hibernateLazyInitializer")// essa propriedade é criada em tempo de execução.
	@ManyToOne(fetch = FetchType.LAZY) // Faz buscas do objeto relacionado apenas quando será usado.
	@JoinColumn(name = "cozinha_id", nullable = false)// Indica o nome da coluna na bd
	private Cozinha cozinha;

	@Embedded// É um objeto vinculado a classe. Não gera tabelas.
	@JsonIgnore
	private Endereco endereco;

	@JsonIgnore
	@CreationTimestamp// Vincula a data e hora no momento de criação
	@Column(nullable = false)
	private LocalDateTime dataCadastro;

	@JsonIgnore
	@UpdateTimestamp// Sempre que é alterado, o jpa atualiza essa data/hora
	@Column(nullable = false)
	private LocalDateTime dataAtualizacao;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma-pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
	inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<FormaPagamento>();
	
}
