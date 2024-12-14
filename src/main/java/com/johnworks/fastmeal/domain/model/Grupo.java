package com.johnworks.fastmeal.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    private List<Usuario> usuarios;

    @ManyToMany
    private List<Permissao> permissoes;

}
